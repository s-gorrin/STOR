/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: A class to define an article of clothing which covers legs individually
 */

package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.util.Objects;

public class Pants extends Clothing {

    public static final Type type = Type.PANTS;

    private Length length;
    private Function function;
    private Length waist;
    private boolean pockets;
    private boolean beltLoops;

    // Constructors

    /**
     * default constructor, allowing class attributes to be set manually
     */
    public Pants() {
        super();
    }

    /**
     * constructor with passed-in child class attributes but no parent attributes
     * @param length     how long it is going down
     * @param function   when to wear it
     * @param waist      how high the waist is
     * @param pockets    does it have functional pockets
     * @param beltLoops  does it have functional belt loops
     */
    public Pants(Length length, Function function, Length waist, boolean pockets, boolean beltLoops) {
        super();

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
    }

    /**
     * Constructor with all parameters passed in
     * @param material              What material it is made of
     * @param textile               How it is constructed
     * @param color                 What the primary color is
     * @param warmth                How warm it is to wear
     * @param fastener              How it is fastened
     * @param usesPerCleanLevel     How many uses before it changes cleanliness rating
     * @param length                Child attribute: how long it is going down
     * @param function              Child attribute: when to wear it
     * @param waist                 Child attribute: how high the waist is
     * @param pockets               Child attribute: does it have functional pockets
     * @param beltLoops             Child attribute: does it have functional belt loops
     */
    public Pants(Material material, Textile textile, Color color, Warmth warmth,
                 Fastener fastener, int usesPerCleanLevel, Length length, Function function,
                 Length waist, boolean pockets, boolean beltLoops) {
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.waist = waist;
        this.pockets = pockets;
        this.beltLoops = beltLoops;
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
     * @param waist                 Child attribute: how high the waist is
     * @param pockets               Child attribute: does it have functional pockets
     * @param beltLoops             Child attribute: does it have functional belt loops
     */
    public Pants(int ID, Instant added, int totalUses, int usesSinceCleaned, Clean cleanLevel,
                 Instant lastUsed, Material material, Textile textile, Color color,
                 Warmth warmth, Fastener fastener, int usesPerCleanLevel, String detail,
                 Length length, Function function, Length waist, boolean pockets,
                 boolean beltLoops) {
        super(ID, added, totalUses, usesSinceCleaned, cleanLevel, lastUsed, material, textile,
                color, warmth, fastener, usesPerCleanLevel, detail);

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

    /**
     * a preliminary compatability checker
     * @param item  an instance of a type of Clothing
     * @return      true if possibly compatible, false if not
     */
    @Override
    public boolean possiblyCompatible(Clothing item) {
        if (item instanceof Pants || item instanceof Dress)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    /**
     * get a descriptive name from traits of the class
     * example: black, woven, cotton long pants with a zipper
     * @return                      a descriptive name
     * @throws NullPointerException if a data point is missing
     */
    public String getName() throws NullPointerException {
        String base = length.toString().toLowerCase() + " pants";

        if (!Objects.equals(getDetail(), ""))
            return getName(base) + " with " + getDetail();

        return getName(base);
    }
}
