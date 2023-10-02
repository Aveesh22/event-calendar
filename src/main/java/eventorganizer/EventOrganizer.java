package eventorganizer;

import java.util.Scanner;

/**
 * This class handles user input and running commands.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class EventOrganizer
{
    private EventCalendar calendar = new EventCalendar();
    private final int FAR_MONTHS = 6;

    public static final int MIN_DURATION = 30;

    public static final int MAX_DURATION = 120;

    /**
     * Run the A command:
     * Adds an event to the event calendar.
     * We can assume the user will always enter enough data tokens.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdA(String[] cmd) {
        //Date
        Date date = new Date(cmd[Command.DATE.getIndex()]);
        //Timeslot
        Timeslot timeslot = (Timeslot) getEnumValue(Timeslot.values(), cmd[Command.TIME.getIndex()]);
        //Location
        Location location = (Location) getEnumValue(Location.values(), cmd[Command.ROOM.getIndex()]);
        //Contact
        Department department = (Department) getEnumValue(Department.values(), cmd[Command.DEPARTMENT.getIndex()]);
        Contact contact = new Contact(department, cmd[Command.EMAIL.getIndex()]);
        //Duration
        int duration = Integer.parseInt(cmd[Command.DURATION.getIndex()]);

        //Instantiate Event
        Event event = new Event(date, timeslot, location, contact, duration);

        if (eventIsValidAdd(event) && calendar.add(event)) //need to check conditions before adding an event
            System.out.println("eventorganizer.Event added to the calendar.");
    }

    /**
     * Get the corresponding Enum constant to the given string
     * @param values array of constant names in the Enum class
     * @param name the name of the constant to find
     * @return the Enum constant
     */
    private Object getEnumValue(Object[] values, String name)
    {
        for (Object obj : values)
        {
            if(obj instanceof Timeslot)
            {
                int hour = ((Timeslot) obj).getHour();
                int min = ((Timeslot) obj).getMinute();
                String nameOfTimeslot = ((Timeslot) obj).getName(hour, min);
                if(nameOfTimeslot.equalsIgnoreCase(name))
                    return obj;
            }

            if (obj.toString().equalsIgnoreCase(name))
            {
                return obj;
            }
        }
        return null;
    }

    /**
     * Check if an event is valid to add.
     * @param event the event to check
     */
    private boolean eventIsValidAdd(Event event) {
        return isValidDate(event) &&
                isFuture(event) &&
                !isFar(event) &&
                isValidTimeslot(event) &&
                isValidDuration(event) &&
                isValidLocation(event) &&
                isValidContact(event) &&
                event.getContact().isValidEmail() &&
                !isConflict(event);
    }

    /**
     * Check if an event has a valid date.
     * @param event the event to check.
     * @return true if the date is valid.
     */
    private boolean isValidDate(Event event) {
        boolean result = event.getDate().isValid();
        if (!result) {
            System.out.println(event.getDate().toString() + ": Invalid calendar date!");
        }
        return result;
    }

    /**
     * Check if an event's date is a future date.
     * @param event the event to check.
     * @return true if the date is a future date.
     */
    private boolean isFuture(Event event) {
        Date today = Date.today();
        boolean result;
        if (event.getDate().getYear() == today.getYear()) { //year == current year
            if (event.getDate().getMonth() == today.getMonth()) {
                result = event.getDate().getDay() > today.getDay();
            }
            else
                result = event.getDate().getMonth() > today.getMonth();
        }
        else
            result = event.getDate().getYear() > today.getYear(); //year > current year

        if (!result) {
            System.out.println(event.getDate().toString() + ": eventorganizer.Event date must be a future date!");
        }
        return result;
    }

    /**
     * Check if an event's date is more than FAR_MONTHS away.
     * @param event the event to check.
     * @return true if the date is too far.
     */
    private boolean isFar(Event event) {
        Date today = Date.today();
        boolean result = false;
        if (event.getDate().getYear() == today.getYear()) { //year == current year
            if (event.getDate().getMonth() == today.getMonth() + FAR_MONTHS) {
                result = event.getDate().getDay() > today.getDay();
            }
            else
                result = event.getDate().getMonth() > today.getMonth() + FAR_MONTHS;
        }
        else if (event.getDate().getYear() > today.getYear()) { //year > current year
            if (event.getDate().getYear() == today.getYear() + 1) {
                int eventMonth = event.getDate().getMonth();
                int monthsLeftCurrYear = Month.DECEMBER.getMonthNumber() - today.getMonth();
                int totalMonths = monthsLeftCurrYear + eventMonth;
                result = totalMonths >= FAR_MONTHS;
            }
            else result = event.getDate().getYear() > today.getYear() + 1;
        }

        if (result) {
            System.out.println(event.getDate().toString() +
                    ": eventorganizer.Event date must be within 6 months!");
        }
        return result;
    }

    /**
     * Check if an event has a valid timeslot.
     * @param event the event to check.
     * @return true if the timeslot exists.
     */
    private boolean isValidTimeslot(Event event) {
        if (event.getStartTime() == null) {
            System.out.println("Invalid time slot!");
            return false;
        }

        for (Timeslot ts : Timeslot.values()) {
            if (event.getStartTime().equals(ts))
                return true;
        }
        System.out.println("Invalid time slot!");
        return false;
    }

    /**
     * Check if an event has a valid duration.
     * @param event the event to check.
     * @return true if the duration is between MIN_DURATION and MAX_DURATION.
     */
    private boolean isValidDuration(Event event) {
        int duration = event.getDuration();
        boolean result = duration >= MIN_DURATION && duration <= MAX_DURATION;
        if (!result) {
            System.out.println("eventorganizer.Event duration must be at least 30 minutes and at most 120 minutes");
        }
        return result;
    }

    /**
     * Check if an event has a valid location.
     * @param event the event to check.
     * @return true if the location exists.
     */
    private boolean isValidLocation(Event event) {
        if (event.getLocation() == null) {
            System.out.println("Invalid location!");
            return false;
        }

        for (Location l : Location.values()) {
            if (event.getLocation().equals(l))
                return true;
        }
        System.out.println("Invalid location!");
        return false;
    }

    /**
     * Check if an event has a valid contact.
     * @param event the event to check.
     * @return true if the department and email are valid.
     */
    private boolean isValidContact(Event event) {
        boolean result = event.getContact().isValid();
        if (!result) {
            System.out.println("Invalid contact information!");
        }
        return result;
    }

    /**
     * Check if there is a conflict with the given event.
     * @param event the event to check.
     * @return true if there is a conflict on the calendar.
     */
    private boolean isConflict(Event event) {
        boolean conflict = false;
        if(calendar.getNumEvents() == 0)
            return false;
        for (Event e : calendar.getEvents()) {
            if (e != null && e.compareTo(event) == 0) {
                if (e.getLocation().equals(event.getLocation())) {
                    conflict = true;
                    System.out.println("The event is already on the calendar.");
                    break;
                }
            }
        }
        return conflict;
    }

    /**
     * Run the R command:
     * Cancels and removes an event from the event calendar.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdR(String[] cmd) {
        //Date
        Date date = new Date(cmd[Command.DATE.getIndex()]);
        //Timeslot
        Timeslot timeslot = (Timeslot) getEnumValue(Timeslot.values(), cmd[Command.TIME.getIndex()]);
        //Location
        Location location = (Location) getEnumValue(Location.values(), cmd[Command.ROOM.getIndex()]);

        //Instantiate Event
        Event event = new Event(date, timeslot, location);

        if (eventIsValidRemove(event)) {
            boolean eventFound = false;
            for (Event e : calendar.getEvents()) {
                if (e != null &&
                        e.getDate().compareTo(date) == 0 &&
                        e.getStartTime().equals(timeslot) &&
                        e.getLocation().equals(location)) {
                    if (calendar.remove(e)) {
                        eventFound = true;
                        System.out.println("eventorganizer.Event has been removed from the calendar!");
                        break;
                    }
                }
            }
            if (!eventFound)
                System.out.println("Cannot remove; event is not in the calendar!");
        }
    }

    /**
     * Check if an event is valid to remove.
     * @param event the event to check.
     */
    private boolean eventIsValidRemove(Event event) {
        return isValidDate(event) &&
                isFuture(event) &&
                !isFar(event) &&
                isValidTimeslot(event) &&
                isValidLocation(event);
    }


    /**
     * Run the P, PE, PC, or PD command:
     * Displays the event calendar on the console.
     * @param cmd The current input line as a String array of tokens
     */
    private void cmdP(String[] cmd) {
        if (calendar.getNumEvents() > 0) {
            switch (cmd[Command.COMMAND.getIndex()]) {
                case "PE":
                    System.out.println("* eventorganizer.Event calendar by event date and start time *");
                    calendar.printByDate();
                    break;
                case "PC":
                    System.out.println("* eventorganizer.Event calendar by campus and building *");
                    calendar.printByCampus();
                    break;
                case "PD":
                    System.out.println("* eventorganizer.Event calendar by department *");
                    calendar.printByDepartment();
                    break;
                default:
                    System.out.println("* eventorganizer.Event calendar *");
                    calendar.print();
                    break;
            }
            System.out.println("* end of event calendar *");
        }
        else
            System.out.println("eventorganizer.Event calendar is empty!");
    }



    /**
     * Assuming the input is not blank, we run the command corresponding
     * to the command given. If it is invalid, we return the invalid response.
     * @param cmd The current input line as a String array of tokens
     */
    private void runCmd(String[] cmd) {
        switch (cmd[Command.COMMAND.getIndex()])
        {
            case "A" -> cmdA(cmd);
            case "R" -> cmdR(cmd);
            case "P", "PE", "PC", "PD" -> cmdP(cmd);
            default -> System.out.println(cmd[0] + " is an invalid command!");
        };
    }





    /**
     * Read and parse user input commands.
     */
    public void run()
    {
        System.out.println("Event Organizer running...");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();

        while (true) {
            if (!currLine.isBlank()) {
                if (currLine.charAt(0) != 'Q') {
                    String[] commands = currLine.split("\\n+");
                    for (String command : commands) {
                        String[] cmd = command.split("\\s+");
                        runCmd(cmd);
                    }
                }
                else break;
            }
            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Event Organizer terminated.");
    }
}