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
     * Runs the A command:
     * Adds an event to the event calendar.
     * We can assume the user will always enter enough data tokens.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdA(String[] cmd) {
        /*
        Date date = new Date(cmd[1]);
        Timeslot timeslot = new Timeslot(cmd[2]);
        Location location = new Location(cmd[3]);
        Contact contact = new Contact(Department.CS); //cs for now as a placeholder
        int duration = Integer.parseInt(cmd[6]);
        Event event = new Event(cmd[1], cmd[2], cmd[3], contact, duration);
        calendar.add(event);
         */
        return "eventorganizer.Event added to the calendar.";
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
     * Runs the P command:
     * Displays the event calendar on the console.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdP(String[] cmd) {
        if (calendar.getNumEvents() > 0) {
            System.out.println("* eventorganizer.Event calendar *");
            calendar.print();
            System.out.println("* end of event calendar *");
        }
        else
            System.out.println("eventorganizer.Event calendar is empty!");

        return "output";
    }

    /**
     * Runs the PE command:
     * Displays the event calendar sorted by time on the console.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdPE(String[] cmd) {
        //how can we use the code in cmdP without copy-pasting??
        //all we need to do is change the calendar.print() to calendar.printByDate() or by campus etc.

        return "output";
    }

    /**
     * Runs the PC command:
     * Displays the event calendar sorted by room on the console.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdPC(String[] cmd) {
        return "output";
    }

    /**
     * Runs the PD command:
     * Displays the event calendar sorted by department on the console.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String cmdPD(String[] cmd) {
        return "output";
    }

    /**
     * Assuming the input is not blank, we run the command corresponding
     * to the command given. If it is invalid, we return the invalid response.
     * @param cmd The current input line as a String array of tokens
     * @return The String output for the console.
     */
    private String runCmd(String[] cmd) {
        String output = switch (cmd[0]) {
            case "A" -> cmdA(cmd);
            case "R" -> cmdR(cmd);
            case "P" -> cmdP(cmd);
            case "PE" -> cmdPE(cmd);
            case "PC" -> cmdPC(cmd);
            case "PD" -> cmdPD(cmd);
            default -> cmd[0] + "is an invalid command!";
        };
        return output;
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
                String output = runCmd(cmd);
                System.out.println(output);
            }

            currLine = scanner.nextLine();
        }

        System.out.println();
        System.out.println("Event Organizer terminated.");
    }
}
