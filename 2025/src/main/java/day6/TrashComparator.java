package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TrashComparator {
    static int y = 0;
    static List<Column> columns = new ArrayList<>();

    public static void read(String text) {
        String[] rows = text.split("\n");
        String lastRow = rows[rows.length - 1];
        int[] colCounts = IntStream.rangeClosed(1, lastRow.length()-1)
                .filter(s -> lastRow.charAt(s) != ' ')
                .toArray();
        Arrays.stream(rows)
                .forEach(line -> readColumns(line, colCounts));

        long sum = columns.stream()
                .mapToLong(TrashComparator::operationsCalculator)
                .sum();
        System.out.println("Operations Sum: " + sum);
    }

    private static long operationsCalculator(Column column) {
        return column.values.stream()
                .map(StringBuilder::toString)
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .reduce((a, b) -> {
                    if (column.operator.equals("*")) {
                        return a * b;
                    } else {
                        return a + b;
                    }
                }).orElse(0L);
    }

    private static void readColumns(String line, int[] colCounts) {
        int prevIndex = 0;
        List<String> numbers = new ArrayList<>();
        for (int colCount : colCounts) {
            String number = line.substring(prevIndex, colCount-1);
            numbers.add(number);
            prevIndex = colCount;
        }
        IntStream.rangeClosed(0, numbers.size() - 1)
                .forEach(x -> {
                    String number = numbers.get(x);

                    if (y == 0) {
                        Column col = new Column(x);
                        IntStream.rangeClosed(0, number.length() - 1)
                                .forEach(i -> col.values.add(new StringBuilder(number.charAt(i) + "")));
                        columns.add(col);
                        return;
                    }
                    if (number.trim().equals("*") || number.trim().equals("+")) {
                        columns.get(x).setOperator(number.trim());
                        return;
                    }
                    Column column = columns.get(x);
                    IntStream.rangeClosed(0, number.length() - 1)
                            .forEach(i -> column.values.get(i).append(number.charAt(i)));
                });
        y++;
    }

    private static class Column {
        int colNumber;
        List<StringBuilder> values;
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
