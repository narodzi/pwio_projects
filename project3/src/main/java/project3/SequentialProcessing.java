package project3;

import java.util.List;

public class SequentialProcessing {

    private static final int SIZE = 100_000_000;
    private static final int BOUND = 1_000;

    public static void main(String[] args) {
        List<Integer> largeList = DataGenerator.generate(SIZE, BOUND);

        long startSequential = System.nanoTime();

        long countSequential = largeList.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 3)
                .distinct()
                .count();

        long endSequential = System.nanoTime();
        long timeSequential = endSequential - startSequential;

        System.out.println("== SEKWENCYJNE PRZETWARZANIE ==");
        System.out.println("Wynik sekwencyjny (count)  : " + countSequential);
        System.out.println("Czas sekwencyjny           : " + timeSequential / 1_000_000 + " ms");
    }
}
