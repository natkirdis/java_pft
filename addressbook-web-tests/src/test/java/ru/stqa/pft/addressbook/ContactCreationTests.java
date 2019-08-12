package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("test name", "test middle name", "test last name", "89993424433", "test@email.ru"));
    submitContractCreation();
    returnToContactPage();
  }

}
