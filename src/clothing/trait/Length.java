/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/01/2022
 * Desc: A length scale for various clothing properties
 */

package clothing.trait;

public enum Length {
    NONE,
    VERY_SHORT,
    SHORT,
    MID_SHORT,
    MEDIUM,
    MID_LONG,
    LONG;

    public static final int size;
    static {
        size = values().length;
    }

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        list.append("lengths:\n");

        for (Length name : values()) {
            list.append(i++).append(". ").append(
                    name.toString().toLowerCase().replace('_', ' ')).append("\n");
        }

        list.replace(list.length() - 1, list.length(), "");

        return list.toString();
    }

    /**
     * Get an enum item from a 1-indexed integer
     * @param i the index + 1 of the item to be retrieved
     * @return  the Clean item
     */
    public static Length get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i];
    }
}
