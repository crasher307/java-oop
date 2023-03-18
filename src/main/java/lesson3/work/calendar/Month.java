package lesson3.work.calendar;

enum Month {
    JAN(1, 31, "Январь"), FEB(2, 28, 29, "Февраль"),
    MAR(3, 31, "Март"), APR(4, 30, "Апрель"),
    MAY(5, 31, "Май"), JUN(6, 30, "Июнь"),
    JUL(7, 31, "Июль"), AUG(8, 31, "Август"),
    SEP(9, 30, "Сентябрь"), OCT(10, 31, "Октябрь"),
    NOV(11, 30, "Ноябрь"), DEC(12, 31, "Декабрь");
    private final int idx;
    private final int days;
    private final int daysLeap; // leapYear
    private final String name;

    Month(int idx, int days, String name) {
        this(idx, days, days, name);
    }

    Month(int idx, int days, int daysLeap, String name) {
        this.idx = idx;
        this.days = days;
        this.daysLeap = daysLeap;
        this.name = name;
    }

    public int getIdx() {
        return idx;
    }

    public int getDays() {
        return days;
    }

    public int getDaysLeap() {
        return daysLeap;
    }

    public String getName() {
        return name;
    }
}
