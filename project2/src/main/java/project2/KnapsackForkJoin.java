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

        int min = Integer.parseInt(split_weights[0]);
        int max = Integer.parseInt(split_weights[1]);

        System.out.print("Podaj ilość przedmiotów: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        List<Integer> weights = new ArrayList<Integer>();
        List<Integer> values = new ArrayList<Integer>();

        for(int i = 0; i < quantity; i++) {
            values.add(rnd.nextInt(max) + min);
            weights.add(rnd.nextInt(max) + min);
        }


        System.out.println("Wagi: " + weights.toString());
        System.out.println("Wartości: " + values.toString());

        int maxValue = knapsackForkJoin(weights, values, capacity);

        System.out.println("Maksymalna wartość, którą można osiągnąć: " + maxValue);
    }
    
    public static int knapsackForkJoin(List<Integer> weights, List<Integer> values, int capacity) {
        ForkJoinPool pool = new ForkJoinPool();
        KnapsackTask task = new KnapsackTask(weights, values, weights.size(), capacity);
        return pool.invoke(task);
    }

    static class KnapsackTask extends RecursiveTask<Integer> {
        private final List<Integer> weights;
        private final List<Integer> values;
        private final int n;
        private final int capacity;

        public KnapsackTask(List<Integer> weights, List<Integer> values, int n, int capacity) {
            this.weights = weights;
            this.values = values;
            this.n = n;
            this.capacity = capacity;
        }

        @Override
        protected Integer compute() {
            if (n == 0 || capacity == 0) {
                return 0;
            }

            if (weights.get(n - 1) > capacity) {
                return new KnapsackTask(weights, values, n - 1, capacity).fork().join();
            }

            KnapsackTask withoutItem = new KnapsackTask(weights, values, n - 1, capacity);
            withoutItem.fork();

            KnapsackTask withItem = new KnapsackTask(weights, values, n - 1, capacity - weights.get(n - 1));

            int includeItem = values.get(n - 1) + withItem.compute();
            int excludeItem = withoutItem.join();

            return Math.max(includeItem, excludeItem);
        }
    }
}
