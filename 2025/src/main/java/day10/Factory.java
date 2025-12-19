package day10;

import java.util.*;
import java.util.stream.IntStream;

public class Factory {
    public void read(String text) {
        int sum = Arrays.stream(text.split("\n"))
                .map(this::splitLine)
                .mapToInt(this::getFewestSwiching)
                .sum();
        System.out.println("Sum: " + sum);
    }

    private int getFewestSwiching(Item item) {
        List<Boolean> expected = item.getExpected();
        List<List<Integer>> values = item.getValues();
        List<Boolean> result = expected.stream()
                .map(e -> false)
                .toList();

        return IntStream.rangeClosed(0, values.size() - 1)
                .mapToObj(i -> switching(result, expected, values.subList(i, values.size())))
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    private Integer switching(List<Boolean> result, List<Boolean> expected, List<List<Integer>> values) {
        List<Boolean> newResult = new ArrayList<>(result);
        values.get(0).forEach(i -> newResult.set(i, !result.get(i)));
        if (newResult.equals(expected)) {
            return 1;
        }
        if (values.size() == 1) {
            return null;
        }
        OptionalInt min = IntStream.rangeClosed(1, values.size() - 1)
                .mapToObj(i -> switching(newResult, expected, values.subList(i, values.size())))
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .min();
        if (min.isEmpty()) {
            return null;
        }
        return 1 + min.getAsInt();
    }

    private Item splitLine(String line) {
        String[] parts = line.split("(?<=\\])|(?=\\{)");
        return new Item(getFirstPart(parts[0]), getSecondPart(parts[1]), getThirdPart(parts[2]));
    }

    private List<Integer> getThirdPart(String part) {
        return Arrays.stream(part
                        .replace("{", "")
                        .replace("}", "")
                        .split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private List<List<Integer>> getSecondPart(String part) {
        return Arrays.stream(part
                        .replace("(", "")
                        .replace(")", "")
                        .trim()
                        .split(" "))
                .map(s -> Arrays.stream(s.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }

    private List<Boolean> getFirstPart(String part) {
        return Arrays.stream(part
                        .replace("[", "")
                        .replace("]", "")
                        .split(""))
                .map(s -> s.equals("#"))
                .toList();
    }

    private class Item {
        private final List<Boolean> expected;
        private final List<List<Integer>> values;
        private final List<Integer> results;

        public Item(List<Boolean> expected, List<List<Integer>> values, List<Integer> results) {
            this.expected = expected;
            this.values = values;
            this.results = results;
        }

        public List<Boolean> getExpected() {
            return expected;
        }

        public List<List<Integer>> getValues() {
            return values;
        }

        public List<Integer> getResults() {
            return results;
        }
    }
}
