package clothing.trait;

// Possible clothing colors and a generalized compatibility checker
public enum Color {
    BLACK,
    GRAY,
    WHITE,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    EMERALD,
    BLUE,
    NAVY,
    PURPLE,
    GREEN_PLAID,
    BLUE_PLAID,
    RED_PLAID,
    PATTERN,
    OTHER;

    // TODO: work with Sage to finish this
    public static boolean compatible(Color self, Color other) {
        // A static function to determine if two clothing colors are compatible
        // POST-CONDITION: true or false is returned, indicating compatible colors or not
        if (self == other)
            return true;

        switch (self) {
            case BLACK:
                return other != Color.BROWN;
            case GRAY:
                return other != Color.PATTERN;
            case WHITE:
                return true;
            case BROWN:
                return true;
            case RED:
                return other != Color.GREEN && other != Color.EMERALD;
            case ORANGE:
                return other == Color.BLACK || other == Color.WHITE ||
                        other == Color.YELLOW || other == Color.RED ||
                        other == Color.BROWN || other == Color.RED_PLAID;
            case YELLOW:
                return true;
            case GREEN:
                return other != Color.PURPLE && other != Color.RED && other != Color.RED_PLAID;
            case EMERALD:
                return other != Color.PURPLE;
            case BLUE:
                return other != Color.ORANGE;
            case NAVY:
                return other != Color.ORANGE;
            case PURPLE:
                return other != Color.GREEN && other != Color.ORANGE && other != Color.GREEN_PLAID;
            case GREEN_PLAID:
                return other != Color.ORANGE;
            case BLUE_PLAID:
                return other != Color.ORANGE;
            case RED_PLAID:
                return true;
            case PATTERN:
                return true;
            default:
                return true;
        }
    }
}
