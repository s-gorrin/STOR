package clothing.trait;

/**
 * Possible clothing fastener types
 */
public enum Fastener {
    BUTTON,
    ZIPPER,
    DRAWSTRING,
    SNAP,
    ELASTIC,
    OTHER,
    NONE;

    /**
     * A descriptive phrase for each fastener type
     * @param fastener  the fastener to be described
     * @return          the descriptive phrase
     */
    public static String getDescription(Fastener fastener) {
        if (fastener == null)
            return "";

        switch (fastener) {
            case BUTTON:
                return " with buttons";
            case ZIPPER:
                return " with a zipper";
            case DRAWSTRING:
                return " with a drawstring";
            case SNAP:
                return " with snaps";
            case ELASTIC:
                return " with elastic";
            default:
                return "";
        }
    }

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        for (Fastener name : values()) {
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
    public static Fastener get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i-1];
    }
}
