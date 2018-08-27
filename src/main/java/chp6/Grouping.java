package chp6;


import java.util.*;

import static chp6.Dish.menu;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class Grouping {
    enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
        System.out.println(groupDishesByType());
        System.out.println(groupByCaloricLevel());
        System.out.println(groupByTypeAndCaloricLevel());
        System.out.println(countDishesByType());
        System.out.println(mostCaloricByType());
        System.out.println(caloricLevelsByType());
        System.out.println(partitionedMenu());
    }


    private static Map<Dish.Type, List<Dish>> groupDishesByType() {

        return menu
                .stream()
                .collect(groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupByCaloricLevel() {

        return menu
                .stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() < 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() < 700)
                        return CaloricLevel.NORMAL;
                    else
                        return CaloricLevel.FAT;
                }));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByTypeAndCaloricLevel() {

        return menu
                .stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() < 400)
                                return CaloricLevel.DIET;
                            else if (dish.getCalories() < 700)
                                return CaloricLevel.NORMAL;
                            else
                                return CaloricLevel.FAT;
                        })));
    }

    private static Map<Dish.Type, Long> countDishesByType() {

        return menu
                .stream()
                .collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Dish> mostCaloricByType() {
        return menu
                .stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {

        return menu
                .stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toSet())));

    }

    private static Map<Boolean, List<Dish>> partitionedMenu() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {

        return menu
                .stream()
                .collect(
                        partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));
    }

    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {

        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}
