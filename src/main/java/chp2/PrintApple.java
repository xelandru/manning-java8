package chp2;


import java.util.List;

public class PrintApple {

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple:inventory) {
            String output = formatter.display(apple);
            System.out.println(output);
        }
    }
}
