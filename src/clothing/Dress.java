package clothing;

import clothing.trait.*;

import java.util.Objects;

public class Dress extends Clothing {
    // A class to define an article of clothing that covers the whole body

    public static final Type type = Type.DRESS;

    private Length sleeves;
    private Length length;
    private Volume volume;
    private Neckline neck;
    private boolean pockets;
    private Function function;

    // Constructors

    public Dress() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Dress(Length sleeves, Length length, Volume volume, Neckline neck, boolean pockets, Function function) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.sleeves = sleeves;
        this.length = length;
        this.volume = volume;
        this.neck = neck;
        this.pockets = pockets;
        this.function = function;
    }

    public Dress(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
                 int usesPerCleanLevel, Length sleeves, Length length, Volume volume, Neckline neck,
                 boolean pockets, Function function) {
        // constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);


        this.sleeves = sleeves;
        this.length = length;
        this.volume = volume;
        this.neck = neck;
        this.pockets = pockets;
        this.function = function;
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

    @Override
    public String getName() {
        // POST-CONDITION: a descriptive name is returned
        // example: yellow, woven, cotton long dress with a drawstring
        String base;

        if (length != null)
            base = length.toString().toLowerCase() + " dress";
        else
            base = "dress";

        if (getColor() == null || getTextile() == null || getMaterial() == null)
            return base + getFastenerDescription();

        if (!Objects.equals(super.getDetail(), ""))
            return super.getName() + " " + base + getFastenerDescription() + " with " + super.getDetail();

        return super.getName() + " " + base + getFastenerDescription();
    }

}
