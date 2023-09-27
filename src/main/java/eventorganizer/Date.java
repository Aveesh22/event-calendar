package eventorganizer;

import java.util.Calendar;

/**
 * This class defines a Date for an Event
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Date implements Comparable<Date>
{
    private int year;
    private int month;
    private int day;



    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int monthOffset = 1;



    /**
     * A parameterized constructor that takes a string in the form of "mm/dd/yyyy"
     * @param date string date as "mm/dd/yyyy"
     */
    public Date(String date) {}

    /**
     * A default constructor that creates a date with today's date.
     */
    public Date() {}

    /**
     * A copy constructor that clones a date object.
     */
    public Date(Date date) {}



    /**
     * This method compares 2 dates.
     */
    @Override
    public int compareTo(Date date) {
        return 0;
    }

    /**
     * Return the textual representation of a date.
     * @return date as string
     */
    @Override
    public String toString() { return month + "/" + day + "/" + year; }



    /**
     * Check if this date object is a valid calendar date.
     * @return
     */
    public boolean isValid() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month + monthOffset, day);

        //if month out of range
        if (month < Calendar.JANUARY + monthOffset || month > Calendar.DECEMBER + monthOffset) {
            return false;
        }
        //if it's February, check if it's a leap year and the day accordingly
        if (month == Calendar.FEBRUARY + monthOffset) {
            if (isLeap()) {
                if () //have to figure out how to check if out of range of month
                    return false;
                else return true;
            }
            else {
                if ()
                    return false;
                else return true;
            }
        }
    }



    /**
     * Check if this date object's year is a leap year.
     * @return true if it is a leap year, false otherwise
     */
    public boolean isLeap() {
        if (year % QUADRENNIAL == 0) { //evenly divisible by 4
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                }
                else return false;
            }
            else return true;
        }
        else return false;
    }

    /**
     * Return today's date.
     */
    public static Date today() { return new Date(); }








    /**
     * Testbed main to exercise the isValid() method.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
    }

    /**
     * Test Case #1
     */
    private static void testDaysInFeb_NonLeap() {
        Date date = new Date("2/29/2011"); //test data
        boolean expectedOutput = false; //define expected output
        boolean actualOutput = date.isValid(); //call the method to get the actual output
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28.");
        testResult(expectedOutput, actualOutput); //compare the result
    }

    /**
     * Test Case #2
     */
    private static void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2011");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a leap year is 29.");
        testResult(expectedOutput, actualOutput);
    }

    /**
     * Test Case #3
     */
    private static void testMonth_OutOfRange() {
        //code
    }

    /**
     * Check if a given test case is PASS or FAIL.
     * @param expectedOutput expected output
     * @param actualOutput actual output
     */
    private static void testResult(boolean expectedOutput, boolean actualOutput) {
        if (expectedOutput == actualOutput)
            System.out.println("PASS");
        else
            System.out.println("FAIL");
    }
}