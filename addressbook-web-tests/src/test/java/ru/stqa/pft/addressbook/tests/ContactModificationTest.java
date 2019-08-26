package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() {
    // Precondition
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    // Action
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData( before.get(before.size() - 1).getId(),"edited name", "edited middle name", "edited last name", "89993424433", "edited@email.ru", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
