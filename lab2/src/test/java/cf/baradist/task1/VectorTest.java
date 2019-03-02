package cf.baradist.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("tmp", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    void add() {
        Vector vector = new Vector(new int[]{1, 2, 3});
        vector = vector.add(new Vector(new int[]{1, 2, 3}));
        Vector expected = new Vector(new int[]{2, 4, 6});
        expected.output();
        assertEquals(expected, vector);
    }

    @Test
    void writeRead() {
        Vector expected = new Vector(new int[]{1, 2, 3});
        expected.write(tempFile);
        Vector read = new Vector();
        read.read(tempFile);
        assertEquals(expected, read);
    }

    @Test
    void length() {
        Vector vector = new Vector(new int[]{1, 2, 3});
        assertEquals(3.741657, vector.length(), 0.000001);
    }

    @Test
    void length1() {
        Vector vector = new Vector(new int[]{1, 2, 3});
        assertEquals(3.741657, vector.length1(), 0.000001);
    }

    @Test
    void multiply() {
        Matrix actual = new Vector(new int[]{1, 2, 3})
                .multiply(new Matrix(new int[][]{{4}, {5}, {6}}));
        actual.output();
        assertEquals(32, actual.get(0, 0));
    }
}
