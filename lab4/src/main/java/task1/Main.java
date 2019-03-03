package task1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static final String IN_FILE_NAME = "lab4/src/main/resources/in.txt";
    public static final String OUT_FILE_NAME = "lab4/out/out.txt";

    public static void main(String[] args) throws IOException {
        new Solution().readRealNumbers(new FileReader(IN_FILE_NAME), new FileWriter(OUT_FILE_NAME));
    }
}
