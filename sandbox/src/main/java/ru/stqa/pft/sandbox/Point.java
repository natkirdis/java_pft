package ru.stqa.pft.sandbox;

public class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {
    return Math.sqrt((Math.pow((this.x - p2.x), 2) + Math.pow((this.y - p2.y), 2)));
  }

  public static void main(String[] args) {
    Point p1 = new Point(1, -3);
    Point p2 = new Point(-2, 5);
    System.out.println("Расстояние между точками: (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
  }
}
