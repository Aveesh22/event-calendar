package eventorganizer;

/**
 * Enum class to describe indices for the array of tokens in an A command.
 */
public enum Command {
    COMMAND (0),
    DATE (1),
    TIME (2),
    ROOM (3),
    DEPARTMENT (4),
    EMAIL (5),
    DURATION (6);

    private final int index;

    /**
     * Constructor for the command class which initializes the index variable
     * @param index the index to be initialized
     */
    Command(int index) {
        this.index = index;
    }

    /**
     * Returns the index of a given enum
     * @return the index of the given enum
     */
    public int getIndex()
     {
        return index;
    }
}
