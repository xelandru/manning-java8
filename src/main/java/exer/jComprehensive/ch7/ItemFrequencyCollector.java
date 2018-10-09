package exer.jComprehensive.ch7;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class ItemFrequencyCollector<T> implements Collector<T, Map<T, Long>, Map<T, Long>> {

    @Override
    public Supplier<Map<T, Long>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Long>, T> accumulator() {
        return (map, key) -> {
            if (map.containsKey(key))
                map.replace(key, map.get(key) + 1);
            else
                map.put(key, 1L);
        };
    }

    @Override
    public BinaryOperator<Map<T, Long>> combiner() {
        return (map, x) -> {
            throw new UnsupportedOperationException("No Parallel implementation");
        };
    }

    @Override
    public Function<Map<T, Long>, Map<T, Long>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(UNORDERED, IDENTITY_FINISH));
    }
}
