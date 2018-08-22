package chp5;


import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.stream.Collectors;

public class TraderTransactionApp {


    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //1
        List<Transaction> sortedTransactionIn2011 = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(t -> (t.getValue())))
                .collect(Collectors.toList());

        System.out.println(sortedTransactionIn2011);


        //2
        List<String> uniqCities = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqCities);


        //3
        List<Trader> traidersFromCambridge = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traidersFromCambridge);


        //4
        transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce(String::concat)
                .ifPresent(System.out::println);


        //5
        transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .filter(city -> city.equals("Milan"))
                .findAny()
                .ifPresent(System.out::println);

        transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        //6
        List<Integer> values = transactions
                .stream()
                .filter((transaction -> transaction.getTrader().getCity().equals("Cambridge")))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        System.out.println(values);


        //7
        transactions
                .stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);

        //8
        transactions
                .stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo)
                .ifPresent(System.out::println);


    }
}
