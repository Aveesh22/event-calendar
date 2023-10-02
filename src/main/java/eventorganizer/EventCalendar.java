package eventorganizer;

/**
 * This class defines an EventCalendar for organizing Events.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class EventCalendar
{
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    public static final int GROWTH_RATE = 4;

    /**
     * A default constructor that creates an event calendar
     * starting with size GROWTH_RATE.
     */
    public EventCalendar()
    {
        events = new Event[GROWTH_RATE];
        setNumEvents();
    }

    /**
     * A parameterized constructor that creates an event calendar
     * based on a given array of events.
     * @param events the array of events
     */
    public EventCalendar(Event[] events)
    {
        this.events = events;
        setNumEvents();
    }

    /**
     * Get the array of events
     * @return the events array
     */
    public Event[] getEvents()
    {
        return events;
    }

    /**
     * Get the number of events in the calendar
     * @return the number of events as an int
     */
    public int getNumEvents()
    {
        return numEvents;
    }

    /**
     * Set the numEvents variable to be the amount of
     * events in the events array.
     */
    public void setNumEvents()
    {
        int i = 0;
        for (Event event : events) {
            if (event != null)
                i++;
        }
        this.numEvents = i;
    }

    /**
     * Search for an Event in the list
     * @param event the Event to search for
     * @return the index of the Event or -1 if not found
     */
    private int find(Event event)
    {
        for(int i = 0; i < events.length; i++)
        {
            if(events[i].equals(event))
            {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increase the capacity of events by 4
     */
    private void grow()
    {
        Event[] temp = new Event[numEvents + GROWTH_RATE];
        for(int i = 0; i < events.length; i++)
        {
            temp[i] = events[i];
        }
        events = temp;
        setNumEvents();
    }

    /**
     * Add an Event to the events list
     * @param event the Event to add
     * @return true if the Event was added successfully
     */
    public boolean add(Event event)
    {
        boolean addSuccess = false;

        for (int i = events.length - 2; i >= 0; i--)
        {
            if(events[i] != null && events[i + 1] == null)
            {
                events[i + 1] = event;
                addSuccess = true;
            }

            if(i == 0 && events[i] == null)
            {
                events[i] = event;
                addSuccess = true;
            }
        }

        if (events[events.length - 1] != null)
            grow();

        setNumEvents();
        return addSuccess;
    }

    /**
     * Remove an Event from the events list
     * @param event the Event to remove
     * @return true if the Event was removed successfully
     */
    public boolean remove(Event event)
    {
        int index = find(event);
        if (index != NOT_FOUND) {
            int i = index;
            while (events[i] != null && i != numEvents) {
                events[i] = events[i + 1];
                i++;
            }
            setNumEvents();
            return true;
        }
        else
            return false;
    }

    /**
     * Checks if the events list contains the Event
     * @param event the Event to search for
     * @return true if the Event was found
     */
    public boolean contains(Event event)
    {
        for (Event e : events) {
            if (e.equals(event))
                return true;
        }
        return false;
    }

    /**
     * print the Event Calendar as is; ordered by the specific commands
     * ran on the events array
     */
    public void print() //print the array as is
    {
        if (numEvents > 0) {
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
        }
        else {
            System.out.println("eventorganizer.Event calendar is empty!");
        }
    }

    /**
     * print the Event Calendar ordered by date and timeslot
     */
    public void printByDate() {
        Quicksort q = new Quicksort(Sort.DATE);
        q.sort(events);
        print();
    }

    /**
     * print the Event Calendar ordered by campus and building/room
     */
    public void printByCampus() {
        Quicksort qs = new Quicksort(Sort.CAMPUS);
        qs.sort(events);
        print();
    }

    /**
     * print the Event Calendar ordered by department
     */
    public void printByDepartment() {
        Quicksort qs = new Quicksort(Sort.DEPARTMENT);
        qs.sort(events);
        print();
    }

}
