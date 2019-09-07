package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().сontactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pit.jpg");
    ContactData contact = new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name")
            .withHomePhone("89993424433").withEmail("test@email.ru").withAddress("Test address").withPhoto(photo).withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().сontactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("test' name").withMiddleName("test middle name").withLastName("test last name").withHomePhone("89993424433").withEmail("test@email.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before));
  }

}
