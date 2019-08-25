package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru", "test1"), true);
    app.getNavigationHelper().gotoContactPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
