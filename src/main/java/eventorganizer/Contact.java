package eventorganizer;

/**
 * This class defines a Contact which includes a department and email.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public class Contact
{
    private Department department;
    private String email;

    Contact (Department department, String email) {
        this.department = department;
        this.email = email;
    }

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

    /**
     * Checks if a Contact's department name and email are valid.
     * @return true if both department and email are valid
     */
    public boolean isValid() {
        return (isValidDepartment() && isValidEmail());
    }

    /**
     * Checks if a Contact's department name is valid.
     * @return true if the department is real
     */
    public boolean isValidDepartment() {
        if (department == null)
            return false;
        else {
            boolean validDep = false;
            for (Department d : Department.values()) {
                if (department.getFullName().equals(d.getFullName())) {
                    validDep = true;
                    break;
                }
            }
            return validDep;
        }
    }

    /**
     * Checks if a Contact's department name and email are valid.
     * @return true if the email is correct for this department
     */
    public boolean isValidEmail() {
        return email.equals(new Contact(department).getEmail());
    }


}
