package cf.baradist.task4;

public class Fish extends Animal {
    public Fish(String name) {
        super(name);
    }

    @Override
    public String moves() {
        return "Swim";
    }

    @Override
    public String sound() {
        return "Nothing";
    }
}
