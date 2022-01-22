package clothing;

import clothing.trait.*;

import java.util.Objects;

/**
 * A class to define an article of clothing that covers the whole body
 */
public class Dress extends Clothing {

    private Length length;
    private Function function;
    private Length sleeves;
    private Neckline neck;
    private Volume volume;
    private boolean pockets;

    // Constructors

    public Dress() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Dress(Length length, Function function, Length sleeves, Neckline neck, Volume volume, boolean pockets) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
        this.volume = volume;
        this.pockets = pockets;
    }

    public Dress(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
                 int usesPerCleanLevel, Length length, Function function, Length sleeves,
                 Neckline neck, Volume volume, boolean pockets) {
        // constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.length = length;
        this.function = function;
        this.sleeves = sleeves;
        this.neck = neck;
        this.volume = volume;
        this.pockets = pockets;
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

    public void setPockets(boolean pockets) {
        this.pockets = pockets;
    }

    public void setFunction(Function function) {
        if (this.function == null)
            this.function = function;
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

    @Override
    public boolean possiblyCompatible(Clothing item) {
        // A preliminary checker of compatibility with other clothing items
        // POST-CONDITION: if the item could be compatible, true is returned. Otherwise, false
        if (item instanceof Dress || item instanceof Top || item instanceof Pants || item instanceof Skirt)
            return false;

        if (getColor() != null && item.getColor() != null)
            return Color.compatible(this.getColor(), item.getColor());

        return true;
    }

    public String getName() throws NullPointerException {
        // POST-CONDITION: a descriptive name is returned
        // example: yellow, woven, cotton long dress with a drawstring
        String base = length.toString().toLowerCase() + " dress";

        if (!Objects.equals(getDetail(), ""))
            return getName(base) + " with " + getDetail();

        return getName(base);
    }
}
