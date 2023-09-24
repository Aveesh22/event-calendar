package eventorganizer;

import java.util.Scanner;

/**
 * This class handles user input and running commands.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class EventOrganizer
{
    private String currLine; //current line being parsed

    /**
     * Performs the A command - Adds an event to the event calendar.
     * @return boolean indicating if the event was added successfully.
     */
    private boolean cmdA(String[] cmd) {
        if (cmd.length == 7) {
            Date date = new Date(cmd[1]);
            Timeslot timeslot = new Timeslot(cmd[2]);
            Location location = new Location(cmd[3]);
            Contact contact = new Contact(Department.CS); //cs for now as a placeholder
            int duration = Integer.parseInt(cmd[6]);
            Event event = new Event(cmd[1], cmd[2], cmd[3], contact, duration);
        }
        return false;
    }

    private void cmdR() {}

    private void cmdP() {}

    private void cmdPE() {}

    private void cmdPC() {}

    private void cmdPD() {}

    private String runCmd(String[] cmd) {
        String output = null;
        switch(cmd[0]) {
            case "A":
                cmdA(cmd);
                break;
            case "R":
                cmdR();
                break;
            case "P":
                cmdP();
                break;
            case "PE":
                cmdPE();
                break;
            case "PC":
                cmdPC();
                break;
            case "PD":
                cmdPD();
                break;
            default:
                output = "Incorrect Command";
                break;
        }
        return output;
    }





    /**
     * Reads and parses user input commands.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        currLine = scanner.nextLine();
        while (currLine.charAt(0) != 'Q') {
            String[] cmd = currLine.split("\\s+");
            String output = runCmd(cmd);
            System.out.println(output);

            currLine = scanner.nextLine();
        }
    }
}
