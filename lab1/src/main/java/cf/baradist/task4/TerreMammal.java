package cf.baradist.task4;

public class TerreMammal extends Mammal {
    private String sound;

    public TerreMammal(String name, String sound) {
        super(name);
        this.sound = sound;
    }

    @Override
    public String moves() {
        return "Run";
    }

    @Override
    public String sound() {
        return sound;
    }
}
