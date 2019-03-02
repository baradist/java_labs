package cf.baradist.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {

    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("tmp", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    void demo() throws IOException {
        new Matrix(4, 2).output();
        new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}}).output();
        Matrix matrix = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        matrix.write("out.txt");
        Matrix read = new Matrix();
        read.read("out.txt");
        read.output();
    }

    @Test
    void add() {
        IMatrix matrix = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        matrix = matrix.add(new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}}));
        Matrix expected = new Matrix(new int[][]{{2, 4, 6}, {8, 10, 12}});
        expected.output();
        assertEquals(expected, matrix);
    }

    @Test
    void writeRead() {
        Matrix expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        expected.write(tempFile);
        Matrix read = new Matrix();
        read.read(tempFile);
        assertEquals(expected, read);
    }

    @Test
    void multiply() {
        Matrix expected = new Matrix(new int[][]{{58, 64}, {139, 154}});
        Matrix actual = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}})
                .multiply(new Matrix(new int[][]{{7, 8}, {9, 10}, {11, 12}}));
        actual.output();
        assertEquals(expected, actual);
    }
}