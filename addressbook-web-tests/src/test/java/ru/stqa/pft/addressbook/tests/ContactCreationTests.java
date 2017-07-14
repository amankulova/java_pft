package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    list.add(new Object[] {"test 1", "test 1"});
    list.add(new Object[] {"test 2", "test 2"});
    list.add(new Object[] {"test 3", "test 3"});

    return list.iterator();

  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(String firstname, String lastname) {

      app.goTo().homePage();
      File photo = new File("src/test/resources/stru.png") ;
      ContactData contact = new ContactData().withFirstname(firstname).withLastname("Тест3")
              .withPhoto(photo);
      Contacts before = app.contact().all();
      app.contact().create(contact);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();

      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }


  }


