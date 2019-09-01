package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433")
              .withEmail("test@email.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().сontactPage();
    Set<ContactData> after = app.contact().all();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
