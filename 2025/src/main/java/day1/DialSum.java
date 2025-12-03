package day1;

public class DialSum {
    public static void read(String text) {
        int sum = 0;
        int dial = 50;
        for (String line : text.split("\n")) {
            int number = Integer.parseInt(line.substring(1));
            int oldDial = dial;
            if (number >= 100) {
                sum += number / 100;
                number = number % 100;
            }
            if (line.charAt(0) == 'L') {
                dial -= number;
                if (dial < 0) {
                    dial += 100;
                    if (oldDial % 100 != 0) {
                        sum++;
                    }
                }
            } else if (line.charAt(0) == 'R') {
                dial += number;
                if (dial > 100) {
                    dial -= 100;
                    if (oldDial % 100 != 0) {
                        sum++;
                    }
                }
            }
            if (dial % 100 == 0) {
                sum++;
                dial = 0;
            }
        }
        System.out.println(sum);
    }
}
