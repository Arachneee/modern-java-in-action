package chapter16;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class chapter16 {
    public static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));;
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(findPrices());
        System.out.println("순차 실행 시간 : " + (System.nanoTime() - start) / 10000);

        start = System.nanoTime();
        System.out.println(findPricesParallel());
        System.out.println("병렬 실행 시간 : " + (System.nanoTime() - start) / 10000);



        start = System.nanoTime();
        System.out.println(findPricesParallelAsync());
        System.out.println("비동기 실행 시간 : " + (System.nanoTime() - start) / 10000);
    }

    //순차 실행
    public static List<String> findPrices() {

        return shops.stream()
                .map(shop -> String.format("%s price is %d",
                        shop.getName(),shop.getPrice()))
                .collect(Collectors.toList());
    }

    //병령 실행
    public static List<String> findPricesParallel() {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %d",
                        shop.getName(),shop.getPrice()))
                .collect(Collectors.toList());
    }

    //비동기 호출
    public static List<String> findPricesParallelAsync() {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %d",
                                shop.getName(),shop.getPrice()), executor))
                .collect(Collectors.toList());

        // stream을 쪼개 줘야 비동기로 동작한다.
        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
