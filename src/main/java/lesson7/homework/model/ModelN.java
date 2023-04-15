package lesson7.homework.model;

import lombok.*;

@Getter
@Setter
public class ModelN implements N {
    protected double r = 0; // real, вещественная часть

    public ModelN(double real) {
        this.r = real;
    }

    @Override
    public String toString() {
        return String.format("%1.2f", this.r);
    }
}