package day2;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
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
                .map(number -> {
                    if (isRepeated(number)) {
                        return number;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .mapToLong(Long::parseLong)
                .sum();
    }

    private static boolean isRepeated(String number) {
        IntStream stream = IntStream.rangeClosed(1, number.length()/2);
        return stream
                .filter(i-> number.length()%i==0)
                .anyMatch(repeatLength-> {
                    int repeateTime = number.length() / repeatLength;
                    return IntStream.rangeClosed(1, repeateTime)
                            .allMatch(sectionMultiplier-> allPartEquals(number, repeatLength, sectionMultiplier));
                    
                });
    }

    private static boolean allPartEquals(String number, int repeatLength, int sectionMultiplier) {
        String part = number.substring(0, repeatLength);
        String nextPart = number.substring((sectionMultiplier -1)* repeatLength, sectionMultiplier * repeatLength);
        return part.equals(nextPart);
    }
}
