package day4;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintingDepartment {
    private static int x = 0;
    private static int y = 0;
    private final static List<Point> points = new ArrayList<>();

    public static void read(String text) {
        Arrays.stream(text.split("\n"))
                .forEach(PrintingDepartment::addPoint);
        long count = getCount();
        System.out.println(count);
    }

    private static long getCount() {
        y = 0;
        List<Point> removePoints = new ArrayList<>();
        long count = points.stream()
                .filter(PrintingDepartment::isFewerThanFourNeighbors)
                .peek(removePoints::add)
                .count();
        points.removeAll(removePoints);
        if (count > 0) {
            count += getCount();
        }
        return count;
    }

    private static boolean isFewerThanFourNeighbors(Point p) {
        int neighbors = getNeighbors(p);
        return neighbors < 4;
    }

    private static int getNeighbors(Point p) {
        int neighbors = 0;
        if (points.contains(new Point(p.x + 1, p.y))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x + 1, p.y + 1))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x + 1, p.y - 1))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x, p.y + 1))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x, p.y - 1))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x - 1, p.y))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x - 1, p.y + 1))) {
            neighbors++;
        }
        if (points.contains(new Point(p.x - 1, p.y - 1))) {
            neighbors++;
        }
        return neighbors;
    }

    private static void addPoint(String line) {
        x = 0;
        Arrays.stream(line.split(""))
                .forEach(c -> {
                    if (c.equals("@")) {
                        points.add(new Point(x, y));
                    }
                    x++;
                });
        y++;
    }
}
