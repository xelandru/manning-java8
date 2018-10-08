package exer.jComprehensive.ch7;


import java.util.Arrays;
import java.util.Map;

public class NumberFrequency {

    public static void main(String[] args) {
        int[] numbers = new int[]{1,2,3,1,2,3,3,3,3};

        Map<Integer, Long> frequency = Arrays
                .stream(numbers)
                .boxed()
                .collect(new NumberFrequencyCollector<>());

        System.out.println(frequency);
    }
}
