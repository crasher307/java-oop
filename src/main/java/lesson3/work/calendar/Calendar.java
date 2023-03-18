package lesson3.work.calendar;

public class Calendar {
    private final boolean isLeapYear;
    private final Month[] months;

    public Calendar(boolean isLeapYear) {
        this.isLeapYear = isLeapYear;
        this.months = new Month[]{
                Month.JAN,
                Month.FEB,
                Month.MAR,
                Month.APR,
                Month.MAY,
                Month.JUN,
                Month.JUL,
                Month.AUG,
                Month.SEP,
                Month.OCT,
                Month.NOV,
                Month.DEC
        };
    }

    public int getMonthsId(int idx) {
        return months[idx].getIdx();
    }

    public String getMonthsName(int idx) {
        return months[idx].getName();
    }

    public int getMonthsDays(int idx) {
        return isLeapYear ? months[idx].getDaysLeap() : months[idx].getDays();
    }
}
