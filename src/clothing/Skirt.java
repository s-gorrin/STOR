package clothing;

import clothing.trait.*;

import java.util.Objects;

/**
 * A class to define an article of clothing which covers both legs together
 */
public class Skirt extends Clothing {

    private Length length;
    private Function function;
    private Volume volume;
    private Length waist;
    private boolean pockets;

    public Skirt() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Skirt(Length length, Function function, Length waist, Volume volume, boolean pockets) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.length = length;
        this.function = function;
        this.volume = volume;
        this.waist = waist;
        this.pockets = pockets;
    }

    public Skirt(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
                 int usesPerCleanLevel, Length length, Function function, Length waist, Volume volume,
                 boolean pockets) {
        // constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.volume = volume;
        this.pockets = pockets;
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

    public void setVolume(Volume volume) {
        if (this.volume == null)
            this.volume = volume;
    }

    public void setPockets(boolean pockets) {
        this.pockets = pockets;
    }

    public void setFunction(Function function) {
        if (this.function == null)
            this.function = function;
    }


    // Accessors
    public Type getType() {
        return Type.SKIRT;
    }

    public Length getLength() {
        return length;
    }

    public Length getWaist() {
        return waist;
    }

    public Volume getVolume() {
        return volume;
    }

    public boolean getPockets() {
        return pockets;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public boolean possiblyCompatible(Clothing item) {
        // A preliminary checker of compatibility with other clothing items
        // POST-CONDITION: if the item could be compatible, true is returned. Otherwise, false
        if (item instanceof Dress || item instanceof Skirt || item instanceof Pants)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    public String getName() throws NullPointerException {
        // POST-CONDITION: a descriptive name is returned
        // example: yellow, woven, cotton long dress with a drawstring
        String base = length.toString().toLowerCase() + " skirt";

        if (!Objects.equals(getDetail(), ""))
            return getName(base) + " with " + getDetail();

        return getName(base);
    }
}
