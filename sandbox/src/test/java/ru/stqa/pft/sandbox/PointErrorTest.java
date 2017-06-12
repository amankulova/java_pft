package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 12.06.2017.
 */
public class PointErrorTest {

@Test
public void testDistance() {
  Point p = new Point(6, 6, 4, 8);
  Assert.assertEquals(p.distance(), 4);
  }
}
