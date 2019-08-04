package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
  @Test
  public void testPointPositive() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 6);
    Assert.assertEquals(p1.distance(p1, p2),4.47213595499958);
  }

  @Test
  public void testPointDistanceIsNull() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 2);
    Assert.assertEquals(p1.distance(p1, p2),0);
  }

  @Test
  public void testPointDistanceSecondIsLess() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p1, p2),2.23606797749979);
  }

  @Test
  public void testPointDistanceFirstIsLess() {
    Point p1 = new Point(-3, -777);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p1, p2),777.0057914842076);
  }
}
