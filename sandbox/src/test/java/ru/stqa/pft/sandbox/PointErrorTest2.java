package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointErrorTest2 {
  @Test
  public void testDistance() {
    Point p1 = new Point(3, 12);
    Point p2 = new Point(6, 12);
    Assert.assertEquals(p1.distance(p2), 3);
  }
}
