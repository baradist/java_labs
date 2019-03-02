package model;

import java.util.Objects;

public class Educator extends Person {
    private String degree;

    public Educator(String name, String degree) {
        super(name);
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Educator educator = (Educator) o;
        return Objects.equals(degree, educator.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), degree);
    }

    @Override
    public String toString() {
        return "Educator{" +
                "degree='" + degree + ", " +
                super.toString() +
                '}';
    }
}
