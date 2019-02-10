package cf.baradist.task2;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    private final Scanner scanner;
    private final int findClosestTo;
    private List<List<Integer>> arrays;
    private Pair<Integer, Pair<Integer, Integer>> theClosest;

    public Solution(int findClosestTo) {
        this.findClosestTo = findClosestTo;
        scanner = new Scanner(System.in);
        theClosest = new Pair<>(null, null);
    }

    public void process() {
        createArrays(askForInteger());
        printArrays();
        findTheClosest();
        printTheClosest();
    }

    public void printTheClosest() {
        System.out.println("The closest value is " + theClosest.getKey()
                + (theClosest.getValue() == null ? "" : ", row: " + theClosest.getValue().getKey()
        + ", element: " + theClosest.getValue().getValue()));
    }

    void findTheClosest() {
        for (int i = 0; i < arrays.size(); i++) {
            for (int j = 0; j < arrays.get(i).size(); j++) {
                if (less(arrays.get(i).get(j), theClosest.getKey())) {
                    theClosest = new Pair<>(arrays.get(i).get(j), new Pair<>(i, j));
                }
            }
        }
    }

    public Pair<Integer, Pair<Integer, Integer>> getTheClosest() {
        return theClosest;
    }

    void setArrays(List<List<Integer>> arrays) {
        this.arrays = arrays;
    }

    private boolean less(Integer x, Integer closest) {
        if (closest == null) {
            return true;
        }
        return Math.abs((long) findClosestTo - x) < Math.abs((long) findClosestTo - closest);
    }

    private void createArrays(int size) {
        arrays = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrays.add(new ArrayList<>(i + 1));
            for (int j = 0; j < i + 1; j++) {
                arrays.get(i).add(askForInteger());
            }
        }
    }

    private int askForInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Input a number");
            }
        }
    }

    private void printArrays() {
        System.out.println(arrays.stream()
                .map((array) -> array.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("\t")))
                .collect(Collectors.joining("\n")));
    }
}
