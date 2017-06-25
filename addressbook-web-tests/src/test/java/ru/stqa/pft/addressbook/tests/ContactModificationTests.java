package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by User on 22.06.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Тестовый", "Тест", "6666", "t@mail.ru", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Изменение", "Тест", "6666", "t@mail.ru", null));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}
