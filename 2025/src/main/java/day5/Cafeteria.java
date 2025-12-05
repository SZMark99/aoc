package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cafeteria {
    public static void read(String text) {
        String[] split = text.split("\n\n");
        List<Range> ranges = Arrays.stream(split[0].split("\n"))
                .map(line -> {
                    String[] parts = line.split("-");
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    return new Range(start, end);
                })
                .toList();
        List<Range> collectionRanges = new ArrayList<>();
        ranges.stream()
                .forEach(r -> {
                    if (collectionRanges.isEmpty()) {
                        collectionRanges.add(r);
                        return;
                    }
                    Range startInNewRanges = getRange(r.start, collectionRanges);
                    Range endInNewRanges = getRange(r.end, collectionRanges);
                    if (isInNewRanges(startInNewRanges, endInNewRanges)) {
                        return;
                    }
                    if (isMergeNeeded(startInNewRanges, endInNewRanges)) {
                        Range mergedRange = new Range(
                                Math.min(startInNewRanges.start, endInNewRanges.start),
                                Math.max(startInNewRanges.end, endInNewRanges.end)
                        );
                        collectionRanges.remove(startInNewRanges);
                        collectionRanges.remove(endInNewRanges);
                        collectionRanges.add(mergedRange);
                        return;
                    }
                    if (Objects.nonNull(startInNewRanges)) {
                        startInNewRanges.end = Math.max(startInNewRanges.end, r.end);
                        return;
                    }
                    if (Objects.nonNull(endInNewRanges)) {
                        endInNewRanges.start = Math.min(endInNewRanges.start,  r.start);
                        return;
                    }
                    collectionRanges.add(r);

                });

        long total = collectionRanges.stream()
                .filter(range -> !isInOtherRange(range, collectionRanges))
                .mapToLong(r -> r.end - r.start + 1)
                .sum();
        System.out.println(total);
    }

    private static boolean isMergeNeeded(Range startInNewRanges, Range endInNewRanges) {
        return Objects.nonNull(startInNewRanges) && Objects.nonNull(endInNewRanges) && startInNewRanges != endInNewRanges;
    }

    private static boolean isInNewRanges(Range startInNewRanges, Range endInNewRanges) {
        return Objects.nonNull(startInNewRanges) && Objects.nonNull(endInNewRanges) && startInNewRanges == endInNewRanges;
    }

    private static Range getRange(Long start, List<Range> ranges) {
        return ranges.stream()
                .filter(r -> start >= r.start && start <= r.end)
                .findFirst()
                .orElse(null);
    }

    private static boolean isInOtherRange(Range range, List<Range> ranges) {
        return ranges.stream()
                .filter(obj -> !range.equals(obj))
                .anyMatch(r -> range.start >= r.start && range.end <= r.end);
    }

    private static class Range {
        Long start;
        Long end;

        public Range(Long start, Long end) {
            this.start = start;
            this.end = end;
        }
    }
}
