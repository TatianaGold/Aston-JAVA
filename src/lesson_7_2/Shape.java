package lesson_7_2;
interface Shape {
    double getPerimeter();

    double getArea();

    void setFillColor(String color);

    void setBorderColor(String color);

    String getFillColor();

    String getBorderColor();

    default void printDetails() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}