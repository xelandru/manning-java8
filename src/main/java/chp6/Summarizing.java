package chp6;


import java.util.Comparator;
import java.util.IntSummaryStatistics;

import static java.util.stream.Collectors.*;

import static chp6.Dish.menu;

public class Summarizing {

    private static long howManyDishes() {

        return menu.stream().count();
    }

    private static double findMostCalories() {
        return menu
                .stream()
                .max(Comparator.comparingInt(Dish::getCalories))
                .get()
                .getCalories();

    }

    private static int totalCalories() {
        return menu
                .stream()
                .collect(summingInt(Dish::getCalories));
    }

    private static int sumCaloriesWithReducing() {

        return menu
                .stream()
                .collect(reducing(
                        0,
                        Dish::getCalories,
                        (Integer::sum
                )));

    }

    private static IntSummaryStatistics menuStatistics() {

        return menu
                .stream()
                .collect(summarizingInt(Dish::getCalories));

    }

    private static String dishesName() {

        return menu
                .stream()
                .map(Dish::getName)
                .collect(joining(", "));
    }

    public static void main(String[] args) {
        System.out.println(howManyDishes());
        System.out.println(findMostCalories());
        System.out.println(totalCalories());
        System.out.println(menuStatistics());
        System.out.println(dishesName());
    }

}
