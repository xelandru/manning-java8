package main.java.exer.jComprehensive.ch7;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class ArrayExercises {

//    static double[] array = new double[]{2.0, -1, 0, 0.1, -0.9, 12.1};

    static int[] array = new int[] {1,1,2,2,0,1,2,0,3,4,2};

    /**
     * 7.9
     */
    static double smallestElement(double[] array) {

        return Arrays
                .stream(array)
                .min()
                .orElseThrow(() -> {
                    throw new RuntimeException("No element in the array");
                });
    }

    /**
     * 7.10
     */

    public static int indexOfSmallestElement(double[] array) {

        double smallest = smallestElement(array);
        return IntStream
                .range(0, array.length)
                .filter(x -> smallest == array[x])
                .findFirst()
                .orElse(-1);

    }


    /**
     * 7.11
     */
    public static double computeDeviation(double[] array) {


        double mean = Arrays
                .stream(array)
                .summaryStatistics()
                .getAverage();

        double deviation = Arrays
                .stream(array)
                .map(i -> Math.pow((i - mean), 2))
                .reduce(0, Double::sum);

        return Math.sqrt(deviation / (array.length - 1));

    }

    /**
     * 7.12
     */
    public static double[] reverseArray(double[] array) {

        return Arrays
                .stream(array)
                .collect(
                        LinkedList<Double>::new,
                        LinkedList::addFirst,
                        (l1, l2) -> {
                            throw new UnsupportedOperationException("No parallel implementation");
                        })
                .stream()
                .mapToDouble(Double::doubleValue)
                .toArray();

    }

    /**
     * Ex. 7.15
     *
     */
    public static int[] eliminateDuplicates(int[] list) {

        return Arrays
                .stream(list)
                .distinct()
                .toArray();

    }




    public static void main(String[] args) {


        System.out.println(Arrays.toString(eliminateDuplicates(array)));

    }
}
