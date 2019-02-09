package cf.baradist.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class SolutionTest {
    private Solution solution;
    private List<Integer> expected;

    private static Stream<Arguments> createArguments() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(1, 3, 2),
                Arguments.of(2, 1, 3),
                Arguments.of(3, 1, 2),
                Arguments.of(2, 3, 1),
                Arguments.of(3, 2, 1));
    }

    @BeforeEach
    void setUp() {
        solution = new Solution();
        expected = Arrays.asList(1, 2, 3);
    }

    @ParameterizedTest(name = "Run #{index}. Sort nums: [{arguments}]")
    @MethodSource("createArguments")
    void sortThreeNumbers(int x1, int x2, int x3) {
        solution.setIntegers(new ArrayList<Integer>() {{
            add(x1);
            add(x2);
            add(x3);
        }});
        solution.sortThreeNumbers();
        assertIterableEquals(expected, solution.getIntegers(), "Should be sorted");
        solution.print();
    }
}