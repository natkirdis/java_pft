package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().сontactPage();
      app.contact().create(new ContactData()
              .withFirstName("test name").withMiddleName("test middle name").withLastName("test last name")
              .withHomePhone("89993424433").withEmail("test@email.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("edited name").withMiddleName("edited middle name").withLastName("edited last name")
            .withHomePhone("89993424433").withMobilePhone("89993424431").withWorkPhone("333-22-11").withAddress("edited adress")
            .withEmail("edited@email.ru").withEmail1("edited1@email.ru").withEmail2("edited2@email.ru");
    app.goTo().сontactPage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();;
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}