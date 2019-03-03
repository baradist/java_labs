package task1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class Solution {

    public void readRealNumbers(Reader in, Writer out) throws IOException {
        try (Scanner scanner = new Scanner(in);
             BufferedWriter br = new BufferedWriter(out)) {
            while (scanner.hasNext()) {
                try {
                    br.write(String.valueOf(Double.valueOf(scanner.next())));
                    br.write(' ');
                } catch (NumberFormatException e) {
                }
            }
        }
    }
}
