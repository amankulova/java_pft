package ru.stqa.pft.sandbox;

public class MyFirstProgram {

 public static void main(String[] args) {
   hello("world");
   hello("user");
   hello("Gulzhan");

 Point p = new Point(3, 3 , 2, 4);
 System.out.println("Расстояние между точками A ( x1=" + p.x1 + "; y1=" + p.y1 + ") и Б (x2=" + p.x2 + "; y2=" + p.y2 + ") = " + p.distance());
 }
  /*
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + "=" + s.area());
 
    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
   }
 */

	public static void hello(String somebody) {
  System.out.println("Hello, " + somebody + "!");
  }

}