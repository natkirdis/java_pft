package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  //@BeforeMethod //запуск втроенного почтового сервера
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws Exception {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    app.james().createUser(user, password); //Создаем юзера на почтовом сервере james
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //Ждем два письма в течении 10 секунд. 1 - пользователю, 2 - админу;
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000); //Ждем письмо в течени 60000 (60 сек). Получаем письмо из внешнего почт сервера.
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

 // @AfterMethod(alwaysRun = true) //остановка втроенного почтового сервера
  public void stopMailServer() {
    app.mail().stop();
  }
}
