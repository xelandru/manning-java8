package chp7;


import java.util.function.Function;
import java.util.stream.LongStream;

public class SumCalculator {

    public static void main(String[] args) {

        long n = 10_000_000L;
        Function<Long, Long> function = SumCalculator::iterativeSum;
        System.out.println("Iterative: " + measureSumPerformance(function, n));
        function = SumCalculator::sequentialSum;
        System.out.println("Sequential: " + measureSumPerformance(function, n));
        function = SumCalculator::parallelSum;
        System.out.println("Parallel: " + measureSumPerformance(function, n));
        function = SumCalculator::sideEffectParallelSum;
        System.out.println("Side Effect: " + measureSumPerformance(function, n));
    }

    public static long sequentialSum(long n) {

        return LongStream
                .rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {

        return LongStream
                .rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {

        long sum = 0;
        for (long i = 1; i <= n; i++)
            sum += i;
        return sum;
    }

    public static long measureSumPerformance(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();

        LongStream
                .rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);

        return accumulator.total;

    }

    public static long sideEffectSum(long n) {

        Accumulator accumulator = new Accumulator();
        LongStream
                .rangeClosed(1, n)
                .forEach(accumulator::add);

        return accumulator.total;
    }


    private static class Accumulator {

        private static long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
