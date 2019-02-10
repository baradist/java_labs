package cf.baradist.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution solution;

    private static Stream<Arguments> createArguments() {
        return Stream.of(
                Arguments.of(10, 3, Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2, 3))),
                Arguments.of(10, null, new ArrayList<>()),
                Arguments.of(Integer.MAX_VALUE, 3, Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2, 3))),
                Arguments.of(Integer.MIN_VALUE, 1, Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2, 3))),
                Arguments.of(3, 4, Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(4, 5)))
        );
    }

    @ParameterizedTest(name = "Run #{index}. Args: [{arguments}]")
    @MethodSource("createArguments")
    void findTheClosest(Integer findClosestTo, Integer expected, List<List<Integer>> arrays) {
        solution = new Solution(findClosestTo);
        solution.setArrays(arrays);
        solution.findTheClosest();
        assertEquals(expected, solution.getTheClosest().getKey());
        solution.printTheClosest();
    }
}
