package day6;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class GuardMover {
    int direction = 0; // 0 = north, 1 = east, 2 = south, 3 = west
    String[][] rows;

    public void read(String text) {
        rows = Stream.of(text.split("\n"))
                .map(row -> row.split(""))
                .toArray(String[][]::new);
        for (int y = 0; y < rows.length; y++) {
            String[] row = rows[y];
            for (int x = 0; x < row.length; x++) {
                String c = row[x];
                if (Objects.equals(c, ".") || Objects.equals(c, "#")) {
                    continue;
                }
                setDirection(c);
                rows[y][x] = "X";
                move(x, y);
                xSum();
                return;
            }
        }
    }

    private void xSum() {
        System.out.println(Arrays.stream(rows)
                .flatMap(Arrays::stream)
                .filter("X"::equals)
                .count());
    }

    private void move(int x, int y) {
        while (true) {
            if (direction == 0) {
                y--;
            } else if (direction == 1) {
                x++;
            } else if (direction == 2) {
                y++;
            } else if (direction == 3) {
                x--;
            }
            if (y < 0 || y >= rows.length || x < 0 || x >= rows[y].length) {
                break;
            }
            if (Objects.equals(rows[y][x], "#")) {
                if (direction == 0) {
                    y++;
                } else if (direction == 1) {
                    x--;
                } else if (direction == 2) {
                    y--;
                } else if (direction == 3) {
                    x++;
                }
                directionNext();
            }
            rows[y][x] = "X";
        }
    }

    private void directionNext() {
        direction++;
        if (direction > 3) {
            direction = 0;
        }
    }

    private void setDirection(String c) {
        if (Objects.equals(c, "^")) {
            direction = 0;
        } else if (Objects.equals(c, ">")) {
            direction = 1;
        } else if (Objects.equals(c, "v")) {
            direction = 2;
        } else if (Objects.equals(c, "<")) {
            direction = 3;
        }
    }
}
