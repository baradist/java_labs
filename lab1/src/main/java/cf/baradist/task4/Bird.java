package cf.baradist.task4;

public class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    @Override
    public String moves() {
        return "Fly";
    }

    @Override
    public String sound() {
        return "Chirp";
    }
}
