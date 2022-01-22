package clothing;

import clothing.trait.*;

import java.util.Objects;

public class Pants extends Clothing {
    // A class to define an article of clothing which covers legs individually

    public static final Type type = Type.PANTS;

    private Length length;
    private Function function;
    private Length waist;
    private boolean pockets;
    private boolean beltLoops;

    // Constructors
    public Pants() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Pants(Length length, Function function, Length waist, boolean pockets, boolean beltLoops) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
    }

    public Pants(Material material, Textile textile, Color color, Warmth warmth,
                 Fastener fastener, int usesPerCleanLevel, Length length, Function function,
                 Length waist, boolean pockets, boolean beltLoops) {
        // Constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
    }

    // Mutators
    public void setLength(Length length) {
        if (this.length == null)
            this.length = length;
    }

    public void setWaist(Length waist) {
        if (this.waist == null)
            this.waist = waist;
    }

    public void setPockets(boolean pockets) {
        this.pockets = pockets;
    }

    public void setBeltLoops(boolean beltLoops) {
        this.beltLoops = beltLoops;
    }

    public void setFunction(Function function) {
        if (this.function == null)
            this.function = function;
    }


    // Accessors
    public Type getType() {
        return Type.PANTS;
    }

    public Length getLength() {
        return length;
    }

    public Length getWaist() {
        return waist;
    }

    public boolean getPockets() {
        return pockets;
    }

    public boolean getBeltLoops() {
        return beltLoops;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public boolean possiblyCompatible(Clothing item) {
        // A preliminary checker of compatibility with other clothing items
        // POST-CONDITION: if the item could be compatible, true is returned. Otherwise, false
        if (item instanceof Pants || item instanceof Dress)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    public String getName() throws NullPointerException {
        // POST-CONDITION: a descriptive name is returned
        // example: black, woven, cotton long pants with a zipper
        String base = length.toString().toLowerCase() + " pants";

        if (!Objects.equals(getDetail(), ""))
            return getName(base) + " with " + getDetail();

        return getName(base);
    }
}
