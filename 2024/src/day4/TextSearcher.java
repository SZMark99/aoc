package day4;

import java.util.List;
import java.util.regex.Pattern;

public class TextSearcher {
    String searchedText = "XMAS";
    String reverseSearchedText = new StringBuffer(searchedText).reverse().toString();

    public void read(String text) {
        List<String> rows = List.of(text.split("\n"));
        int sum = 0;
        int rowLength = rows.get(0).length();
        for (int rowNumber = 0; rowNumber < rows.size(); rowNumber++) {
            sum += (int) Pattern.compile(searchedText).matcher(rows.get(rowNumber)).results().count();
            sum += (int) Pattern.compile(reverseSearchedText).matcher(rows.get(rowNumber)).results().count();
            if (!(rowNumber + 3 < rows.size())) {
                continue;
            }
            for (int i = 0; i < rows.get(rowNumber).length(); i++) {
                char c = rows.get(rowNumber).charAt(i);
                if (c != searchedText.charAt(0) && c != reverseSearchedText.charAt(0)) {
                    continue;
                }
                sum += getDiagonalXmas(rows, rowNumber, i, rowLength);
                sum += getVerticalXmas(rows, rowNumber, i);
            }
        }
        System.out.println(sum);
    }

    private int getDiagonalXmas(List<String> rows, int rowNumber, int i, int rowLength) {
        int n = 0;
        if (i + 3 < rowLength && isDiagonalMatch(searchedText, rows, rowNumber, i, true)) {
            n++;
        }
        if (i + 3 < rowLength && isDiagonalMatch(reverseSearchedText, rows, rowNumber, i, true)) {
            n++;
        }
        if (i > 2 && isDiagonalMatch(searchedText, rows, rowNumber, i, false)) {
            n++;
        }
        if (i > 2 && isDiagonalMatch(reverseSearchedText, rows, rowNumber, i, false)) {
            n++;
        }
        return n;
    }

    private boolean isDiagonalMatch(String searchedText, List<String> rows, int row, int col, boolean right) {
        for (int i = 0; i < searchedText.length(); i++) {
            int next = getNext(col, i, right);
            if (rows.get(row + i).charAt(next) !=
                    searchedText.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private int getNext(int col, int i, boolean right) {
        if (right) {
            return col + i;
        }
        return col - i;
    }

    private int getVerticalXmas(List<String> rows, int rowNumber, int i) {
        int n = 0;
        if (isVerticalMatch(searchedText, rows, rowNumber, i)) {
            n++;
        }
        if (isVerticalMatch(reverseSearchedText, rows, rowNumber, i)) {
            n++;
        }
        return n;
    }
    private boolean isVerticalMatch(String searchedText, List<String> rows, int row, int col) {
        for (int i = 0; i < searchedText.length(); i++) {
            if (rows.get(row + i).charAt(col) !=
                    searchedText.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
