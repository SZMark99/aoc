package day5;

import java.util.*;

public class OrderRule {
    Map<Integer, List<Integer>> rules = new HashMap<>();

    public void read(String text) {
        String[] section = text.split("\n\n");
        for (String rule : section[0].split("\n")) {
            String[] r = rule.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(r[0]), o -> new ArrayList<>()).add(Integer.parseInt(r[1]));
        }
        System.out.println(getCorrectOrdersMiddelSum(section[1].split("\n")));
    }

    private int getCorrectOrdersMiddelSum(String[] split) {
        int sum = 0;
        int incorrect = 0;
        for (String s : split) {
            String[] row = s.split(",");
            if (!isCorrectOrder(row)) {
                incorrect += Integer.parseInt(row[(row.length - 1) / 2]);
                continue;
            }
            sum += Integer.parseInt(row[(row.length - 1) / 2]);
        }
        System.out.println("Incorrect: " + incorrect);
        return sum;
    }

    private boolean isCorrectOrder(String[] row) {
        for (int i = 0; i < row.length; i++) {
            List<Integer> rule = rules.get(Integer.parseInt(row[i]));
            if (rule != null) {
                for (int j = 0; j < i; j++) {
                    if (rule.contains(Integer.parseInt(row[j]))) {
                        orderRow(row, i, j);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void orderRow(String[] row, int i, int j) {
        String temp = row[i];
        row[i] = row[j];
        row[j] = temp;
        isCorrectOrder(row);
    }
}
