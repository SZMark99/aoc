package day9;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovieTheater {
    public void read(String text) {
        List<Point> points = Arrays.stream(text.split("\n"))
                .map(line -> line.split(","))
                .map(parts -> new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .collect(Collectors.toList());
        long largestArea = getLargestArea(points);
        System.out.println("Largest area: " + largestArea);
    }

    private long getLargestArea(List<Point> points) {
        long largestArea = 0L;
        for (int first = 0; first <= points.size() - 2; first++) {
            for (int second = first + 1; second <= points.size() - 1; second++) {
                long area = getArea(points, first, second);
                if (area > largestArea) {
                    largestArea = area;
                }
            }
        }
        return largestArea;
    }

    private static long getArea(List<Point> points, int first, int second) {
        return (long) (Math.abs(points.get(first).x - points.get(second).x)+1) *
                (Math.abs(points.get(first).y - points.get(second).y)+1);
    }
}
