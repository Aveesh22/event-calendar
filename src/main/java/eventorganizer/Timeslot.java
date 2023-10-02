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

    /**
     * Constructor to create the Timeslot object
     * with the given hour and minute.
     * @param hour the hour of the time
     * @param minute the minute of the time
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Get the hour of this timeslot
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the minute of this timeslot
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Get the name of this timeslot
     * @param hour the hour
     * @param min the minute
     * @return the String name
     */
    public String getName(int hour, int min)
    {
        if(hour == MORNING.hour && min == MORNING.minute)
            return "MORNING";
        else if(hour == AFTERNOON.hour && min == AFTERNOON.minute)
            return "AFTERNOON";
        else
            return "EVENING";
    }

    /**
     * Format the timeslot to a string with a colon and am/pm.
     * @return the formatted time as a String
     */
    @Override
    public String toString() {
        if (name().equals("MORNING"))
            return hour + ":" + String.format("%02d", minute) + "am";
        else
            return hour + ":" + String.format("%02d", minute) + "pm";
    }
}
