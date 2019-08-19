package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String middleName;
  private final String lastName;
  private final String phoneNumber;
  private final String email;
  private  String group;

  public ContactData(String name, String middleName, String lastName, String phoneNumber, String email, String group) {
    this.name = name;
    this.middleName = middleName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
