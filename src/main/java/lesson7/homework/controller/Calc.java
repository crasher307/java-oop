package lesson7.homework.controller;

import lesson7.homework.model.*;

import java.util.List;

public interface Calc {
    CalculatorBase<? extends N> plus(Object... values);

    CalculatorBase<? extends N> minus(Object... values);

    CalculatorBase<? extends N> multi(Object... values);

    CalculatorBase<? extends N> div(Object... values);

    <T extends N> T getResult();

    List<Log> getHistory();
}