package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 22.06.2017.
 */
public class NavigationHelperTemp {
  private FirefoxDriver wd;

  public NavigationHelperTemp(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoHomePage() {
      wd.findElement(By.linkText("home")).click();
  }
}
