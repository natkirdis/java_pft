package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru", "test1"), true);
    app.getContactHelper().submitContractCreation();
    app.getNavigationHelper().gotoContactPage();
  }

}
