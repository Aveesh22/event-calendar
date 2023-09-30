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

    Command(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
