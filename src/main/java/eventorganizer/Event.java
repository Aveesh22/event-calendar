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

    /** Event class constructor which instantiates the
     * date, startTime, location, contact, and duration variables
     * @param date the Date of the Event
     * @param startTime the startTime of the Event
     * @param location the location the Event takes place
     * @param contact the contact person for the Event
     * @param duration the duration of the Event
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration)
    {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /** Event class constructor which instantiates the
     * date, startTime, and location
     * @param date the Date of the Event
     * @param startTime the startTime of the Event
     * @param location the location the Event takes place
     */
    public Event(Date date, Timeslot startTime, Location location)
    {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }

    /**
     * Get the Date of the Event
     * @return the Date of the Event
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the start time of the Event
     * @return a Timeslot object depicting the start time of the Event
     */
    public Timeslot getStartTime() {
        return startTime;
    }

    /**
     * Get the location of the Event
     * @return a Location object depicting the location of the Event
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get the contact associated with the Event
     * @return a Contact object depicting the contact for the Event
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Get the duration of the Event
     * @return an integer representing the duration of the Event
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * Overrides the equals() method in the Object class such that 2 events
     * are equal if their dates, timeslots, and locations are equal.
     * @param obj the second event to compare with
     * @return boolean indicating if the 2 events equal
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Event)
        {
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
    public String toString()
    {
        String endTime = calculateEndTime(startTime, duration);
        assert endTime != null;
        return "[eventorganizer.Event eventorganizer.Date: " + date.toString() + "] " +
                "[Start: " + startTime.toString() + "] " + "[End: " + endTime + "] " +
                "@" + location.name() + " " +
                "(" + location.getBuilding() + ", " + location.getCampus() + ") " +
                "[eventorganizer.Contact: " + contact.getDepartment().getFullName() +
                ", " + contact.getEmail() + "]";
    }

    /**
     * Given a Timeslot and duration of an event, the method returns
     * a textual representation of the end time of the provided event
     * @param startTime the Timeslot object which contains a start time for the event
     * @param duration an integer depicting the number of minutes the event will last
     * @return a textual representation of the end time of the Event
     */
    public String calculateEndTime(Timeslot startTime, int duration)
    {
        int hour = startTime.getHour();
        int min = startTime.getMinute() + duration;
        boolean amPM = startTime.name().equals("MORNING");
        while(min >= 60)
        {
            if(hour == 11)
                amPM = !amPM;

            if(hour == 12)
            {
                hour = 1;
                min -= 60;
                continue;
            }

            hour += 1;
            min -= 60;
        }

        if(amPM)
            return (hour + ":" + String.format("%02d", min) + "am");

        else
            return (hour + ":" + String.format("%02d", min) + "pm");
    }

    /**
     * This method compares 2 events first by their dates, then by timeslots.
     * @param event the event compared to by the current event
     * @return -1, 0, or 1
     */
    @Override
    public int compareTo(Event event)
    {
        if ((this.date.compareTo(event.date)) > 0)
            return 1;
        else if ((this.date.compareTo(event.date)) < 0)
            return -1;
        else
        {
            if ((this.startTime.compareTo(event.startTime)) > 0)
                return 1;
            else if ((this.startTime.compareTo(event.startTime)) < 0)
                return -1;
            else
                return 0;
        }
    }

    /**
     * Test Case #1
     */
    private static void testEquals_True() {
        Date date = new Date("01/01/1999");
        Timeslot startTime = Timeslot.MORNING;
        Location location = Location.AB2225;
        Department department = Department.CS;
        Contact contact = new Contact(department);
        int duration = 80;
        Event event1 = new Event(date, startTime, location, contact, duration);
        Event event2 = new Event(date, startTime, location, contact, duration);

        boolean expectedOutput = true;
        boolean actualOutput = event1.equals(event2);
        System.out.println("**Test case #1: Two events are equal.");
        testResult(event1, event2, expectedOutput, actualOutput);
    }

    /**
     * Test Case #2
     */
    private static void testEquals_False() {
        Date date = new Date("01/01/1999");
        Timeslot startTime = Timeslot.MORNING;
        Location location1 = Location.AB2225;
        Location location2 = Location.TIL232;
        Department department = Department.CS;
        Contact contact = new Contact(department);
        int duration = 80;
        Event event1 = new Event(date, startTime, location1, contact, duration);
        Event event2 = new Event(date, startTime, location2, contact, duration);

        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("**Test case #2: Two events are not equal.");
        testResult(event1, event2, expectedOutput, actualOutput);
    }

    /**
     * Test Case #3
     */
    private static void testCompareTo_Equal() {
        Date date = new Date("01/01/1999");
        Timeslot startTime = Timeslot.MORNING;
        Location location = Location.AB2225;
        Department department = Department.CS;
        Contact contact = new Contact(department);
        int duration = 80;
        Event event1 = new Event(date, startTime, location, contact, duration);
        Event event2 = new Event(date, startTime, location, contact, duration);

        int expectedOutput = 0;
        int actualOutput = event1.compareTo(event2);
        System.out.println("**Test case #3: Two events are comparatively equal.");
        testResult(event1, event2, expectedOutput, actualOutput);
    }

    /**
     * Test Case #4
     */
    private static void testCompareTo_NotEqual() {
        Date date = new Date("01/01/1999");
        Date d = new Date("05/27/2000");
        Timeslot startTime = Timeslot.MORNING;
        Location location1 = Location.AB2225;
        Location location2 = Location.TIL232;
        Department department = Department.CS;
        Contact contact = new Contact(department);
        int duration = 80;
        Event event1 = new Event(date, startTime, location1, contact, duration);
        Event event2 = new Event(d, startTime, location2, contact, duration);

        int expectedOutput = -1;
        int actualOutput = event1.compareTo(event2);
        System.out.println("**Test case #4: Event 1 comes first.");
        testResult(event1, event2, expectedOutput, actualOutput);
    }

    /**
     * Check if a given test case is PASS or FAIL given 2 outputs.
     * Overloaded - given boolean outputs
     * @param expectedOutput expected output
     * @param actualOutput actual output
     */
    private static void testResult(Event event1, Event event2,
                                   boolean expectedOutput, boolean actualOutput) {
        System.out.println("Test Event 1: " + event1.toString());
        System.out.println("Test Event 2: " + event2.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.print("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput)
            System.out.println(" (PASS)\n");
        else
            System.out.println(" (FAIL)\n");
    }

    /**
     * Check if a given test case is PASS or FAIL given 2 outputs.
     * Overloaded - given int outputs
     * @param expectedOutput expected output
     * @param actualOutput actual output
     */
    private static void testResult(Event event1, Event event2,
                                   int expectedOutput, int actualOutput) {
        System.out.println("Test Event 1: " + event1.toString());
        System.out.println("Test Event 2: " + event2.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.print("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput)
            System.out.println(" (PASS)\n");
        else
            System.out.println(" (FAIL)\n");
    }

    /**
     * Testbed main to test methods in the Event class
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        testEquals_True();
        testEquals_False();
        testCompareTo_Equal();
        testCompareTo_NotEqual();
    }
}
