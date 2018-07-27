package chp3;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfacesUsage {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {

        List<T> results = new ArrayList<T>();
        for (T t : list)
            if (p.test(t))
                results.add(t);

        return results;
    }

    public static <T> void process(List<T> list, Consumer<T> c) {
        for (T t : list)
            c.accept(t);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {

        List<R> result = new ArrayList<R>();
        for (T t: list)
            result.add(f.apply(t));

        return result;
    }


    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("");
        list.add("b");
        list.add("cd");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> noneEmptyStrings = filter(list, nonEmptyStringPredicate);
        System.out.println(noneEmptyStrings);

        Consumer<String> stringPrinter = (String s) -> System.out.println(s);
        process(list, stringPrinter);

        Function<String, Integer> mapStringToLength =  (String s) -> s.length();

        List<Integer> lengths = map(list, mapStringToLength);
        System.out.println(lengths);


    }
}
