package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    // Precondition
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru", "test1"), true);
    }
    int before = app.getContactHelper().getContactCount();

    // Action
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeContactDeletionAlert();
    app.getNavigationHelper().gotoContactPage();
    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before - 1);
  }
}
