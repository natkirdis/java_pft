package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public FirefoxDriver wd;

   // создаем ссылку на класс
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    // инициализируем в методе init, который вызывается перед тестами
    // передаем драйвер в конструктор, чтобы helper могли им пользоваться
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    // обращаемся через помощник
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  // геттеры
  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
  public ContactHelper getContactHelper() {
    return contactHelper;
  }
  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}