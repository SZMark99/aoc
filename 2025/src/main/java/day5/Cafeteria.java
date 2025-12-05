package day5;

import java.util.Arrays;
import java.util.List;

public class Cafeteria {
    public static void read(String text) {
        String[] split = text.split("\n\n");
        List<Long[]> ranges = Arrays.stream(split[0].split("\n"))
                .map(line -> {
                    String[] parts = line.split("-");
                    Long[] range = new Long[2];
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    range[0] = start;
                    range[1] = end;
                    return range;
                })
                .toList();
        long total = Arrays.stream(split[1].split("\n"))
                .map(Long::parseLong)
                .filter(o -> isContainsRange(o, ranges))
                .count();
        System.out.println(total);
    }

    private static boolean isContainsRange(Long o, List<Long[]> ranges) {
        return ranges.stream().anyMatch(r -> o >= r[0] && o <= r[1]);
    }
}
