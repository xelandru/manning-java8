package chp6;


import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class PartitionPrimeNumbers {


    public static void main(String[] args) {

        System.out.println(partitionPrimes(20));
    }


    private static boolean isPrime(int candidate) {

        return IntStream
                .rangeClosed(2, (int) Math.sqrt(candidate))
                .noneMatch(i -> candidate % i == 0);
    }


    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {

        return IntStream
                .rangeClosed(2, n)
                .boxed()
                .collect(groupingBy(PartitionPrimeNumbers::isPrime));

    }
}
