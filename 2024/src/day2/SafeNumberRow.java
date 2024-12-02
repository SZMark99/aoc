package day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SafeNumberRow {
    public void read(String text) {
        int safe = 0;
        for (String line : text.split("\n")) {
            List<String> parts = Arrays.asList(line.split(" "));

            if (isSafe(parts.stream().map(Integer::parseInt).collect(Collectors.toList()))) {
                safe++;
            }
        }
        System.out.println(safe);
    }

    private boolean isSafe(List<Integer> parts) {
        boolean decrease = false;
        boolean increase = false;
        for (int i = 1; i < parts.size(); i++) {
            int sub = Math.abs(parts.get(i) - parts.get(i - 1));
            if (sub > 3 || sub == 0) {
                return false;
            }
            if (decrease && parts.get(i) > parts.get(i - 1)) {
                return false;
            }
            if (increase && parts.get(i) < parts.get(i - 1)) {
                return false;
            }
            if (parts.get(i) < parts.get(i - 1)) {
                decrease = true;
            } else {
                increase = true;
            }
        }
        return true;
    }
}
