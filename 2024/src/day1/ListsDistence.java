package day1;

import java.util.ArrayList;
import java.util.List;

public class ListsDistence {
    public List<Integer> getDistances(List<Integer> leftList, List<Integer> rightList) {
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < leftList.size(); i++) {
            distances.add(Math.abs(rightList.get(i) - leftList.get(i)));
        }
        return distances;
    }
}
