package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBaseTemp {

    @Test
    public void testContactDeletion() {
        app2.getNavigationHelperTemp().gotoHomePage();
        app2.getContactHelper().selectContact();
        app2.getContactHelper().deleteSelectedContactsAndAutoReturnToHomepage();
    }


}
