package day2;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class GiftShopCodeSum {
    public static void read(String text) {
        int sum = Arrays.stream(text.replace("\n", "")
                        .split(","))
                .map(s -> s.split("-"))
                .mapToInt(GiftShopCodeSum::searchReflection)
                .sum();
        System.out.println(sum);
    }

    private static int searchReflection(String[] strings) {
        IntStream stream = IntStream.rangeClosed(
                Integer.parseInt(strings[0]),
                Integer.parseInt(strings[1])
        );
        return stream.mapToObj(Integer::toString)
                .filter(number -> number.length() % 2 == 0)
                .map(number -> {
                    String firstHalf = number.substring(0, number.length() / 2);
                    String secondHalf = number.substring(number.length() / 2);
                    if (Objects.equals(firstHalf, secondHalf)) {
                        return number;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
