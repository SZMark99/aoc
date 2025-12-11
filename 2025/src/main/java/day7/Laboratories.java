package day7;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Laboratories {
    static List<Point> lastSeparators = new ArrayList<>();
    static List<Point> lastLines = new ArrayList<>();
    static int y = 0;

    public static void read(String text) {
        long num = Arrays.stream(text.split("\n"))
                .map(Laboratories::getSeparateRows)
                .mapToLong(Laboratories::getSeparateNumber)
                .sum();

        System.out.println("Total split cells: " + num);
    }

    private static Row getSeparateRows(String line) {
        Row row = new Row();
        String[] lineSplit = line.split("");
        IntStream.rangeClosed(0, lineSplit.length - 1)
                .forEach(x -> {
                    String point = lineSplit[x];
                    if (point.equals("S")) {
                        Point startPont = new Point(x, 0);
                        row.addLine(startPont);
                    } else if (y != 0 && !point.equals("^") && lastLines.contains(new Point(x, y - 1))) {
                        Point p = new Point(x, y);
                        row.addLine(p);
                    } else if (y != 0 && point.equals("^") && lastLines.contains(new Point(x, y - 1))) {
                        Point p = new Point(x, y);
                        row.addSeparator(p);
                        row.addLine(new Point(x - 1, y));
                        row.addLine(new Point(x + 1, y));
                    }
                });

        reset(row);
        y++;
        return row;
    }

    private static void reset(Row row) {
        lastSeparators = row.getSeparators();
        lastLines = row.getLines();
    }

    private static long getSeparateNumber(Row line) {
        return line.getSeparators().size();
    }

    private static class Row {
        List<Point> separators = new ArrayList<>();
        List<Point> lines = new ArrayList<>();

        public List<Point> getSeparators() {
            return separators;
        }

        public void addSeparator(Point p) {
            separators.add(p);
        }

        public List<Point> getLines() {
            return lines;
        }

        public void addLine(Point p) {
            lines.add(p);
        }
    }
}
