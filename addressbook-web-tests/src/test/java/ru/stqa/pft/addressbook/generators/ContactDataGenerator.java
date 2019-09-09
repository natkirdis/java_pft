package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }


  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s,%s,%s,%s,%s,%s,%s,%s\n",
                contact.getName(), contact.getMiddleName(), contact.getLastName(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail1(), contact.getEmail2(),
                contact.getAddress()));
      }
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("name%s", i)).withMiddleName(String.format("middleName%s", i)).withLastName(String.format("lastName%s", i))
              .withHomePhone(String.format("000-00-0%s", i)).withMobilePhone(String.format("8(900)-000-00-0%s", i)).withWorkPhone(String.format("100-00-0%s", i))
              .withEmail1(String.format("test%s@mail.ru", i)).withEmail1(String.format("test%s@gmail.com", i)).withEmail2(String.format("test%s@yandex.ru", i))
              .withAddress(String.format("test address%s", i)));
    }
    return contacts;
  }
}
