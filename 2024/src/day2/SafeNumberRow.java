package day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SafeNumberRow {
    public void read(String text) {
        int safe = 0;
        for (String line : text.split("\n")) {
            List<String> parts = Arrays.asList(line.split(" "));

            if (isSafe(parts.stream().map(Integer::parseInt).collect(Collectors.toList()), false)) {
                safe++;
            }
        }
        System.out.println(safe);
    }

    private boolean isSafe(List<Integer> parts, boolean remove) {
        boolean decrease = false;
        boolean increase = false;
        for (int i = 1; i < parts.size(); i++) {
            int sub = Math.abs(parts.get(i) - parts.get(i - 1));
            if (sub > 3 || sub == 0 ||
                    decrease && parts.get(i) > parts.get(i - 1) ||
                    increase && parts.get(i) < parts.get(i - 1)) {
                if (!remove) {
                    for (int j = 0; j < parts.size(); j++) {
                        List<Integer> copy = new ArrayList<>(parts);
                        copy.remove(j);
                        if (isSafe(copy, true)) {
                            return true;
                        }
                    }
                }
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
