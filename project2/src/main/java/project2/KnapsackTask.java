package project2;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class KnapsackTask extends RecursiveTask<Integer> {
    private final StoreItems items;
    private final int n;
    private final int capacity;

    public KnapsackTask(StoreItems items, int n, int capacity) {
        this.items = items;
        this.n = n;
        this.capacity = capacity;
    }

    @Override
    protected Integer compute() {
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (items.get(n - 1).getWeight() > capacity) {
            return new KnapsackTask(items, n - 1, capacity).fork().join();
        }

        KnapsackTask withoutItem = new KnapsackTask(items, n - 1, capacity);
        withoutItem.fork();

        KnapsackTask withItem = new KnapsackTask(items, n - 1, capacity - items.get(n - 1).getWeight());
        withItem.fork();

        int includeItem = items.get(n - 1).getPrice() + withItem.join();
        int excludeItem = withoutItem.join();

        return Math.max(includeItem, excludeItem);
    }
}
