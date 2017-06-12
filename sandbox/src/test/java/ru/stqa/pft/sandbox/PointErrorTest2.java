package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointErrorTest2 {
  @Test
  public void testDistance() {
    Point p = new Point(12, 12, 8, 16);
    Assert.assertEquals(p.distance(), 33);
  }
}
