package org.example;

import static org.example.Factorial.calculateFactorial;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Factorial <number>");
            return;
        }

        try {
            int number = Integer.parseInt(args[0]);
            long factorial = calculateFactorial(number);
            System.out.println("Factorial of " + number + " is " + factorial);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}