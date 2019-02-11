package cf.baradist.task4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AnimalDemo {

    @Test
    void demo() {
        Animal[] animals = {
                new Fish("Shark"),
                new SeaMammal("Whale"),
                new Bird("Eagle"),
                new TerreMammal("Dog", "Woof-woof!")
        };
        Arrays.stream(animals)
                .forEachOrdered(animal -> {
                    animal.output();
                    System.out.println(animal.moves() + "\t" + animal.sound() + "\t");
                });
    }
}