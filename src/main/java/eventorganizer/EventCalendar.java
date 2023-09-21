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
        for(int i = 0; i < events.length; i++)
        {
            System.out.println(events[i]);
        }
    }
    public void printByDate() { } //ordered by date and timeslot
    public void printByCampus() { } //ordered by campus and building/room
    public void printByDepartment(){ } //ordered by department
}
