package eventorganizer;

/**
 * This enum class defines the possible timeslots for an Event.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public enum Timeslot {
    MORNING (10, 30),
    AFTERNOON (2, 0),
    EVENING (6, 30);

    private final int hour;
    private final int minute;

    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        if (name().equals("MORNING"))
            return hour + ":" + String.format("%02d", minute) + "am";
        else
            return hour + ":" + String.format("%02d", minute) + "pm";
    }
}
