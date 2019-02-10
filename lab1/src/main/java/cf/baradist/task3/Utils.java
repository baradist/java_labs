package cf.baradist.task3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Utils {
    private static void print(Employee[] employees) {
        Arrays.stream(employees).forEach(System.out::println);
    }

    public static Employee getWithMaxSalary(Employee[] employees) {
        Employee employeeWithMaxSalary = employees[0];
        for (Employee employee : employees) {
            if (employeeWithMaxSalary.getSalary() < employee.getSalary()) {
                employeeWithMaxSalary = employee;
            }
        }
        return employeeWithMaxSalary;
    }

    public static Optional<Employee> getWithMaxSalary1(Employee[] employees) {
        return Arrays.stream(employees)
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    public static double getSumSalary(final Employee[] employees) {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum;
    }

    public static double getSumSalary1(final Employee[] employees) {
        return Arrays.stream(employees)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}
