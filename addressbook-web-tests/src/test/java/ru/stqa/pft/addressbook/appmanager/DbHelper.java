package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by User on 21.07.2017.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }


  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData contact = (ContactData) session.createQuery("from ContactData where id = '" + id + "'").getSingleResult();
    session.getTransaction().commit();
    session.close();
    return contact;

  }

  public Contacts contactNotInGroup() {
    Contacts result = new Contacts();
    Groups groupsFull = groups();
    Contacts contactsFull = contacts();
    for (ContactData contact : contactsFull) {
      if (contact.getGroups().size() < groupsFull.size()) {
        result.add(contact);
      }
    }
    return new Contacts(result);
  }


  public Contacts contactAreInGroup() {
    Contacts result = new Contacts();
    Contacts contacts = contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() > 0) {
        result.add(contact);
      }
    }
    return new Contacts(result);
  }


}
