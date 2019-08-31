package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().сontactPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
