package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManagerTemp;

/**
 * Created by User on 22.06.2017.
 */
public class TestBaseTemp {

  protected final ApplicationManagerTemp app2 = new ApplicationManagerTemp();

  @BeforeMethod
  public void setUp() throws Exception {
    app2.init();
  }

  @AfterMethod
  public void tearDown() {
    app2.stop();
  }

  public ApplicationManagerTemp getApp2() {
    return app2;
  }
}
