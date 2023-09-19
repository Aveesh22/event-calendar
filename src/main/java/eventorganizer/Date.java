package eventorganizer;

/**
 * This class defines ...
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



    /**
     * A parameterized constructor that takes a string in the form of "mm/dd/yyyy"
     * @param date
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
     * Return today's date.
     */
    public static Date today() {return new Date(); }

    /**
     * Check if this date object is a valid calendar date.
     * @return
     */
    public boolean isValid() {}

    /**
     * Return the textual representation of a date.
     * @return date as string
     */
    @Override
    public String toString() { return month + "/" + day + "/" + year; }


    /**
     * Testbed main to exercise the isValid method.
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
        testResult(date, expectedOutput, actualOutput); //compare the result
    }

    /**
     * Test Case #2
     */
    private static void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2011"); //test data
        boolean expectedOutput = true; //define expected output
        boolean actualOutput = date.isValid(); //call the method to get the actual output
        System.out.println("**Test case #2: # of days in Feb. in a leap year is 29.");
        testResult(date, expectedOutput, actualOutput); //compare the result
    }

    /**
     * Test Case #3
     */
    private static void testMonth_OutOfRange() {
        //code
    }

    /**
     * Check if a given test case is PASS or FAIL.
     * @param date date
     * @param expectedOutput expected output
     * @param actualOutput actual output
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        //code
    }
}