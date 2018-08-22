package chp5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by alex on 8/2/18.
 */
public class PythagorasTriangle {
    public static void main(String[] args) {


        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(b * b + a * a) % 1 == 0)
                                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));


        pythagoreanTriples.limit(10).forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));


    }

}
