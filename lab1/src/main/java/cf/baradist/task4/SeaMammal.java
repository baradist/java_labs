package cf.baradist.task4;

public class SeaMammal extends Mammal {
    public SeaMammal(String name) {
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
