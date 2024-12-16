package project2;

import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class KnapsackForkJoin {
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj pojemność plecaka: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Podaj zakres wag przedmiotów oddzielony \"-\": ");
        String range = scanner.nextLine();

        String[] split_weights = range.split("-");

        int wmin = Integer.parseInt(split_weights[0]);
        int wmax = Integer.parseInt(split_weights[1]);

        System.out.print("Podaj zakres wartości przedmiotów oddzielony \"-\": ");
        String vrange = scanner.nextLine();

        String[] split_values = vrange.split("-");

        int vmin = Integer.parseInt(split_values[0]);
        int vmax = Integer.parseInt(split_values[1]);

        System.out.print("Podaj ilość przedmiotów: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        List<Integer> weights = new ArrayList<Integer>();
        List<Integer> values = new ArrayList<Integer>();

        for(int i = 0; i < quantity; i++) {
            values.add(rnd.nextInt(vmax-vmin) + vmin);
            weights.add(rnd.nextInt(wmax-wmin) + wmin);
        }

        System.out.println("Wagi: " + weights.toString());
        System.out.println("Wartości: " + values.toString());

        int maxValue = knapsackForkJoin(weights, values, capacity);

        System.out.println("Maksymalna wartość, którą można osiągnąć: " + maxValue);
    }
    
    private static int knapsackForkJoin(List<Integer> weights, List<Integer> values, int capacity) {
        ForkJoinPool pool = new ForkJoinPool();
        KnapsackTask task = new KnapsackTask(weights, values, weights.size(), capacity);
        return pool.invoke(task);
    }
}
