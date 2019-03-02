package model;

import java.util.Objects;

public class Student extends Person {
    private int age;

    public Student(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return age == student.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age + ", " +
                super.toString() +
                '}';
    }
}
