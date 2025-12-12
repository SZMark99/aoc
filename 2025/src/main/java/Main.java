import day9.MovieTheater;

public class Main {
    public static void main(String[] args) {
        String text = "7,1\n" +
                "11,1\n" +
                "11,7\n" +
                "9,7\n" +
                "9,5\n" +
                "2,5\n" +
                "2,3\n" +
                "7,3";
        new MovieTheater().read(text);
    }
}