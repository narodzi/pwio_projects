package project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinMergeSort {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            list.add(new Random().nextInt(10000));
        }

        System.out.println("Przed sortowaniem:");
        System.out.println(list);

        ForkJoinPool pool = new ForkJoinPool();
        list = pool.invoke(new MergeSortTask(list));

        System.out.println("Po sortowaniu:");
        System.out.println(list);
    }

    static class MergeSortTask extends RecursiveTask<ArrayList<Integer>> {
        private final ArrayList<Integer> list;

        MergeSortTask(ArrayList<Integer> list) {
            this.list = list;
        }

        @Override
        protected ArrayList<Integer> compute() {
            if (list.size() <= 1) {
                return list;
            }

            int mid = list.size() / 2;
            ArrayList<Integer> left = new ArrayList<>(list.subList(0, mid));
            ArrayList<Integer> right = new ArrayList<>(list.subList(mid, list.size()));

            MergeSortTask leftTask = new MergeSortTask(left);
            MergeSortTask rightTask = new MergeSortTask(right);

            invokeAll(leftTask, rightTask);
            return merge(leftTask.join(), rightTask.join());
        }

        private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
            ArrayList<Integer> merged = new ArrayList<>();
            int i = 0, j = 0;

            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    merged.add(left.get(i++));
                } else {
                    merged.add(right.get(j++));
                }
            }

            while (i < left.size()) {
                merged.add(left.get(i++));
            }

            while (j < right.size()) {
                merged.add(right.get(j++));
            }

            return merged;
        }
    }
}
