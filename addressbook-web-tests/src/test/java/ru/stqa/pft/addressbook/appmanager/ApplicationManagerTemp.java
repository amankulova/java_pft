package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 22.06.2017.
 */
public class ApplicationManagerTemp {
  FirefoxDriver wd;

  private SessionHelperTemp sessionHelperTemp;
  private NavigationHelperTemp navigationHelperTemp;
  private ContactHelper contactHelper;

  public static boolean isAlertPresent(FirefoxDriver wd) {
      try {
          wd.switchTo().alert();
          return true;
      } catch (NoAlertPresentException e) {
          return false;
      }
  }

  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    contactHelper = new ContactHelper(wd);
    navigationHelperTemp = new NavigationHelperTemp(wd);
    sessionHelperTemp = new SessionHelperTemp(wd);
    sessionHelperTemp.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelperTemp getNavigationHelperTemp() {
    return navigationHelperTemp;
  }
}
