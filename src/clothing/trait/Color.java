package clothing.trait;

/**
 * Possible clothing colors and a generalized compatibility checker
 */
public enum Color {
    BLACK,
    GRAY,
    WHITE,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    PATTERN,
    OTHER;

    /**
     * A static function to determine if two clothing colors are compatible
     * @param self  The Color of the calling class
     * @param other The Color of the comparing class
     * @return      True if the colors are compatible, false if not
     */
    public static boolean compatible(Color self, Color other) {
        switch (self) {
            case GRAY:
                return other != BROWN && other != ORANGE && other != YELLOW;
            case WHITE:
                return other != GRAY;
            case BROWN:
                return other != GRAY && other != ORANGE && other != YELLOW && other != PURPLE;
            case RED:
                return other != GREEN && other != YELLOW && other != PURPLE;
            case ORANGE:
                return other != BROWN && other != GREEN && other != BLUE && other != PURPLE;
            case YELLOW:
                return other != BROWN && other != RED && other != YELLOW && other != PURPLE;
            case GREEN:
                return other != RED && other != ORANGE && other != PURPLE;
            case BLUE:
                return other != ORANGE;
            case PURPLE:
                return other == BLACK || other == WHITE || other == BLUE;
            default:
                return true;
        }
    }

    /**
     * Format a numbered list of enum items for printing
     * @return a numbered String representation of the enum
     */
    public static String list() {
        StringBuilder list = new StringBuilder();
        int i = 1;

        for (Color name : values()) {
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
    public static Color get(int i) throws ArrayIndexOutOfBoundsException {
        return values()[i-1];
    }
}
