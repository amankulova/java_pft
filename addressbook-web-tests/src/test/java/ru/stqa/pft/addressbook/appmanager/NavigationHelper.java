package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 19.06.2017.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() { wd.findElement(By.linkText("groups")).click();
  }

  public void gotoHomePage() {  wd.findElement(By.linkText("home")).click();
  }
}
