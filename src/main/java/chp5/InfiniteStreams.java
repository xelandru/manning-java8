package chp5;

import java.util.stream.Stream;

/**
 * Created by alex on 8/2/18.
 */
public class InfiniteStreams {
    public static void main(String[] args) {
//        Stream.iterate(new int[]{0, 1},
//                x -> new int[]{x[1], x[0] + x[1]})
//                .limit(20)
//                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream
                .generate(() -> (int) (10 * Math.random()))
                .limit(10)
                .forEach(System.out::println);


    }

}
