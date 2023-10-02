package eventorganizer;

/**
 * This enum class defines the locations an Event can be hosted.
 * @author Patryk Dziedzic, Aveesh Patel
 */
public enum Location {
    AB2225 ("Academic Building", "College Avenue"),
    ARC103 ("Allison Road Classroom", "Busch"),
    BE_AUD ("Beck Hall", "Livingston"),
    HLL114 ("Hill Center", "Busch"),
    MU302 ("Murray Hall", "College Avenue"),
    TIL232 ("Tillett Hall", "Livingston");

    private final String building;
    private final String campus;

    /**
     * Constructor to create the Location object
     * with the given building and campus.
     * @param building the building for the event
     * @param campus the campus for the event
     */
    Location (String building, String campus) {
        this.building = building;
        this.campus = campus;
    }

    /**
     * Get the building of the location
     * @return the building name as a String
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Get the campus of the location
     * @return the campus name as a String
     */
    public String getCampus() {
        return campus;
    }
}
