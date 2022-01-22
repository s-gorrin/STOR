package clothing.trait;

/**
 * Weather types - more may be added later, or this may be replaced with a weather API
 */
public enum Weather {
    WARM_SUN,
    COLD_SUN,
    CLOUDS,
    RAIN,
    NIGHT,
    OTHER;

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        for (Weather name : values()) {
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
    public static Weather get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i-1];
    }
}
