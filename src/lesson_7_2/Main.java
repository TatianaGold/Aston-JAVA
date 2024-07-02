package lesson_7_2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        circle.setFillColor("Красный");
        circle.setBorderColor("Черный");

        Shape rectangle = new Rectangle(4, 7);
        rectangle.setFillColor("Синий");
        rectangle.setBorderColor("Зеленый");

        Shape triangle = new Triangle(3, 4, 5);
        triangle.setFillColor("Желтый");
        triangle.setBorderColor("Фиолетовый");

        System.out.println("\nКруг:");
        circle.printDetails();

        System.out.println("\nПрямоугольник:");
        rectangle.printDetails();

        System.out.println("\nТреугольник:");
        triangle.printDetails();
    }
}
