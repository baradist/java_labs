package cf.baradist.task1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    private List<Integer> integers;

    List<Integer> getIntegers() {
        return integers;
    }

    void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public void process() {
        inputThreeNumbers();
        sortThreeNumbers();
        print();
    }

    private void inputThreeNumbers() {
        System.out.println("Enter three numbers:");
        integers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; ) {
            try {
                integers.add(scanner.nextInt());
                i++;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Input a number");
            }
        }
    }

    void sortThreeNumbers() {
        int x1 = integers.get(0);
        int x2 = integers.get(1);
        int x3 = integers.get(2);
        integers.clear();
        int tmp;

        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (x2 > x3) {
            tmp = x3;
            x3 = x2;
            x2 = tmp;
        }
        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        integers.add(x1);
        integers.add(x2);
        integers.add(x3);
    }

    public void print() {
        System.out.println(integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
}
