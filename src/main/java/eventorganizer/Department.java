package eventorganizer;

/**
 * This enum class defines the departments.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public enum Department {
    CS ("Computer Science"),
    EE ("Electrical Engineering"),
    ITI ("Information Technology and Informatics"),
    MATH ("Mathematics"),
    BAIT ("Business Analytics and Information Technology");

    private final String fullName;

    /**
     * Parameterized constructor that initializes the fullName variable
     * @param fullName the variable to be initialized with the full name of the department
     */
    Department (String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * Gets the full name of the department
     * @return a String depicting the full name of the department
     */
    public String getFullName()
    {
        return fullName;
    }
}
