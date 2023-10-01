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



    /**
     * Runs the A command:
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

        if (eventIsValid(event) && calendar.add(event)) //need to check conditions before adding an event
            System.out.println("eventorganizer.Event added to the calendar.");
    }

    /**
     * Get the corresponding Enum constant to the given string
     * @param values array of constant names in the Enum class
     * @param name the name of the constant to find
     * @return the Enum constant
     */
    private Object getEnumValue(Object[] values, String name) {
        for (Object obj : values) {
            if (obj.toString().equalsIgnoreCase(name)) { //case-insensitive
                return obj;
            }
        }
        return null;
    }

    /**
     * Checks if an event is valid
     * @param event the event to check
     */
    private boolean eventIsValid(Event event) {
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

    private boolean isValidDate(Event event) {
        boolean result = event.getDate().isValid();
        if (!result) {
            System.out.println(event.getDate().toString() + ": Invalid calendar date!");
        }
        return result;
    }

    private boolean isFuture(Event event) {
        Date today = Date.today();
        boolean result = event.getDate().getYear() >= today.getYear() &&
                event.getDate().getMonth() >= today.getMonth() &&
                event.getDate().getDay() >= today.getDay();

        if (!result) {
            System.out.println(event.getDate().toString() + ": eventorganizer.Event date must be a future date!");
        }
        return result;
    }

    private boolean isFar(Event event) {
        Date today = Date.today();
        boolean result = event.getDate().getYear() >= today.getYear() &&
                event.getDate().getMonth() >= today.getMonth() + FAR_MONTHS &&
                event.getDate().getDay() >= today.getDay();

        if (!result) {
            System.out.println(event.getDate().toString() +
                    ": eventorganizer.Event date must be within 6 months!");
        }
        return result;
    }

    private boolean isValidTimeslot(Event event) {
        for (Timeslot ts : Timeslot.values()) {
            if (event.getStartTime().equals(ts))
                return true;
        }
        System.out.println("Invalid time slot!");
        return false;
    }

    private boolean isValidDuration(Event event) {
        int duration = event.getDuration();
        boolean result = duration >= 30 && duration <= 120;
        if (!result) {
            System.out.println("eventorganizer.Event duration must be at least 30 minutes and at most 120 minutes");
        }
        return result;
    }

    private boolean isValidLocation(Event event) {
        for (Location l : Location.values()) {
            if (event.getLocation().equals(l))
                return true;
        }
        System.out.println("Invalid location!");
        return false;
    }

    private boolean isValidContact(Event event) {
        boolean result = event.getContact().isValid();
        if (!result) {
            System.out.println("Invalid contact information!");
        }
        return result;
    }

    private boolean isConflict(Event event) {
        boolean conflict = false;
        for (Event e : calendar.getEvents()) {
            if (e.compareTo(event) == 0) {
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
     * Runs the R command:
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

        if (!date.isValid()) {
            System.out.println(date.toString() + ": Invalid calendar date!");
            return;
        }

        boolean eventFound = false;
        for (Event e : calendar.getEvents()) {
            if (e.getDate().compareTo(date) == 0 &&
                e.getStartTime().equals(timeslot) &&
                e.getLocation().equals(location))
            {
                calendar.remove(e);
                eventFound = true;
                System.out.println("eventorganizer.Event has been removed from the calendar!");
                break;
            }
        }
        if (!eventFound)
            System.out.println("Cannot remove; event is not in the calendar!");
    }

    /**
     * Runs the P, PE, PC, or PD command:
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
            default -> System.out.println(cmd[0] + "is an invalid command!");
        };
    }





    /**
     * Reads and parses user input commands.
     */
    public void run() {
        System.out.println("Event Organizer running...");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while (currLine.charAt(0) != 'Q') {
            if (!currLine.isBlank()) {
                String[] cmd = currLine.split("\\s+");
                runCmd(cmd);
            }
            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Event Organizer terminated.");
    }
}
