package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointTest {

  @Test
  public void testDistance() {
    Point p = new Point(0, 1);
    Assert.assertEquals(p.distance(p2), 1.4142135623730951);
  }
}
