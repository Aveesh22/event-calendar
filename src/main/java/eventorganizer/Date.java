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
    public static final int MONTH_OFFSET = 1;
    public static final int MAX_MONTH = 12; //Calendar class has 0-index months



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
        month = cal.get(Calendar.MONTH) + MONTH_OFFSET;
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
        //year
        if (this.year > date.year)
            return 1;
        else if (this.year < date.year)
            return -1;
        else {
            //month
            if (this.month > date.month)
                return 1;
            else if (this.month < date.month)
                return -1;
            else {
                //day
                return Integer.compare(this.day, date.day);
            }
        }
    }

    /**
     * Return the textual representation of a date.
     * @return date as string
     */
    @Override
    public String toString() { return month + "/" + day + "/" + year; }



    /**
     * Checks if this Date object is a valid calendar date.
     * - year must be in range
     * - month must be in range
     * - day must be in range for the corresponding month
     * - February must be at most 29 if it is a leap year, 28 otherwise
     * @return true if the Date object is valid, false otherwise
     */
    public boolean isValid() {
        return validYear() && validMonth() && validDay();
    }

    /**
     * Checks if this Date object's year is valid.
     * @return true if the year is after 1900
     */
    private boolean validYear() {
        return year > 1900;
    }

    /**
     * Checks if this Date object's month is valid.
     * @return true if the month is between 1 and MAX_MONTH
     */
    private boolean validMonth() {
        return month >= 1 && month <= MAX_MONTH;
    }

    /**
     * Checks if this Date object's day is valid.
     * @return true if the day is valid for the corresponding month
     */
    private boolean validDay() {
        for (Month month: Month.values()) {
            if (this.month == month.getMonthNumber()) {
                if (this.month == Month.FEBRUARY_LEAP.getMonthNumber()) {
                    if (isLeap()) {
                        return day >= 1 && day <= Month.FEBRUARY_LEAP.getTotalDays();
                    }
                    else return day >= 1 && day <= Month.FEBRUARY_NONLEAP.getTotalDays();
                }
                return day >= 1 && day <= month.getTotalDays();
            }
        }
        return false;
    }

    /**
     * Checks if this date object's year is a leap year.
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
     * Get today's date.
     * @return today's date
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
        Date date = new Date("13/29/2023");
        boolean expectedOutput = false;
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
        System.out.println("Test Input: " + date.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.print("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput)
            System.out.println(" (PASS)\n");
        else
            System.out.println(" (FAIL)\n");
    }
}