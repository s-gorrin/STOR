/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/01/2022
 * Desc: Categories for the main use of a clothing item
 */

package clothing.trait;

public enum Function {
    COMFORTABLE,
    CASUAL,
    ATHLETIC,
    WORK,
    FORMAL;

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

        list.append("functions:\n");

        for (Function name : values()) {
            list.append(i++).append(". ").append(name.toString().toLowerCase()).append("\n");
        }

        list.replace(list.length() - 1, list.length(), "");

        return list.toString();
    }

    /**
     * Get an enum item from a 1-indexed integer
     * @param i the index + 1 of the item to be retrieved
     * @return  the Clean item
     */
    public static Function get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i];
    }
}
