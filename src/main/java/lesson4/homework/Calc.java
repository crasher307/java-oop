package lesson4.homework;

import java.util.ArrayList;

class Calc {
    private int radix; // TODO подумать над переводом в разные системы счисления
    private double result;
    private final ArrayList<Action> history = new ArrayList<>();

    public Calc() {
        this(0, 10);
    }

    public Calc(Number result) {
        this(result, 10);
    }

    public Calc(Number result, int radix) {
        this.result = result.doubleValue();
        this.history.add(new Action(this.result));
    }

    public Calc plus(Number... values) {
        for (Number value : values)
            this.history.add(new Action(
                    '+',
                    this.result,
                    value.doubleValue(),
                    this.result += value.doubleValue()
            ));
        return this;
    }

    public Calc minus(Number... values) {
        for (Number value : values)
            this.history.add(new Action(
                    '-',
                    this.result,
                    value.doubleValue(),
                    this.result -= value.doubleValue()
            ));
        return this;
    }

    public Calc multi(Number... values) {
        for (Number value : values)
            this.history.add(new Action(
                    '*',
                    this.result,
                    value.doubleValue(),
                    this.result *= value.doubleValue()
            ));
        return this;
    }

    public Calc div(Number... values) {
        for (Number value : values)
            this.history.add(new Action(
                    '/',
                    this.result,
                    value.doubleValue(),
                    this.result /= value.doubleValue()
            ));
        return this;
    }

    public double result() {
        return this.result;
    }

    public void history() {
        for (int i = 0; i < Action.count; i++) history.get(i).show();
    }

    public int from10to2(Object number) {
        // Округляет до целых
        // TODO дописать для дробных
        return Integer.parseInt(
                Integer.toBinaryString(
                        Integer.parseInt(
                                String.valueOf(number).replace(',', '.').split("\\.")[0]
                        )
                )
        );
    }

    public int from2to10(Object number) {
        // Округляет до целых
        // TODO дописать для дробных
        return Integer.parseInt(String.valueOf(number).replace(',', '.').split("\\.")[0], 2);
    }
}

