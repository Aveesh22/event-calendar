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

    public Date getDate() {
        return date;
    }

    public Timeslot getStartTime() {
        return startTime;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public int getDuration() {
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
        return "[eventorganizer.Event eventorganizer.Date:: " + date.toString() + "]" +
                "[Start: " + startTime.toString() + "] " + "[End: " + endTime + "]" +
                "@" + location.name() + " " +
                "(" + location.getBuilding() + ", " + location.getCampus() + ") " +
                "[eventorganizer.Contact: " + contact.getDepartment().getFullName() +
                ", " + contact.getEmail() + "]";
    }

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
     * Testbed main to test methods in the Event class
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        Date date = new Date("01/01/1999");
        Date d = new Date("05/27/2000");
        Timeslot startTime = Timeslot.MORNING;
        Location location1 = Location.AB2225;
        Location location2 = Location.TIL232;
        Department department = Department.CS;
        Contact contact = new Contact(department);
        int duration1 = 80;
        Event event1 = new Event(date, startTime, location1, contact, duration1);
        Event event2 = new Event(date, startTime, location2, contact, duration1);
        Event event3 = new Event(d, startTime, location1, contact, duration1);
        System.out.println(event1.equals(event2));
        System.out.println(event1.compareTo(event3));
    }
}
