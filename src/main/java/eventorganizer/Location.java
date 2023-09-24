package eventorganizer;

/**
 * This enum class defines the locations an event can be hosted.
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

    Location (String building, String campus) {
        this.building = building;
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public String getCampus() {
        return campus;
    }
}
