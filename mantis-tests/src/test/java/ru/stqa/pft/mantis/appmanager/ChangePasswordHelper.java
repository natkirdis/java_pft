package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void loginUI(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void password(String user) {
    click(By.xpath("//*[@class='menu-text' and contains(text(),' Manage')]/parent::a")); //Управление
    click(By.xpath("//a[contains(text(), 'Manage Users')]")); //Управление пользователями
    click(By.xpath("//a[contains(text(), 'user1569183796749')]"));
    click(By.cssSelector("input[value='Reset Password']")); //Сбросить пароль

  }

  public void finish(String confirmationLink, String user, String newPassword) {
    wd.get(confirmationLink);
    type(By.cssSelector("input[id='realname']"), user);
    type(By.cssSelector("input[id='password']"), newPassword);
    type(By.cssSelector("input[id='password-confirm']"), newPassword);
    click(By.cssSelector("button[type='submit']"));

  }

 /* public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Войти']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }

  public void password(UserData user) throws IOException {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Управление'])[1]/preceding::i[1]"));
    click(By.linkText("Управление пользователями"));
    click(By.xpath(String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", user.getId())));
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void finish(String confirmationLink, UserData user, String newPassword) {
    wd.get(confirmationLink);
    type(By.cssSelector("input[id='realname']"), user.getUsername());
    type(By.cssSelector("input[id='password']"), newPassword);
    type(By.cssSelector("input[id='password-confirm']"), newPassword);
    click(By.cssSelector("button[type='submit']"));

  }*/
}