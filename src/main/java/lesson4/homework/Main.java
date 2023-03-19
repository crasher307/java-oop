package lesson4.homework;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc(1010.110, 2);
        calc.plus(1100, 1010).minus(10).multi(100, 10).div(10);
        calc.history();

        Calc test = new Calc();
        System.out.println("100.21^10 -> ^2 = " + test.from10to2("100.21"));
        System.out.println("1100100.01011 ^2 -> ^10 = " + test.from2to10("1100100.01011"));
    }
}