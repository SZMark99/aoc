package day7;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Laboratories {
    static List<Point> lastSeparators = new ArrayList<>();
    static List<SulyozottPoint> lastLines = new ArrayList<>();
    static int y = 0;

    public static void read(String text) {
        long num = Arrays.stream(text.split("\n"))
                .map(Laboratories::getSeparateRows)
                .mapToLong(Laboratories::getSeparateNumber)
                .sum();

        long totalWeight = lastLines.stream()
                .mapToLong(SulyozottPoint::getSuly)
                .sum();
        System.out.println("Total weight of paths: " + totalWeight);

        System.out.println("Total split lines: " + num);
    }

    private static Row getSeparateRows(String line) {
        Row row = new Row();
        String[] lineSplit = line.split("");
        IntStream.rangeClosed(0, lineSplit.length - 1)
                .forEach(x -> {
                    String point = lineSplit[x];
                    long sum = lastLines.stream()
                            .filter(new SulyozottPoint(x, y-1)::equals)
                            .mapToLong(SulyozottPoint::getSuly)
                            .sum();
                    if (point.equals("S")) {
                        SulyozottPoint startPont = new SulyozottPoint(new Point(x, 0), 1);
                        row.addLine(startPont);
                    } else if (y != 0 && !point.equals("^") && lastLines.contains(new SulyozottPoint(x, y - 1))) {
                        SulyozottPoint p = new SulyozottPoint(new Point(x, y), sum);
                        row.addLine(p);
                    } else if (y != 0 && point.equals("^") && lastLines.contains(new SulyozottPoint(x, y - 1))) {
                        Point p = new Point(x, y);
                        row.addSeparator(p);
                        row.addLine(new SulyozottPoint(new Point(x - 1, y), sum));
                        row.addLine(new SulyozottPoint(new Point(x + 1, y), sum));
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
        List<SulyozottPoint> lines = new ArrayList<>();

        public List<Point> getSeparators() {
            return separators;
        }

        public void addSeparator(Point p) {
            separators.add(p);
        }

        public List<SulyozottPoint> getLines() {
            return lines;
        }

        public void addLine(SulyozottPoint p) {
            lines.add(p);
        }
    }

    private static class SulyozottPoint {
        Point point;
        long suly;

        public SulyozottPoint(int x, int y) {
            this.point = new Point(x, y);
            this.suly = 0;
        }

        public SulyozottPoint(Point point, long suly) {
            this.point = point;
            this.suly = suly;
        }

        public long getSuly() {
            return suly;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SulyozottPoint that = (SulyozottPoint) o;
            return Objects.equals(point, that.point);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(point);
        }
    }
}
