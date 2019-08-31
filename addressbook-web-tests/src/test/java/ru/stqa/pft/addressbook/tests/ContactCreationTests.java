package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().сontactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().сontactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
