package eventorganizer;

/**
 * This class defines ...
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Contact
{
    private Department department;
    private String email;

    Contact (Department department) {
        this.department = department;
        this.email = department.name().toLowerCase() + "@rutgers.edu";
    }

    public Department getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValid() {}
}
