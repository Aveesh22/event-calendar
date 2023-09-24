package eventorganizer;

/**
 * This enum class defines the departments.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public enum Department {
    CS ("computer science"),
    EE ("electrical engineering"),
    ITI ("information technology and informatics"),
    MATH ("mathematics"),
    BAIT ("business analytics and information technology");

    private final String fullName;

    Department (String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
