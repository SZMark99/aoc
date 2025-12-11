package day1;

import java.util.ArrayList;
import java.util.List;

public class DistanceSum {
    private final ListsDistence listsDistence = new ListsDistence();
    private void sum(List<Integer> distances) {
        int sum = 0;
        for (int distance : distances) {
            sum += distance;
        }
        System.out.println("Sum: " + sum);
    }

    private void reader(String text) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (String row : text.split("\n")) {
            String[] strings = row.split("   ");
            leftList.add(Integer.parseInt(strings[0]));
            rightList.add(Integer.parseInt(strings[1]));
        }
        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);
        sum(listsDistence.getDistances(leftList, rightList));
    }

}
