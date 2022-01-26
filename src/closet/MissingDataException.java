package closet;

/**
 * An exception for if a ClosetArchiver file has missing data
 */
public class MissingDataException extends Exception {
    public MissingDataException(String message) {
        super(message);
    }
}
