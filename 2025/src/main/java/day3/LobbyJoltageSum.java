package day3;

import java.util.Arrays;

public class LobbyJoltageSum {
    public static void read(String text) {
        long sum = Arrays.stream(text.split("\n"))
                .mapToLong(LobbyJoltageSum::getMaxJoltage)
                .sum();
        System.out.println("Joltage Sum: " + sum);
    }

    private static int getMaxJoltage(String line) {
        int max = Arrays.stream(line.substring(0, line.length() - 1).split("")).mapToInt(Integer::parseInt).max().orElse(0);
        int maxIndex = line.indexOf((char) (max + '0'));
        int max2 = Arrays.stream(line.substring(maxIndex + 1).split("")).mapToInt(Integer::parseInt).max().orElse(0);
        return max * 10 + max2;
    }
}
