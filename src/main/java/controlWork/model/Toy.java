package controlWork.model;

import controlWork.controller.Toys;
import lombok.Getter;

@Getter
public class Toy {
    private final Toys toys;
    private final int id;
    private final String name;
    private final double chance;

    public Toy(Toys toys, int id, String name, double chance) {
        this.toys = toys;
        this.id = id;
        this.name = name;
        this.chance = chance;
    }

    public double getFullChance() {
        return this.toys.getAllChances() > 0 ? this.chance / this.toys.getAllChances() : this.chance;
    }

    public String toString() {
        return String.format(
                "Toy(id=%d, name=%s, chance=%1.4f, (*dynamic)fullChance=%1.4f)",
                this.id,
                this.name,
                this.chance,
                this.getFullChance()
        );
    }
}
