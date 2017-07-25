        package ru.stqa.pft.addressbook.tests;

        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.ContactData;
        import ru.stqa.pft.addressbook.model.Contacts;
        import ru.stqa.pft.addressbook.model.GroupData;
        import ru.stqa.pft.addressbook.model.Groups;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      //если нет группы, то создать ее
      app.group().create(new GroupData().withName("test1"));
      assertThat(app.db().groups().size(), equalTo(1));
    }

    if (app.db().contacts().size() == 0) {
      //если нет контакта, то создать его
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstname("Gulzhan").withLastname("Aman"));
      assertThat(app.db().contacts().size(), equalTo(1));
    }
  }

  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    //выбор случайного контакта и группы для модификции привязки:
    ContactData modifiedContact = contacts.iterator().next();
    GroupData relatedGroup = groups.iterator().next();

    app.goTo().homePage();
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().selectRelatedGroup(relatedGroup);
    app.contact().submitAddToGroup();
    app.goTo().homePage();

  }
}