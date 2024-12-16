package project2;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class KnapsackTask extends RecursiveTask<Integer> {
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
            return new KnapsackTask(weights, values, n - 1, capacity).invoke();
        }

        KnapsackTask withoutItem = new KnapsackTask(weights, values, n - 1, capacity);
        withoutItem.fork();

        KnapsackTask withItem = new KnapsackTask(weights, values, n - 1, capacity - weights.get(n - 1));
        withItem.fork();

        int includeItem = values.get(n - 1) + withItem.join();
        int excludeItem = withoutItem.join();

        return Math.max(includeItem, excludeItem);
    }
}
