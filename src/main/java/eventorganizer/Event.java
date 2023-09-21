package eventorganizer;

/**
 * This class defines ...
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Event implements Comparable<Event>
{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

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
     * of an event in the following format:
     * [Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]
     * @return the textual representation of an event
     */
    @Override
    public String toString() {
        Timeslot endTime = null; //this.startTime.addMinutes(this.duration);
        return "[Event Date: " + date + "] [Start: " + startTime + "] [End: " + endTime +
                "] @" + location.getRoom() + " (" + location.getBuilding() + ", " + location.getCampus() +
                ") [Contact: " + contact.getDepartment() + ", " + contact.getEmail() + "]";
    }

    /**
     * This method compares 2 events.
     */
    @Override
    public int compareTo(Event event) {
        return 0;
    }


    /**
     * Testbed main to exercise the equals() method.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //run tests
    }
}
