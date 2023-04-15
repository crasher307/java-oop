package lesson7.homework.controller;

import lesson7.homework.model.*;
import lesson7.homework.view.*;

public class UserCalc {
    public static void test() {
        // TODO Добавить работу с пользователем
        Calc calc = new Calculator(10);
        // calc.plus(new ModelCN(2, 4));
        calc.minus(6);
        calc.plus(12);
        calc.minus("H");
        calc.div(2);
        // calc.multi(new ModelCN(4, 4));
        calc.multi(-1);

        Viewer.showLog(calc.getHistory());

        Calc calc2 = new CalculatorCN();
        calc2
                .plus(10, new ModelCN(2, 4))
                .minus(6, "H")
                .div(2)
                .multi(new ModelCN(4, 4), -1);

        Viewer.showLog(calc2.getHistory());
    }
}