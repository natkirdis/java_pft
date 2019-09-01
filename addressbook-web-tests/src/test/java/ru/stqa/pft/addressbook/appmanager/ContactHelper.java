package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContractCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getPhoneNumber());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeContactDeletionAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int id) {
    wd.findElement(By.xpath("//a[@href = 'edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContractCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    closeContactDeletionAlert();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id = '" + id + "']")).click();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String name = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("td/input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

}
