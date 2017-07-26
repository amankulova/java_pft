package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by User on 22.06.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname( "gg" ).withLastname("nnn")
              .withMobilePhone("88").withHomePhone("87").withWorkPhone("55")
              .withEmail1("uu").withEmail2("uu").withEmail3("9"), true );





    }
  }

  @Test
  public void testGroupModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Тестовый5").withLastname("Тест5");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.group().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalToObject(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();

  }


}
