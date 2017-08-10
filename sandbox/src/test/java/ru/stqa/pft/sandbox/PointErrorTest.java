package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointErrorTest {

@Test(enabled = false)
public void testDistance() {
  Point p1 = new Point(5, 9);
  Point p2 = new Point(22, 34);
  Assert.assertEquals(p1.distance(p2), 36);
  }
}
