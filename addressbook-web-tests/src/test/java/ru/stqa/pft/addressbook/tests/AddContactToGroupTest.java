        package ru.stqa.pft.addressbook.tests;

        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.ContactData;
        import ru.stqa.pft.addressbook.model.Contacts;
        import ru.stqa.pft.addressbook.model.GroupData;
        import ru.stqa.pft.addressbook.model.Groups;
        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().isEmpty()) {
      app.group().create(new GroupData()
              .withName("test1"));
    }

    app.goTo().homePage();
    if (app.contact().all().isEmpty()) {
      app.contact().create(new ContactData()
              .withFirstname("test")
              .withLastname("tesst"));
    }

         }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    if (app.db().contactNotInGroup().isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testtest"));
      app.goTo().homePage();

    }
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    Groups group = app.db().groups();
    ContactData modifiedContact = app.db().contactNotInGroup().iterator().next();
    GroupData addedGroup = group.iterator().next();
    app.contact().selectContact(modifiedContact.getId());
    app.contact().addContactToGroup(addedGroup.getId());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
  }
}
