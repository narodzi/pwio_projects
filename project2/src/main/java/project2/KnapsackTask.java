package project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class KnapsackTask extends RecursiveTask<List<StoreItem>> {
    private final List<StoreItem> items;
    private final int n;
    private final int capacity;

    public KnapsackTask(List<StoreItem> items, int n, int capacity) {
        this.items = items;
        this.n = n;
        this.capacity = capacity;
    }

    @Override
    protected List<StoreItem> compute() {
        if (n == 0 || capacity == 0) {
            return new ArrayList<>();
        }

        if (items.get(n - 1).getWeight() > capacity) {
            return new KnapsackTask(items, n - 1, capacity).fork().join();
        }

        KnapsackTask withoutItem = new KnapsackTask(items, n - 1, capacity);
        withoutItem.fork();

        KnapsackTask withItem = new KnapsackTask(items, n - 1, capacity - items.get(n - 1).getWeight());
        withItem.fork();

        List<StoreItem> includeItem = withItem.join();
        includeItem.add(items.get(n - 1));
        List<StoreItem> excludeItem = withoutItem.join();
        if(StoreItem.getCumulativeValue(includeItem) > StoreItem.getCumulativeValue(excludeItem))
            return includeItem;
        else
            return excludeItem;
    }
}
