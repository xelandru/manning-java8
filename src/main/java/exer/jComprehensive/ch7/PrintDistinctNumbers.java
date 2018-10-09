package exer.jComprehensive.ch7;


import java.util.Arrays;

/**
 * 7.5
 */
public class PrintDistinctNumbers {

    static int[] numbers = new int[]{1, 2, 3, 4, 4, 1, 3, 1};

    static long distinctCounter(int[] array) {

        return Arrays
                .stream(array)
                .distinct()
                .count();

    }

    public static void main(String[] args) {
        System.out.println(distinctCounter(numbers));
    }
}
