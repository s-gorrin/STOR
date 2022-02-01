/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: A class to define an article of clothing that covers the whole body
 */

package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.util.Objects;

public class Dress extends Clothing {

    private Length length;
    private Function function;
    private Length sleeves;
    private Neckline neck;
    private Volume volume;
    private boolean pockets;

    // Constructors
    /**
     * default constructor, allowing class attributes to be set manually
     */
    public Dress() {
        super();
    }

    /**
     * constructor with passed-in child class attributes but no parent attributes
     * @param length    how long it is going down
     * @param function  when to wear it
     * @param sleeves   how long the sleeves are
     * @param neck      the type of neckline
     * @param volume    how much air fits under the skirt
     * @param pockets   does it have functional pockets
     */
    public Dress(Length length, Function function, Length sleeves, Neckline neck, Volume volume, boolean pockets) {
        super();

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
        this.volume = volume;
        this.pockets = pockets;
    }

    /**
     * constructor with everything passed-in
     * @param material              What material it is made of
     * @param textile               How it is constructed
     * @param color                 What the primary color is
     * @param warmth                How warm it is to wear
     * @param fastener              How it is fastened
     * @param usesPerCleanLevel     How many uses before it changes cleanliness rating
     * @param length                Child attribute: how long it is going down
     * @param function              Child attribute: when to wear it
     * @param sleeves               Child attribute: how long the sleeves are
     * @param neck                  Child attribute: the type of neckline
     * @param volume                Child attribute: how much air fits under the skirt
     * @param pockets               Child attribute: does it have functional pockets
     */
    public Dress(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
                 int usesPerCleanLevel, Length length, Function function, Length sleeves,
                 Neckline neck, Volume volume, boolean pockets) {
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
        this.volume = volume;
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
     * @param sleeves               Child attribute: how long the sleeves are
     * @param neck                  Child attribute: the type of neckline
     * @param volume                Child attribute: how much air fits under the skirt
     * @param pockets               Child attribute: does it have functional pockets
     */
    public Dress(int ID, Instant added, int totalUses, int usesSinceCleaned, Clean cleanLevel,
                 Instant lastUsed, Material material, Textile textile, Color color,
                 Warmth warmth, Fastener fastener, int usesPerCleanLevel, String detail,
                 Length length, Function function, Length sleeves, Neckline neck, Volume volume,
                 boolean pockets) {
        super(ID, added, totalUses, usesSinceCleaned, cleanLevel, lastUsed, material, textile,
                color, warmth, fastener, usesPerCleanLevel, detail);

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
        this.volume = volume;
        this.pockets = pockets;
    }

    // Mutators
    public void setLength(Length length) {
        if (this.length == null)
            this.length = length;
    }

    public void setFunction(Function function) {
        if (this.function == null)
            this.function = function;
    }

    public void setSleeves(Length sleeves) {
        if (this.sleeves == null)
            this.sleeves = sleeves;
    }

    public void setNeck(Neckline neck) {
        if (this.neck == null)
            this.neck = neck;
    }

    public void setVolume(Volume volume) {
        if (this.volume == null)
            this.volume = volume;
    }

    public void setPockets(boolean pockets) {
        this.pockets = pockets;
    }


    // Accessors
    public Type getType() {
        return Type.DRESS;
    }

    public Length getSleeves() {
        return sleeves;
    }

    public Length getLength() {
        return length;
    }

    public Volume getVolume() {
        return volume;
    }

    public Neckline getNeck() {
        return neck;
    }

    public boolean getPockets() {
        return pockets;
    }

    public Function getFunction() {
        return function;
    }

    /**
     * a preliminary compatability checker
     * @param item  an instance of a type of Clothing
     * @return      true if the items might be compatible, false if not
     */
    @Override
    public boolean possiblyCompatible(Clothing item) {
        if (item instanceof Dress || item instanceof Top || item instanceof Pants || item instanceof Skirt)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    /**
     * get a descriptive name from traits of the class
     * example: yellow, woven, cotton long dress with a drawstring
     * @return                      a descriptive name
     * @throws NullPointerException if a data point is missing
     */
    public String getName() throws NullPointerException {
        String base = length.toString().toLowerCase() + " dress";

        if (!Objects.equals(getDetail(), ""))
            return getName(base) + " with " + getDetail();

        return getName(base);
    }
}
