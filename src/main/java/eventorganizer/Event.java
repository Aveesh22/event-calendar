package eventorganizer;

/**
 * This class defines an Event on the EventCalendar.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Event implements Comparable<Event>
{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Overrides the equals() method in the Object class such that 2 events
     * are equal if their dates, timeslots, and locations are equal.
     * @param obj the second event to compare with
     * @return boolean indicating if the 2 events equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return event.date.equals(this.date) && //dates are equal
                    event.startTime.equals(this.startTime) && //starting times are equal
                    event.location.equals(this.location); //locations are equal
        }
        return false;
    }

    /**
     * Overrides the toString() method to return a textual representation of an event.
     * @return the textual representation of an event
     */
    @Override
    public String toString() {
        Timeslot endTime = addMinutes(startTime, duration);
        assert endTime != null;
        return "[eventorganizer.Event eventorganizer.Date:: " + date.toString() + "]" +
                "[Start: " + startTime.toString() + "] " + "[End: " + endTime.toString() + "]" +
                "@" + location.name() + " " +
                "(" + location.getBuilding() + ", " + location.getCampus() + ") " +
                "[eventorganizer.Contact: " + contact.getDepartment().getFullName() +
                ", " + contact.getEmail() + "]";
    }

    /**
     * This method compares 2 events first by their dates, then by timeslots.
     * @return -1, 0, or 1
     */
    @Override
    public int compareTo(Event event) {
        if ((this.date.compareTo(event.date)) > 0)
            return 1;
        else if ((this.date.compareTo(event.date)) < 0)
            return -1;
        else {
            if ((this.startTime.compareTo(event.startTime)) > 0)
                return 1;
            else if ((this.startTime.compareTo(event.startTime)) < 0)
                return -1;
            else
                return 0;
        }
    }





    /**
     * This method adds minutes to the current timeslot time
     * @param duration amount of minutes to add
     * @return new Timeslot
     */
    private static Timeslot addMinutes(Timeslot startTime, int duration) {
        return startTime;
    }




    /**
     * Testbed main to exercise the equals() method.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //run tests
    }
}
