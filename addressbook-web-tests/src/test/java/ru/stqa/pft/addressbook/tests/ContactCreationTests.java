package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Тестовый", "Тест", "6666", "t@mail.ru", "test1"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
