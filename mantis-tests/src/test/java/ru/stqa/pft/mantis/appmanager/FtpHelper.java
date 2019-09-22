package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;

  //Инициализируется ФТП клиент, который будет устанавливать соединение, передавать файлы.
  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient();
  }

  //Загружает нужный файл, а старый временно переименовывает
  //file - локальный файл, который должен быть загружен на удаленную машину
  //target - имя удаленного файла, куда все загружается
  //backup - имя резервной копии, если удаленнй файл уже существует
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host")); // Устанавливает соединение с удаленной машиной по протоколу ftp
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password")); // Выполняет вход
    ftp.deleteFile(backup); //Удаляет предыдущую резервная копия файла
    ftp.rename(target, backup); //Переименовывает удаленный файл, затем делается резервная копия
    ftp.enterLocalPassiveMode(); //Включается пассивный режим передачи файлов
    ftp.storeFile(target, new FileInputStream(file)); //Передает локальный файл на удаленную машину, в которой он сохраняется в файл под именем target
    ftp.disconnect(); //Разрыв соединения
  }

  //Восстанавливает старый файл
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(backup); //Удаляет файл, который мы загрузили вначале
    ftp.rename(target, backup); //Востанавливает оригинальный файл из резервной копии
    ftp.disconnect(); //Разрыв соединения
  }
}