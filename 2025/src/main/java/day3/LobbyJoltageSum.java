package day3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LobbyJoltageSum {
    static int maxIndex = 0;

    public static void read(String text) {
        double sum = Arrays.stream(text.split("\n"))
                .mapToDouble(LobbyJoltageSum::getMaxJoltage)
                .sum();
        System.out.println("Joltage Sum: " + (long) sum);
    }

    private static double getMaxJoltage(String line) {
        maxIndex = 0;
        double sum = IntStream.rangeClosed(1, 12)
                .mapToDouble(i -> {
                    int max = getMax(line, i);
                    String substring = line.substring(maxIndex + plusOne(i));
                    maxIndex += substring.indexOf((char) (max + '0')) + plusOne(i);
                    return max * Math.pow(10, 12 - i);
                })
                .sum();
        System.out.println("maxJoltage: " + (long) sum);
        return sum;
    }

    private static int plusOne(int i) {
        return i == 1 ? 0 : 1;
    }

    private static int getMax(String line, int i) {
        return Arrays.stream(line.substring(maxIndex + plusOne(i), line.length() - 12 + i)
                        .split(""))
                .mapToInt(Integer::parseInt)
                .max().orElse(0);
    }
}
