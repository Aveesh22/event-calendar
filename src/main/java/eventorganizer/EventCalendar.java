package eventorganizer;

/**
 * This class defines ...
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class EventCalendar
{
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    public EventCalendar()
    {
        numEvents = events.length;
    }

    public EventCalendar(Event[] events)
    {
        this.events = events;
        this.numEvents = events.length;
    }

    public Event[] getEvents() {
        return events;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public void setNumEvents() {
        this.numEvents = events.length;
    }

    private int find(Event event) //search an event in the list
    {
        int index = -1;
        for(int i = 0; i < events.length; i++)
        {
            if(events[i].equals(event))
            {
                index = i;
            }
        }
        return index;
    }
    private void grow() //increase the capacity by 4
    {
        Event[] temp = new Event[numEvents+4];
        for(int i = 0; i < events.length; i++)
        {
            temp[i] = events[i];
        }
        events = temp;
    }
    public boolean add(Event event)
    {
        

    }
    public boolean remove(Event event)
    {

    }
    public boolean contains(Event event)
    {
        for(int i = 0; i < events.length; i++)
        {
            if(events[i].equals(event))
                return true;
        }
        return false;
    }

    public void print() //print the array as is
    {
        if (numEvents > 0) {
            System.out.println("* eventorganizer.Event calendar *");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");
        }
        else {
            System.out.println("eventorganizer.Event calendar is empty!");
        }
    }

    /**
     * ordered by date and timeslot
     */
    public void printByDate() {
        /**
         * POSSIBLE LOGIC:
         * - clone the calendar into a new calendar object
         * - sort the clone's events array
         * - call clone.print() to print as is (bc it will be sorted)
         */
        //things to consider: do we want a helper method for the sorting algorithm?
        //sorting algorithm must be in-place and written ourselves
        //IN-PLACE --> quicksort (implementation from data structures)
        //if quicksort is too annoying to implement, insertion sort is fine (also in-place)

        Event[] clonedEvents = events.clone();
        Quicksort.sort(clonedEvents);

        EventCalendar clonedCalendar = new EventCalendar(clonedEvents);
        clonedCalendar.print();

        //still haven't implemented how exactly it's sorted... just copy-pasted sort code
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
