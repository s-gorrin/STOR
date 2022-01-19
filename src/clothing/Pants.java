package clothing;

import clothing.trait.*;

import java.util.Objects;

public class Pants extends Clothing {
    // A class to define an article of clothing which covers legs individually

    public static final Type type = Type.PANTS;

    private Length length;
    private Length waist;
    private boolean pockets;
    private boolean beltLoops;
    private Function function;

    // Constructors
    public Pants() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Pants(Length length, Length waist, boolean pockets, boolean beltLoops, Function function) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.length = length;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
        this.function = function;
    }

    public Pants(Material material, Textile textile, Color color, Warmth warmth,
                 Fastener fastener, int usesPerCleanLevel, Length length, Length waist,
                 boolean pockets, boolean beltLoops, Function function) {
        // Constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
        this.function = function;
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

        return Color.compatible(this.getColor(), item.getColor());
    }

    public String getName() {
        // POST-CONDITION: a descriptive name is returned
        // example: black, woven, cotton long pants with a zipper
        String base;

        if (length != null)
            base = length.toString().toLowerCase() + " pants";
        else
            base = "pants";

        if (getColor() == null || getTextile() == null || getMaterial() == null)
            return base + getFastenerDescription();

        if (!Objects.equals(super.getDetail(), ""))
            return super.getName() + " " + base + getFastenerDescription() + " with " + super.getDetail();

        return super.getName() + " " + base + getFastenerDescription();
    }
}
