package cf.baradist.task1;

import java.util.Arrays;

public class Vector extends Matrix {
    public Vector() {
        super();
    }

    public Vector(int width) {
        super(width, 1);
    }

    public Vector(Vector other) {
        super(other);
    }

    public Vector(int[] array) {
        super(new int[][]{array});
    }

    public Vector(IMatrix other) {
        super(other);
        if (!(other instanceof Matrix)) {
            throw new MatrixException("Can't cast IMatrix to Vector");
        }
        if (((Matrix) other).getHeight() > 1) {
            throw new MatrixException("Vector's height can't be greater then 1");
        }
    }

    @Override
    public Vector add(IMatrix matrix) throws MatrixException {
        return new Vector(super.add(matrix));
    }

    public double length() {
        double sum = 0;
        for (int i : arrays[0]) {
            sum += Math.pow(i, 2);
        }
        return Math.sqrt(sum);
    }

    public double length1() {
        return Math.sqrt(Arrays.stream(arrays[0])
                .mapToDouble(i -> Math.pow(i, 2D))
                .sum());
    }
}
