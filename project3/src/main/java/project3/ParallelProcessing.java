package project3;

import java.util.List;

public class ParallelProcessing {

    private static final int SIZE = 10_000_000;
    private static final int BOUND = 1_000;

    public static void main(String[] args) {
         System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "16");

        List<Integer> largeList = DataGenerator.generate(SIZE, BOUND);

        long startParallel = System.nanoTime();

        long countParallel = largeList.parallelStream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 3)
                .distinct()
                .count();

        long endParallel = System.nanoTime();
        long timeParallel = endParallel - startParallel;

        System.out.println("== RÓWNOLEGŁE PRZETWARZANIE ==");
        System.out.println("Wynik równoległy (count)    : " + countParallel);
        System.out.println("Czas równoległy             : " + timeParallel / 1_000_000 + " ms");

    }
}
