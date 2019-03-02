package cf.baradist.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix implements IMatrix {
    protected int width;
    protected int height;
    protected int[][] arrays;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int width, int height) {
        initMatrixBySize(width, height);
    }

    public Matrix(IMatrix otherIMatrix) {
        Matrix other = getMatrixThrowIfWrongType(otherIMatrix);
        initMatrixBySize(other.getWidth(), other.getHeight());
        for (int i = 0; i < other.getHeight(); i++) {
            for (int j = 0; j < other.getWidth(); j++) {
                set(i, j, other.get(i, j));
            }
        }
    }

    public Matrix(int[][] arrays) {
        if (arrays == null) {
            throw new MatrixException("Argument can't be null");
        }
        if (arrays.length == 0) {
            throw new MatrixException("Height should be greater then 0");
        }
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].length != arrays[0].length) {
                throw new MatrixException("Width should be the same");
            }
        }
        this.height = arrays.length;
        this.width = arrays[0].length;
        this.arrays = arrays;
    }

    private static Matrix getMatrixThrowIfWrongType(IMatrix otherIMatrix) {
        if (!(otherIMatrix instanceof Matrix)) {
            throw new MatrixException("Can't cast to Matrix: " + otherIMatrix);
        }
        return (Matrix) otherIMatrix;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initMatrixBySize(int width, int height) {
        if (width < 0 || height < 0) {
            throw new MatrixException("Sizes can't be negative");
        }
        this.width = width;
        this.height = height;
        arrays = new int[height][width];
    }

    @Override
    public int get(int i, int j) throws MatrixException {
        return arrays[i][j];
    }

    @Override
    public void output() {
        for (int[] ints : arrays) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public IMatrix add(IMatrix otherIMatrix) throws MatrixException {
        Matrix other = getMatrixThrowIfWrongType(otherIMatrix);
        if (width != other.getWidth() || height != other.getHeight()) {
            throw new MatrixException("Sizes should be the same");
        }
        Matrix result = new Matrix(this);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.arrays[i][j] += other.get(i, j);
            }
        }
        return result;
    }

    @Override
    public boolean equals(IMatrix other) throws MatrixException {
        if (this == other) {
            return true;
        }
        Matrix that = (Matrix) other;
        for (int i = 0; i < arrays.length; i++) {
            if (!Arrays.equals(arrays[i], that.arrays[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void write(String fileName) {
        write(new File(fileName));
    }

    public void write(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.append(String.valueOf(width))
                    .append('\t')
                    .append(String.valueOf(height))
                    .append('\n');
            for (int[] ints : arrays) {
                for (int i : ints) {
                    writer.append(String.valueOf(i))
                            .append('\t');
                }
                writer.append('\n');
            }
        } catch (IOException e) {
            throw new MatrixException("Can't write to a file " + file.getName(), e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) {
            return false;
        }
        return equals((Matrix) obj);
    }

    @Override
    public int set(int i, int j, int value) throws MatrixException {
        if (i < 0 || i >= height
                || j < 0 || j >= width) {
            throw new MatrixException("Arguments out of bound: i=" + i + ", j=" + j);
        }
        return arrays[i][j] = value;
    }

    public IMatrix read(File file) {
        try (Scanner scanner = new Scanner(file)) {
            width = scanner.nextInt();
            height = scanner.nextInt();
            arrays = new int[height][];
            for (int i = 0; i < height; i++) {
                arrays[i] = new int[width];
                for (int j = 0; j < width; j++) {
                    arrays[i][j] = scanner.nextInt();
                }
            }
        } catch (IOException e) {
            throw new MatrixException("Can't read a matrix from a file " + file.getName(), e);
        }
        return this;
    }

    @Override
    public IMatrix read(String file) {
        return read(new File(file));
    }

    public Matrix multiply(IMatrix otherIMatrix) {
        Matrix other = getMatrixThrowIfWrongType(otherIMatrix);
        if (width != other.getHeight()) {
            throw new MatrixException("Height of an argument-matrix must be equal to width");
        }
        Matrix result = new Matrix(other.getWidth(), getHeight());
        int n;
        for (int lY = 0; lY < getHeight(); lY++) {
            for (int rX = 0; rX < other.getWidth(); rX++) {
                n = 0;
                for (int i = 0; i < width; i++) {
                    n += get(lY, i) * other.get(i, rX);
                }
                result.set(lY, rX, n);
            }
        }
        return result;
    }
}
