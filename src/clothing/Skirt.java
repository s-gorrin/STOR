package clothing;

import clothing.trait.*;

import java.time.Instant;
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

    public Skirt(Length length, Function function, Volume volume, Length waist, boolean pockets) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.volume = volume;
        this.pockets = pockets;
    }

    /**
     * constructor with all passed-in attributes
     * @param material              What material it is made of
     * @param textile               How it is constructed
     * @param color                 What the primary color is
     * @param warmth                How warm it is to wear
     * @param fastener              How it is fastened
     * @param usesPerCleanLevel     How many uses before it changes cleanliness rating
     * @param length                Child attribute: how long it is going down
     * @param function              Child attribute: when to wear it
     * @param volume                Child attribute: how much air fits under the skirt
     * @param waist                 Child attribute: how high the waist is
     * @param pockets               Child attribute: does it have functional pockets
     */
    public Skirt(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
                 int usesPerCleanLevel, Length length, Function function, Volume volume, Length waist,
                 boolean pockets) {
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.volume = volume;
        this.waist = waist;
        this.pockets = pockets;
    }

    /**
     * A constructor for every parameter, including those that are normally set automatically,
     *      to recreate the item from a file.
     * @param ID                    Reference number for it in the Closet
     * @param added                 Instant of first add
     * @param totalUses             Number of times it has been used
     * @param usesSinceCleaned      Number of times it has been used since cleaning
     * @param cleanLevel            Current level of cleanliness
     * @param lastUsed              Instant of most recent use
     * @param material              What material it is made of
     * @param textile               How it is constructed
     * @param color                 What the primary color is
     * @param warmth                How warm it is to wear
     * @param fastener              How it is fastened
     * @param usesPerCleanLevel     How many uses before it changes cleanliness rating
     * @param detail                A descriptive detail about it
     * @param length                Child attribute: how long it is going down
     * @param function              Child attribute: when to wear it
     * @param volume                Child attribute: how much air fits under the skirt
     * @param waist                 Child attribute: how high the waist is
     * @param pockets               Child attribute: does it have functional pockets
     */
    public Skirt(int ID, Instant added, int totalUses, int usesSinceCleaned, Clean cleanLevel,
                 Instant lastUsed, Material material, Textile textile, Color color,
                 Warmth warmth, Fastener fastener, int usesPerCleanLevel, String detail,
                 Length length, Function function, Volume volume, Length waist, boolean pockets) {
        super(ID, added, totalUses, usesSinceCleaned, cleanLevel, lastUsed, material, textile,
                color, warmth, fastener, usesPerCleanLevel, detail);

        this.length = length;
        this.function = function;
        this.volume = volume;
        this.waist = waist;
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
