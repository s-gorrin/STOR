package clothing.trait;

/**
 * Measures of how warm an item can be, as in, how warm it will make the wearer
 */
public enum Warmth {
    HOT,
    WARM,
    NEUTRAL,
    COOL,
    COLD;

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        for (Warmth name : values()) {
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
    public static Warmth get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i-1];
    }
}
