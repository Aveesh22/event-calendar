package eventorganizer;

public enum Month {
    JANUARY (1, 31),
    FEBRUARY_NONLEAP (2, 28),
    FEBRUARY_LEAP (2, 29),
    MARCH (3, 31),
    APRIL (4, 30),
    MAY (5, 31),
    JUNE (6, 30),
    JULY (7, 31),
    AUGUST (8, 31),
    SEPTEMBER (9, 30),
    OCTOBER (10, 31),
    NOVEMBER (11, 30),
    DECEMBER (12, 31);


    private final int monthNumber;
    private final int totalDays;

    Month(int monthNumber, int totalDays) {
        this.monthNumber = monthNumber;
        this.totalDays = totalDays;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getTotalDays() {
        return totalDays;
    }
}
