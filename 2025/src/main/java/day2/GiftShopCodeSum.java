package day2;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.LongStream;

public class GiftShopCodeSum {
    public static void read(String text) {
        Long sum = Arrays.stream(text.replace("\n", "")
                        .split(","))
                .map(s -> s.split("-"))
                .mapToLong(GiftShopCodeSum::searchReflection)
                .sum();
        System.out.println(sum);
    }

    private static long searchReflection(String[] strings) {
        LongStream stream = LongStream.rangeClosed(
                Long.parseLong(strings[0]),
                Long.parseLong(strings[1])
        );
        return stream.mapToObj(Long::toString)
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
                .mapToLong(Long::parseLong)
                .sum();
    }
}
