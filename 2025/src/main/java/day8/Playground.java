package day8;

import java.util.*;
import java.util.stream.IntStream;

public class Playground {
    List<Set<Point>> connectedPoints = new ArrayList<>();
    List<Point> points = new ArrayList<>();

    public void read(String text) {
        Arrays.stream(text.split("\n"))
                .map(line -> line.split(","))
                .map(parts -> new Point(
                        Long.parseLong(parts[0]),
                        Long.parseLong(parts[1]),
                        Long.parseLong(parts[2])
                ))
                .forEach(p -> points.add(p));
        List<PointDistance> distances = createDistances();
        distances.sort(Comparator.comparingDouble(a -> a.distance));

        IntStream.rangeClosed(0, distances.size() - 1)
                .forEach(i -> {
                    PointDistance pointDistance = distances.get(i);
                    if (connectedPoints.size() == 1 && connectedPoints.get(0).size() == points.size()) {
                        return;
                    }
                    boolean point1Connected = connectedPoints.stream()
                            .anyMatch(p -> p.contains(pointDistance.point1));
                    boolean point2Connected = connectedPoints.stream()
                            .anyMatch(p -> p.contains(pointDistance.point2));
                    Set<Point> newConnection = new HashSet<>();

                    if (!point1Connected && !point2Connected) {
                        newConnection.add(pointDistance.point1);
                        newConnection.add(pointDistance.point2);
                        connectedPoints.add(newConnection);
                    } else if (point1Connected && point2Connected) {
                        connectedPoints.stream()
                                .filter(p -> p.contains(pointDistance.point1))
                                .forEach(p -> p.addAll(connectedPoints.stream()
                                        .filter(p1 -> p1.contains(pointDistance.point2)).findFirst().orElse(new HashSet<>())));
                        connectedPoints.removeIf(p -> p.contains(pointDistance.point2) && !p.contains(pointDistance.point1));
                    } else if (point1Connected) {
                        connectedPoints.stream()
                                .filter(p -> p.contains(pointDistance.point1))
                                .forEach(p -> p.add(pointDistance.point2));
                    } else {
                        connectedPoints.stream()
                                .filter(p -> p.contains(pointDistance.point2))
                                .forEach(p -> p.add(pointDistance.point1));
                    }

                    if (connectedPoints.size() == 1 && connectedPoints.get(0).size() == points.size()) {
                        System.out.println("Closest connection to wall: " + pointDistance.point1.x * pointDistance.point2.x);
                    }
                });
    }

    private List<PointDistance> createDistances() {
        List<PointDistance> pointDistances = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point point1 = points.get(i);
                Point point2 = points.get(j);
                double distance = tavolsag(point1, point2);
                pointDistances.add(new PointDistance(point1, point2, distance));
            }
        }
        return pointDistances;
    }

    private double tavolsag(Point point1, Point point2) {
        long dx = point1.x - point2.x;
        long dy = point1.y - point2.y;
        long dz = point1.z - point2.z;
        return Math.round(Math.sqrt(dx * dx + dy * dy + dz * dz));
    }

    private class PointDistance {
        Point point1;
        Point point2;
        double distance;

        public PointDistance(Point point1, Point point2, double distance) {
            this.point1 = point1;
            this.point2 = point2;
            this.distance = distance;
        }
    }

    private class Point {
        long x;
        long y;
        long z;

        public Point(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
