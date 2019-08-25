package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    // Precondition
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru", "test1"), true);
    }
    int before = app.getContactHelper().getContactCount();

    // Action
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("edited name", "edited middle name", "edited last name", "89993424433", "edited@email.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before);
  }
}
