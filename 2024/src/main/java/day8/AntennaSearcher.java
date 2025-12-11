package day8;

import java.util.*;

public class AntennaSearcher {
    Map<String, List<Coordinate>> antennas = new HashMap<>();
    Set<Coordinate> antinode = new HashSet<>();
    int lengthX = 0;
    int lengthY = 0;

    public void read(String text) {
        int y = 0;
        int x = 0;
        for (String line : text.split("\n")) {
            x = 0;
            String[] row = line.split("");
            for (String c : row) {
                if (!Objects.equals(c, ".")) {
                    antennas.computeIfAbsent(c, coordinates -> new ArrayList<>()).add(new Coordinate(x, y));
                }
                x++;
            }
            y++;
        }
        lengthX = x;
        lengthY = y;
        antinodeCounter();
        System.out.println(antinode.size());
    }

    private void antinodeCounter() {
        for (List<Coordinate> coordinates : antennas.values()) {
            antinodeCountByFrequency(coordinates, 0);
        }
    }

    private void antinodeCountByFrequency(List<Coordinate> coordinates, int index) {
        if (index == coordinates.size()) {
            return;
        }
        for (int i = index + 1; i < coordinates.size(); i++) {
            int xDist = coordinates.get(index).x - coordinates.get(i).x;
            int yDist = Math.abs(coordinates.get(index).y - coordinates.get(i).y);
            if (coordinates.get(i).y + yDist < lengthY) {
                if (xDist >= 0 && coordinates.get(i).x - xDist < lengthX) {
                    antinode.add(new Coordinate(coordinates.get(i).x - xDist, coordinates.get(i).y + yDist));
                } else if (xDist <= 0 && coordinates.get(i).x + xDist >= 0) {
                    antinode.add(new Coordinate(coordinates.get(i).x + xDist, coordinates.get(i).y + yDist));
                }
            }
            if (coordinates.get(index).y - yDist >= 0) {
                if (xDist >= 0 && coordinates.get(index).x + xDist < lengthX) {
                    antinode.add(new Coordinate(coordinates.get(index).x + xDist, coordinates.get(index).y - yDist));
                } else if (xDist <= 0 && coordinates.get(index).x - xDist >= 0) {
                    antinode.add(new Coordinate(coordinates.get(index).x - xDist, coordinates.get(index).y - yDist));
                }
            }
        }
        antinodeCountByFrequency(coordinates, index + 1);
    }
}
