package clothing.trait;

// Possible clothing fastener types
public enum Fastener {
    BUTTON,
    ZIPPER,
    DRAWSTRING,
    SNAP,
    ELASTIC,
    OTHER,
    NONE;

    public static String getDescription(Fastener fastener) {
        // POST-CONDITION: A descriptive phrase for each fastener is returned
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
}
