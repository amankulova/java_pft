package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void homePage() {
    if (isElementPresent ( By.id ( "maintable" ) )) {
      return;
    }
    click ( By.linkText ( "home" ) );
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    isAlertPresent();
    contactCache = null;
    gotoHomePage();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }



  public void selectContactById (int id) {
    wd.findElement(By.id("" + id)).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

 public void create(ContactData contact, boolean b) {
   initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    gotoHomePage();
  }

  public void submitDeletionContacts() {
    wd.switchTo ().alert ().accept ();

  }




  public void enterNewContact() {
    click ( By.xpath ( "//div[@id='content']/form/input[21]" ) );
  }



  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;
  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();

      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);

  }


  public void choosingGroupToAdd(GroupData group){

    new Select(wd.findElement(By.cssSelector("select[name='to_group']"))).selectByVisibleText(group.getName());

  }

  public void submitAddToGroup() {
    wd.findElement(By.name("add")).click();

  }

  public void submitRemoveFromGroup() {
    wd.findElement(By.name("remove")).click();
  }

  public void filterByGroup(GroupData relatedGroup) {
    new Select(wd.findElement(By.xpath("//select[@name='group']"))).selectByVisibleText(relatedGroup.getName());
  }

  public boolean isContactInGroup(ContactData modifiedContact, GroupData relatedGroup) {
    boolean passed = false;
    for (GroupData group : modifiedContact.getGroups()) {

      System.out.println((group.getName() + " - related:" +relatedGroup.getName()));
      if ((group.getName()).equals(relatedGroup.getName()))
      {passed = true;}
    }
    return passed;
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  //public void selectContactById(int id) {
   // wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
 // }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert().accept();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void addContactToGroup(ContactData contact, GroupData assosiateGroup) {
    selectContactById(contact.getId());
    choosingGroupToAdd(assosiateGroup);
    initAddContact();
  }

  public void initAddContact() {
    wd.findElement(By.name("add")).click();
  }

  public void deleteContactFromGroup(GroupData groupData, ContactData contactData ) {
    choosingGroupToDelete(groupData);
    selectContactById(contactData.getId());
    initDeleteContact();
  }

  public void choosingGroupToDelete(GroupData group){

    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
  }

  public void initDeleteContact() {
    wd.findElement(By.name("remove")).click();
  }


  public void selectJoinContactById(int id) {

    wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
  }
  public void addingInGroupById(int id) {
    click(By.cssSelector("select[name='to_group']"));
    click(By.cssSelector(".right>select>option[value='" + id + "']"));
    click(By.name("add"));
  }

  public void filterGroupsById(int id) {
    click( By.cssSelector("#right"));
    click(By.cssSelector("#right>select>option[value='" + id + "']"));
  }
  public void removeFromGroup() {
    click(By.cssSelector("input[name='remove']"));
  }

}
