package cf.baradist.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    private Employee[] employees;
    private Employee withMaxSalary;

    @BeforeEach
    void setUp() {
        withMaxSalary = new Employee("Name3", "not a worker");
        employees = new Employee[]{
                new Employee("Name1"),
                new Employee("Name2", "worker"),
                withMaxSalary,
                new Employee("Name4", "manager", 5555D),
        };
    }

    @Test
    void getSumSalaryTest() {
        assertEquals(21555.0, Utils.getSumSalary(employees));
    }

    @Test
    void getSumSalary1Test() {
        assertEquals(21555.0, Utils.getSumSalary1(employees));
    }

    @Test
    void getWithMaxSalaryTest() {
        assertEquals(withMaxSalary, Utils.getWithMaxSalary(employees));
    }

    @Test
    void getWithMaxSalary1Test() {
        assertEquals(withMaxSalary, Utils.getWithMaxSalary1(employees).get());
    }
}