package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContactsAndAutoReturnToHomepage();
    app.getContactHelper().isAlertPresent();
    app.getContactHelper().returnToContactPage();

  }
}
