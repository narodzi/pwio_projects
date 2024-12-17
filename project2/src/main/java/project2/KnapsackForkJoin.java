package project2;

import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class KnapsackForkJoin {
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj pojemnosc plecaka: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Podaj zakres wag przedmiotow oddzielony \"-\": ");
        String range = scanner.nextLine();

        String[] split_weights = range.split("-");

        int wmin = Integer.parseInt(split_weights[0]);
        int wmax = Integer.parseInt(split_weights[1]);

        System.out.print("Podaj zakres wartości przedmiotow oddzielony \"-\": ");
        String vrange = scanner.nextLine();

        String[] split_values = vrange.split("-");

        int vmin = Integer.parseInt(split_values[0]);
        int vmax = Integer.parseInt(split_values[1]);

        System.out.print("Podaj ilość przedmiotow: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        List<StoreItem> storeItems = new ArrayList<>();

        for(int i = 0; i < quantity; i++) {
            storeItems.add(new StoreItem(rnd.nextInt(wmax-wmin) + wmin, rnd.nextInt(vmax-vmin) + vmin));
        }

        System.out.println("Wszystkie przedmioty: " + storeItems.toString());

        List<StoreItem> maxItems = knapsackForkJoin(storeItems, capacity);
        System.out.println("Przedmioty dla maksymalnego zysku: " + StoreItem.itemsToString(maxItems));
        System.out.println("Maksymalna wartosc: " + StoreItem.getCumulativeValue(maxItems));
    }
    
    private static List<StoreItem> knapsackForkJoin(List<StoreItem> storeItems, int capacity) {
        ForkJoinPool pool = new ForkJoinPool();
        KnapsackTask task = new KnapsackTask(storeItems, storeItems.size(), capacity);
        return pool.invoke(task);
    }
}
