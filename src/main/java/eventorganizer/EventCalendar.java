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

    public EventCalendar()
    {
        events = new Event[4];
        setNumEvents();
    }

    public EventCalendar(Event[] events)
    {
        this.events = events;
        setNumEvents();
    }

    public Event[] getEvents()
    {
        return events;
    }

    public int getNumEvents()
    {
        return numEvents;
    }

    public void setEvents(Event[] events)
    {
        this.events = events;
    }

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
        Event[] temp = new Event[numEvents + 4];
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

        for(int i = events.length-2; i > 0; i--)
        {
            if(events[i] != null && events[i+1] == null)
            {
                events[i+1] = event;
                addSuccess = true;
            }
        }

        if(events[events.length-1] != null)
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
     * ordered by date and timeslot
     */
    public void printByDate() {
        //POSSIBLE LOGIC:
        //- clone the calendar into a new calendar object
        //- sort the clone's events array
        //- call clone.print() to print as is (bc it will be sorted)

        //sorting algorithm must be in-place and written ourselves
        //IN-PLACE --> quicksort (implementation from data structures)
        //if quicksort is too annoying to implement, insertion sort is fine (also in-place)

        Event[] clonedEvents = events.clone();
        Quicksort.sort(clonedEvents);
        //right now it sorts based on date and time by the default compareTo in Event
        //how to implement sorting based on campus and department...?

        EventCalendar clonedCalendar = new EventCalendar(clonedEvents);
        clonedCalendar.print();
    }

    /**
     * ordered by campus and building/room
     */
    public void printByCampus() {

    }

    /**
     * ordered by department
     */
    public void printByDepartment() {

    }




}
