package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.models.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {

  private ApplicationManager app;
  private final Wiser wiser;

  //Инициализвция помошника, который запускает почтовый сервер.
  public MailHelper(ApplicationManager app) {
    this.app = app;
    wiser = new Wiser(); //Создание почтового сервера
  }

  //Ожидание доставки почты, т.к. почта приходит не мгновенно
  //count - количество писем
  //timeout - время ожидания
  public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
    long start = System.currentTimeMillis(); //Запоминает текущее время.
    //Проверка, что текущее время не превышает момент старта + таймаут. Т.е. надо ждать почту
    while (System.currentTimeMillis() < start + timeout){
      if (wiser.getMessages().size() >= count){ //Если почты пришло достаточно, то ожидание заканчивается
        //Тут происходит преобразование в список реального объекта (который находится в почтовом ящике на почт сервере, т.е письмо) в модельный
        //Список => превращаем в поток, ко всем письмам применяем функцию toModelMail. Получившиеся новые объекты собираем в список
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }
    }
    try { //Если почты мало => надо подождать 1000млс
      Thread.sleep(1000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }
    throw new Error("No Mail!");
  }


  //Тут происходит преобразование реального почтового сообщения с почт.сервера в модельное.
  public static MailMessage toModelMail(WiserMessage m) {
    try {
      //В перемменную mm добавляется реальное почтовое сообщение
      MimeMessage mm = m.getMimeMessage();

      //"mm.getAllRecipients()[0].toString()" - извлекает список получателей, где [0] - это первый получатель.
      //"mm.getContent()" - извлекает контент из письма и преобразует его в одну строку. После, все это попадает в модельный объект MailMessage

      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    } catch (MessagingException e) { //Перехват ошибок, если они будут
      e.printStackTrace(); //Вывод сообщения на консоль
      return null;
    } catch (IOException e) { //Перехват ошибок, если они будут
      e.printStackTrace(); //Вывод сообщения на консоль
      return null;
    }
  }

  public void start(){
    wiser.start();
  }

  public void stop(){
    wiser.stop();
  }
}