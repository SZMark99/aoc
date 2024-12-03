package day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiplySum {

    public void read(String text) {
        int sum = 0;
        boolean enable = true;
        Pattern pattern = Pattern.compile("mul\\((\\d+,\\d+)\\)|don't|do");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (matcher.group().equals("don't")) {
                enable = false;
            } else if (matcher.group().equals("do")) {
                enable = true;
            } else if (enable) {
                String[] parts = matcher.group(1).split(",");
                sum += Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
            }
        }
        System.out.println(sum);
    }
}
