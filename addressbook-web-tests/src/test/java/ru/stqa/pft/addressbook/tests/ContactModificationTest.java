package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("test name").withMiddleName("test middle name").withLastName("test last name")
              .withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withName("edited name").withMiddleName("edited middle name").withLastName("edited last name")
            .withPhoneNumber("89993424433").withEmail("edited@email.ru");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
