package lesson7.homework.controller;

import lesson7.homework.model.*;
import lombok.*;

import java.util.*;

@Getter
public abstract class CalculatorBase<T extends N> implements Calc {
    @Getter
    @AllArgsConstructor
    protected enum ACT {
        SET('=', "Присвоение"),
        ADD('+', "Сложение"),
        SUB('-', "Вычитание"),
        MLT('*', "Умножение"),
        DIV('/', "Деление");
        private final char type;
        private final String name;

        public String getName() {
            return String.format("%-11s", this.name);
        }
    }

    /** Data */
    protected T result;
    private final List<Log> history = new ArrayList<>();

    /** Constructor */
    public CalculatorBase() {
        this(0);
    }

    public CalculatorBase(Object result) {
        this.calc(ACT.SET, result);
    }

    /** Use */
    public final CalculatorBase<T> plus(Object... values) {
        for (Object value : values) this.calc(ACT.ADD, value);
        return this;
    }

    public final CalculatorBase<T> minus(Object... values) {
        for (Object value : values) this.calc(ACT.SUB, value);
        return this;
    }

    public final CalculatorBase<T> multi(Object... values) {
        for (Object value : values) this.calc(ACT.MLT, value);
        return this;
    }

    public final CalculatorBase<T> div(Object... values) {
        for (Object value : values) this.calc(ACT.DIV, value);
        return this;
    }

    /** Service */
    private void calc(ACT action, Object value) {
        Object response;
        T number;
        T result;
        if ((number = this.setNumber(value)) == null) {
            response = new Exception("Обработка полученного типа не описана");
        } else if ((result = this.setResult(action, number)) == null) {
            response = new Exception("Обработка запрашиваемого действия не описана");
        } else {
            this.result = result;
            response = this.getLogString(action, number);
        }
        this.history.add(new ModelLog(response)); // Add to history
    }

    private String getLogString(ACT action, T value) {
        int cnLength = 16 - value.toString().length();
        if (cnLength < 1) cnLength = 1;
        return String.format(
                "%s%c %s %s",
                action.getName(),
                action.getType(),
                value,
                action.equals(ACT.SET) ? "" : String.format("%" + cnLength + "s = %s", "", this.result)
        ).trim();
    }

    /** For children classes */
    protected abstract T setNumber(Object value);

    protected abstract T setResult(ACT action, T value);
}