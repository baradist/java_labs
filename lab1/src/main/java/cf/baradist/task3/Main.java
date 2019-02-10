package cf.baradist.task3;

import static cf.baradist.task3.Utils.*;
import static jdk.nashorn.internal.objects.Global.print;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[]{
                new Employee("Name1"),
                new Employee("Name2", "worker"),
                new Employee("Name3", "not a worker"),
                new Employee("Name4", "worker", 5555D),
                new Employee("Name5"),
                new Employee("Name6", "worker"),
                new Employee("Name7", "not a worker"),
                new Employee("Name8", "worker", 7777D),
                new Employee("Name9"),
                new Employee("Name10", "manager"),
        };
        print(employees);

        System.out.println(getSumSalary(employees));
        System.out.println(getSumSalary1(employees));

        System.out.println(getWithMaxSalary(employees));
        getWithMaxSalary1(employees).ifPresent(System.out::println);
    }
}
