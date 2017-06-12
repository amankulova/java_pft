package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointTest {
}
  @Test
  public  void testDistance() {
    Point p = new Point(0, 0, 1,1);
    Assert.assertEquals(p.distance(), 25.0);
  }
}
