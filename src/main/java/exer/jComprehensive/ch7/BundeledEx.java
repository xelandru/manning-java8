package exer.jComprehensive.ch7;


import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class BundeledEx {

    static double[] array = new double[]{2.0, 1, 0, 0.1, -0.9, 12.1};

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
        int counter = 0;
        Arrays
                .stream(array)
                .filter(p -> {
                    if (p != smallest)
                        return counter
                    else
                        counter++;
                })

    }
}
