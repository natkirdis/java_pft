package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().сontactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() throws Exception {
    app.goTo().сontactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("test' name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before));
  }

}
