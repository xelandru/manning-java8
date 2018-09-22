package chp11;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chp11.ExchangeService.Money.*;

public class ShopPriceFinder {

    private static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    r -> {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        return t;
                    });

    public List<String> findPricesSync(String product) {

        return shops
                .parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.calculatePrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesAsync(String product) {

        List<CompletableFuture<String>> futurePrices =
                shops
                        .stream()
                        .map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %.2f", shop.getName(), shop.calculatePrice(product)), executor))
                        .collect(Collectors.toList());

        return futurePrices
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesDiscountSync(String product) {

        return shops
                .parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesDiscountAsync(String product) {

        List<CompletableFuture<String>> futurePrices =
                shops
                        .stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                        .collect(Collectors.toList());

        return futurePrices.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    public List<Double> findPricesDiscountExchangeAsync(String product) {

        List<CompletableFuture<Double>> futurePrices = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> Double.parseDouble(shop.getPrice(product)), executor))
                .map(future -> future.thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> ExchangeService.getRate(EUR, USD)),
                        (price, rate) -> price * rate
                ))
                .collect(Collectors.toList());

        return futurePrices.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {

        return shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)))
                ;
    }

    public static void main(String[] args) {
        ShopPriceFinder finder = new ShopPriceFinder();
        long start = System.nanoTime();
       CompletableFuture[] futures =
               finder
                .findPricesStream("iPhone X")
                .map(f -> f.thenAccept(System.out::println))
                       .map(CompletableFuture::join)
        .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Operation lasted " + duration + " milliseconds");
    }
}
