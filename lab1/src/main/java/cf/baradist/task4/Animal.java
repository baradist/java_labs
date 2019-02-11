package cf.baradist.task4;

import java.util.HashSet;
import java.util.Set;

public abstract class Animal {
    private static Set<String> names = new HashSet<>();

    private String name;

    public Animal(String name) {
        if (names.contains(name)) {
            throw new IllegalArgumentException("Already exsists an animal with a name " + name);
        }
        this.name = name;
    }

    abstract public String moves();

    abstract public String sound();

    public void output() {
        System.out.println("Name: " + name + ", type: " + this.getClass().getSimpleName());
    }
}
