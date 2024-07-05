package org.example;
import static org.example.Factorial.calculateFactorial;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Использование: java Factorial <число>");
            return;
        }

        try {
            int number = Integer.parseInt(args[0]);
            long factorial = calculateFactorial(number);
            System.out.println("Факториал из " + number + " это " + factorial);
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, укажите действительное целое число.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}