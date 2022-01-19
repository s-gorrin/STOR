package clothing;

import clothing.trait.*;

import java.util.Objects;

public class Top extends Clothing {
    // A class to define an article of clothing that covers the top half of the body

    public static final Type type = Type.TOP;

    private Length sleeves;
    private Length length;
    private Neckline neck;
    private Function function;

    // Constructors
    public Top() {
        // default constructor, allowing class attributes to be set manually
        super();
    }

    public Top(Length sleeves, Length length, Neckline neck, Function function) {
        // constructor with passed-in child class attributes but no parent attributes
        super();

        this.sleeves = sleeves;
        this.length = length;
        this.neck = neck;
        this.function = function;
    }

    public Top(Material material, Textile textile, Color color, Warmth warmth, Fastener fastener,
               int usesPerCleanLevel, Length sleeves, Length length, Neckline neck, Function function) {
        // constructor with everything passed-in
        super(material, textile, color, warmth, fastener, usesPerCleanLevel);

        this.sleeves = sleeves;
        this.length = length;
        this.neck = neck;
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

    @Override
    public String getName() {
        // POST-CONDITION: a descriptive name is returned
        // example: black, woven, silk top with buttons
        if (getColor() == null || getTextile() == null || getMaterial() == null)
            return "a top" + getFastenerDescription();

        if (!Objects.equals(super.getDetail(), ""))
            return super.getName() + " top" + getFastenerDescription() + " with " + super.getDetail();

        return super.getName() + " top" + getFastenerDescription();
    }
}
