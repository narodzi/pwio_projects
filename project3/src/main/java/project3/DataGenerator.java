package project3;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DataGenerator {

    /**
     * Metoda generująca listę pseudolosowych liczb całkowitych.
     *
     * @param size  - liczba elementów do wygenerowania
     * @param bound - zakres górny dla losowanych wartości (0..bound)
     * @return lista liczb całkowitych
     */
    public static List<Integer> generate(int size, int bound) {
        return new Random()
                .ints(size, 0, bound)
                .boxed()
                .collect(Collectors.toList());
    }
}
