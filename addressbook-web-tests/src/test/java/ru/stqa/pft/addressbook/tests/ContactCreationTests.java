package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru"));
    app.submitContractCreation();
    app.returnToContactPage();
  }

}
