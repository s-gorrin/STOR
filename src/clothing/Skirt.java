package clothing;

import clothing.trait.*;

public class Skirt extends Clothing {
    // A class to define an article of clothing which covers both legs together

    public static final Type type = Type.SKIRT;

    private Length length;
    private Length waist;
    private Volume volume;
    private boolean pockets;
    private Function function;
    private Fastener fastener;

    public Skirt() {
        // default constructor, allowing class attributes to be set manually
        super();
    }
}
