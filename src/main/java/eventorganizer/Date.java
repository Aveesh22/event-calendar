package eventorganizer;

import java.util.Calendar;
import java.util.Scanner;

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
    public Date(String date) {
        String[] tokens = date.split("/");
        year = Integer.parseInt(tokens[2]);
        month = Integer.parseInt(tokens[0]);
        day = Integer.parseInt(tokens[1]);
    }

    /**
     * A default constructor that creates a date with today's date.
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DATE);
    }

    /**
     * A copy constructor that clones a date object.
     */
    public Date(Date date) {
        year = date.year;
        month = date.month;
        day = date.day;
    }



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
     * - Month must be in range
     * - Date must be in range for the corresponding month
     * - February must be 29 if it is a leap year
     * @return
     */
    public boolean isValid() {
        Calendar cal = Calendar.getInstance();
        int calMonth = month - monthOffset;
        cal.set(year, calMonth, day);

        //if month out of range
        if (calMonth < Calendar.JANUARY || calMonth > Calendar.DECEMBER) {
            return false;
        }

        //if day is out of month range
        if (day < 1 || day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        //^ for some reason fails test 4
        /*
        //if it's February, check if it's a leap year and the day accordingly
        if (calMonth == Calendar.FEBRUARY) {
            if (isLeap()) {
                if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) //have to figure out how to check if out of range of month
                    return false;
                else return true;
            }
            else {
                //if ()
                //    return false;
                //else return true;
            }
        }
        else {
            //if the month is a big month (31 days)
            if (isBigMonth(calMonth)) {

            }
            else {

            }
        }
         */
        return true;
    }

    /**
     * Check if this date object's year is a leap year.
     * @return true if it is a leap year, false otherwise
     */
    private boolean isLeap() {
        if (year % QUADRENNIAL == 0) { //evenly divisible by 4
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                }
                else
                    return false;
            }
            else
                return true;
        }
        else
            return false;
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
        testDay_OutOfRange();

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter Month: ");
//        int inputMonth = scanner.nextInt(); System.out.println();
//        while (inputMonth != -20) {
//            int m = inputMonth - monthOffset;
//
//            Calendar cal = Calendar.getInstance();
//            cal.set(2020, m, 20);
//
//            //if month out of range
//            if (m < Calendar.JANUARY || m > Calendar.DECEMBER) {
//                System.out.println("month is out of bounds");
//            }
//
//            System.out.print("Enter Month: ");
//            inputMonth = scanner.nextInt(); System.out.println();
//        }
        System.out.println("done");
    }

    /**
     * Test Case #1
     */
    private static void testDaysInFeb_NonLeap() {
        Date date = new Date("2/29/2023"); //test data
        boolean expectedOutput = false; //define expected output
        boolean actualOutput = date.isValid(); //call the method to get the actual output
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28.");
        testResult(date, expectedOutput, actualOutput); //compare the result
    }

    /**
     * Test Case #2
     */
    private static void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2020");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a leap year is 29.");
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Test Case #3
     */
    private static void testMonth_OutOfRange() {
        Date date = new Date("2/29/2023");
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #3: # of months in a year is 12.");
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Test Case #4
     */
    private static void testDay_OutOfRange() {
        Date date = new Date("4/31/2023");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #4: # of days in Apr. is 30.");
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Check if a given test case is PASS or FAIL.
     * @param expectedOutput expected output
     * @param actualOutput actual output
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println("Date: " + date.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput)
            System.out.println("PASS");
        else
            System.out.println("FAIL");

        for (int i = 0; i < 75; i++) System.out.print("*");
        System.out.println();
    }
}