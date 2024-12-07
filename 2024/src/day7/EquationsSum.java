package day7;

import java.util.*;

public class EquationsSum {

    public void read(String text) {
        long sum = 0;
        for (String line : text.split("\n")) {
            String[] parts = line.split(": ");
            List<Integer> list = Arrays.stream(parts[1].split(" "))
                    .map(Integer::parseInt)
                    .toList();
            long result = Long.parseLong(parts[0]);
            if (isEquation(list, result, 0L, 0)) {
                sum += result;
            }
        }
        System.out.println(sum);
    }

    private boolean isEquation(List<Integer> list, long requiredResult, long result, int index) {
       if (list.size() > index) {
           return isEquation(list, requiredResult, result + list.get(index), index + 1) ||
           isEquation(list, requiredResult, result * list.get(index), index + 1);
         }
        return list.size() == index && Objects.equals(result, requiredResult);
    }
}
