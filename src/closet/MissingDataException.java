/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: An exception for if a ClosetArchiver file has missing data
 */

package closet;

public class MissingDataException extends Exception {
    public MissingDataException(String message) {
        super(message);
    }
}
