package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(0, 2);
    Point p2 = new Point(0, 2);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
}
