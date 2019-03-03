package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution solution;

    private static Stream<Arguments> createArguments() {
        return Stream.of(
                Arguments.of("2.0 3.0", "one 2  3.0 four"),
                Arguments.of("1.0", "1"),
                Arguments.of("", "one two three"),
                Arguments.of("1.0 2.0 3.0", "1 2 3"),
                Arguments.of("", ""));
    }

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @ParameterizedTest(name = "Run #{index}. Args: [{arguments}]")
    @MethodSource("createArguments")
    @DisplayName("Read real numbers")
    void readRealNumbers(String expected, String string) throws IOException {
        StringWriter out = new StringWriter();
        solution.readRealNumbers(new StringReader(string), out);
        assertEquals(expected, out.toString().trim());
    }
}