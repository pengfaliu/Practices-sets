package cn.paul.shape;

/**
 * Created by lfp on 2020/11/13.
 */
public class Shape {
    public  static void main(String[] args) {
        Shape C = new Circle();
        Shape S = new Square();
        Shape T = new Triangle();

        C.draw();
        S.draw();
        T.draw();


    }

    void draw() {

    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Circle.draw()");
    }
}

class Square extends Shape {
    void draw() {
        System.out.println("Square.draw()");
    }
}

class Triangle extends Shape {
    void draw() {
        System.out.println("Triangle.draw()");
    }
}