package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBaseTemp {

    @Test
    public void testContactCreation() {

        app2.getNavigationHelperTemp().gotoHomePage();
        app2.getContactHelper().initContactCreation();
        app2.getContactHelper().fillContactForm(new ContactData("Тестовый", "Тест", "6666", "t@mail.ru"));
        app2.getContactHelper().submitContactCreationAndAutoReturnToHomepage();
    }

}
