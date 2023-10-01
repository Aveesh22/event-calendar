package eventorganizer;

import java.util.Scanner;

/**
 * This class handles user input and running commands.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class EventOrganizer
{
    private String currLine; //current line being parsed
    private EventCalendar calendar = new EventCalendar();

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
     * Runs the A command:
     * Adds an event to the event calendar.
     * We can assume the user will always enter enough data tokens.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdA(String[] cmd) {
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

        if (eventisValid()) { //need to check conditions before adding an event
            if (calendar.add(event))
                return "eventorganizer.Event added to the calendar.";
            else
                return "unable to add";
        }
        else
            return "not valid";
    }

    /**
     * Runs the R command:
     * Cancels and removes an event from the event calendar.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdR(String[] cmd) {
        return "output";
    }

    /**
     * Runs the P, PE, PC, or PD command:
     * Displays the event calendar on the console.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdP(String[] cmd) {
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
            return "* end of event calendar *";
        }
        else
            return "eventorganizer.Event calendar is empty!";
    }



    /**
     * Assuming the input is not blank, we run the command corresponding
     * to the command given. If it is invalid, we return the invalid response.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String runCmd(String[] cmd) {
        return switch (cmd[Command.COMMAND.getIndex()])
        {
            case "A" -> cmdA(cmd);
            case "R" -> cmdR(cmd);
            case "P", "PE", "PC", "PD" -> cmdP(cmd);
            default -> cmd[0] + "is an invalid command!";
        };
    }





    /**
     * Reads and parses user input commands.
     */
    public void run() {
        System.out.println("Event Organizer running...");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        currLine = scanner.nextLine();
        while (currLine.charAt(0) != 'Q') {
            if (!currLine.isBlank()) {
                String[] cmd = currLine.split("\\s+");
                System.out.println(runCmd(cmd));
            }
            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Event Organizer terminated.");
    }
}
