package clothing;

import clothing.trait.*;

public class Top extends Clothing {
    // A class to define an article of clothing that covers the top half of the body

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
        // constructor with passes in child class attributes but no parent attributes
        super();

        this.sleeves = sleeves;
        this.length = length;
        this.neck = neck;
        this.function = function;
    }

    public Top(Material material, Textile textile, Color color, Warmth warmth, int usesPerCleanLevel,
               Length sleeves, Length length, Neckline neck, Function function) {
        // An all-variable constructor
        super(material, textile, color, warmth, usesPerCleanLevel);

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

    public boolean possiblyCompatible(Clothing item) {
        // A preliminary checker of compatibility with other clothing items
        // POST-CONDITION: if the item could be compatible, true is returned. Otherwise, false
        // an item is not compatible with its own type
        if (item instanceof Top) // || item instanceof Dress
            return false;

        return Color.compatible(this.getColor(), item.getColor());
    }

    public String getName() {
        // POST-CONDITION: a descriptive name is returned
        // example: black, woven, silk top
        return super.getName() + " top";
    }
}
