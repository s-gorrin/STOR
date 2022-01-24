package clothing.trait;

/**
 * The basic sorts of fullness/volume a skirt or dress can have
 */
public enum Volume {
    TIGHT,
    LOOSE,
    FLOWY,
    CIRCLE;

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        for (Volume name : values()) {
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
    public static Volume get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i-1];
    }
}