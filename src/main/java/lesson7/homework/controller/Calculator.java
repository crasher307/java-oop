package lesson7.homework.controller;

import lesson7.homework.model.*;
import lombok.*;

@Getter
public final class Calculator extends CalculatorBase<N> {
    /** Constructor */
    public Calculator() {
        super(0);
    }

    public Calculator(Object result) {
        super(result);
    }

    /** Service */
    protected N setNumber(Object value) {
        if (value instanceof N) return (N) value;
        if (value instanceof Number) return new ModelN(((Number) value).doubleValue());
        return null;
    }

    protected N setResult(ACT action, N value) {
        return switch (action) {
            case SET -> value;
            case ADD -> new ModelN(this.result.getR() + value.getR());
            case SUB -> new ModelN(this.result.getR() - value.getR());
            case MLT -> new ModelN(this.result.getR() * value.getR());
            case DIV -> new ModelN(this.result.getR() / value.getR());
        };
    }
}