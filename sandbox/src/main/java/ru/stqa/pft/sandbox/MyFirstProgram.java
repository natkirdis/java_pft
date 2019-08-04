package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Alexei");

    Square s = new Square(5.0);
    System.out.println("Площадь квадрата со стороной" + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4.0, 6.0);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " " + r.b + " = " + r.area());
  }

  public static void hello(String name) {
    System.out.println("Hello, " + name + "!");
  }
}