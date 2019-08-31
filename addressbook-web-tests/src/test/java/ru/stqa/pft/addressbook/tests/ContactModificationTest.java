package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().
              withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(before.get(index).getId()).withName("edited name").withMiddleName("edited middle name").withLastName("edited last name").withPhoneNumber("89993424433").withEmail("edited@email.ru");

    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
