package cf.baradist.task3;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private static AtomicInteger counter = new AtomicInteger(0);
    private int id;
    private String name;
    private String position;
    private double salary;

    public Employee(String name) {
        this(name, "worker", 5000D);
    }

    public Employee(String name, String position) {
        this(name, position, "worker".equals(position) ? 5000D : 6000D);
    }

    public Employee(String name, String position, double salary) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
