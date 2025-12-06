package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TrashComparator {
    static int y = 0;
    static List<Column> columns = new ArrayList<>();

    public static void read(String text) {
        Arrays.stream(text.split("\n"))
                .forEach(TrashComparator::readColumns);

        long sum = columns.stream()
                .mapToLong(TrashComparator::operationsCalculator)
                .sum();
        System.out.println("Operations Sum: " + sum);
    }

    private static long operationsCalculator(Column column) {
        return column.values.stream()
                .mapToLong(Long::parseLong)
                .reduce((a, b) -> {
                    if (column.operator.equals("*")) {
                        return a * b;
                    } else {
                        return a + b;
                    }
                }).orElse(0L);
    }

    private static void readColumns(String line) {
        String[] numbers = line.trim().split("\\s+");
        IntStream.rangeClosed(0, numbers.length - 1)
                .forEach(x -> {
                    String number = numbers[x];
                    if (y == 0) {
                        Column e = new Column(x);
                        e.values.add(number);
                        columns.add(e);
                        return;
                    }
                    if (number.equals("*") || number.equals("+")) {
                        columns.get(x).setOperator(number);
                        return;
                    }
                    columns.get(x).values.add(number);
                });
        y++;
    }

    private static class Column {
        int colNumber;
        List<String> values;
        String operator;

        public Column(int colNumber) {
            this.colNumber = colNumber;
            this.values = new ArrayList<>();
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }
}
