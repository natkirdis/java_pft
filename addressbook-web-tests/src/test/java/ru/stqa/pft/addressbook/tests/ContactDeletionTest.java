package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withName("test name").withMiddleName("test middle name").withLastName("test last name").withPhoneNumber("89993424433").withEmail("test@email.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().сontactPage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
