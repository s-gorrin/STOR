package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.util.Objects;

/**
 * A class to define an article of clothing that covers the top half of the body
 */
public class Top extends Clothing {

    private Length sleeves;
    private Length length;
    private Neckline neck;
    private Function function;

    // Constructors
    public Top() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Top(Length length, Function function, Length sleeves, Neckline neck) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.sleeves = sleeves;
        this.length = length;
        this.neck = neck;
        this.function = function;
    }

    public Top(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
               int usesPerCleanLevel, Length length, Function function, Length sleeves, Neckline neck) {
        // constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.sleeves = sleeves;
        this.length = length;
        this.neck = neck;
        this.function = function;
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
     * @param length                Child attribute: hem length
     * @param function              Child attribute: function
     * @param sleeves               Child attribute: sleeve length
     * @param neck                  Child attribute: neck type
     */
    public Top(int ID, Instant added, int totalUses, int usesSinceCleaned, Clean cleanLevel,
               Instant lastUsed, Material material, Textile textile, Color color,
               Warmth warmth, Fastener fastener, int usesPerCleanLevel, String detail,
               Length length, Function function, Length sleeves, Neckline neck) {
        super(ID, added, totalUses, usesSinceCleaned, cleanLevel, lastUsed, material, textile,
                color, warmth, fastener, usesPerCleanLevel, detail);

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
    }


    // Mutators
    public void setSleeves(Length sleeves) {
        if (this.sleeves == null)
            this.sleeves = sleeves;
    }

    public void setLength(Length length) {
        if (this.length == null)
            this.length = length;
    }

    public void setNeck(Neckline neck) {
        if (this.neck == null)
            this.neck = neck;
    }

    public void setFunction(Function function) {
        if (this.function == null)
            this.function = function;
    }

    // Accessors

    public Type getType() {
        return Type.TOP;
    }

    public Length getSleeves() {
        return sleeves;
    }

    public Length getLength() {
        return length;
    }

    public Neckline getNeck() {
        return neck;
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public boolean possiblyCompatible(Clothing item) {
        // A preliminary checker of compatibility with other clothing items
        // POST-CONDITION: if the item could be compatible, true is returned. Otherwise, false
        if (item instanceof Top || item instanceof Dress)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    public String getName() throws NullPointerException {
        // POST-CONDITION: a descriptive name is returned
        // example: black, woven, silk top with buttons

        if (!Objects.equals(getDetail(), ""))
            return getName("top") + " with " + getDetail();

        return getName("top");
    }
}
