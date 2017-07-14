package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/stru.png") ;
    list.add(new Object[] {new ContactData().withFirstname("имя1").withLastname("фамилия1").withAddress("адрес1")
            .withEmail1("почта1").withWorkPhone("222").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("имя2").withLastname("фамилия2").withAddress("адрес2")
            .withEmail1("почта2").withEmail2("почта 2").withEmail3("почта2 2").withWorkPhone("222")});
    list.add(new Object[] {new ContactData().withFirstname("имя1").withLastname("фамилия1").withAddress("адрес1")
            .withEmail1("почта1").withHomePhone("54-54").withMobilePhone("989 999 99").withWorkPhone("222")});

    return list.iterator();

  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {

      app.goTo().homePage();
    //  ContactData contact = new ContactData().withFirstname(firstname).withLastname("Тест3")
      //        .withPhoto(photo);
      Contacts before = app.contact().all();
      app.contact().create(contact);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();

      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }


  }


