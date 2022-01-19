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
    BLUE,
    PURPLE,
    PATTERN,
    OTHER;

    public static boolean compatible(Color self, Color other) {
        // A static function to determine if two clothing colors are compatible
        // POST-CONDITION: true or false is returned, indicating compatible colors or not

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
}
