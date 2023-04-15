package lesson7.homework.model;

import lombok.*;

@Getter
@Setter
public class ModelCN extends ModelN implements CN {
    protected double i = 0; // imaginary, мнимая часть

    public ModelCN(double real) {
        super(real);
    }

    public ModelCN(double real, double imaginary) {
        super(real);
        this.i = imaginary;
    }

    @Override
    public String toString() {
        if (this.i == 0) return String.format("%1.2f", this.r);
        return String.format("%1.2f%s%1.2fi", this.r, this.i > 0 ? "+" : "", this.i);
    }
}