package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 12.07.2017.
 */
public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Тестовый")
              .withLastname("Пример")
              .withEmail1("test1").withEmail2("test2").withEmail3("test3")
              .withMobilePhone("222 444").withHomePhone("11").withWorkPhone("00 - 00")
              .withAddress("test"));
    }
  }

  @Test()
  public void testContactAdresses() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));


  }


}