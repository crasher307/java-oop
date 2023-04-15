package lesson7.homework.controller;

import lesson7.homework.model.*;
import lombok.*;

@Getter
public final class CalculatorCN extends CalculatorBase<CN> {
    /** Constructor */
    public CalculatorCN() {
        super(0);
    }

    public CalculatorCN(Object result) {
        super(result);
    }

    /** Service */
    protected CN setNumber(Object value) {
        if (value instanceof CN) return (CN) value;
        if (value instanceof Number) return new ModelCN(((Number) value).doubleValue());
        return null;
    }

    protected CN setResult(ACT action, CN value) {
        return switch (action) {
            case SET -> value;
            case ADD -> new ModelCN(
                    this.result.getR() + value.getR(),
                    this.result.getI() + value.getI()
            );
            case SUB -> new ModelCN(
                    this.result.getR() - value.getR(),
                    this.result.getI() - value.getI()
            );
            case MLT -> new ModelCN(
                    this.result.getR() * value.getR() - this.result.getI() * value.getI(),
                    this.result.getI() * value.getR() + this.result.getR() * value.getI()
            );
            case DIV -> {
                double v = value.getR() * value.getR() + value.getI() * value.getI();
                yield new ModelCN(
                        (this.result.getR() * value.getR() + this.result.getI() * value.getI()) / v,
                        (this.result.getI() * value.getR() - this.result.getR() * value.getI()) / v
                );
            }
        };
    }
}