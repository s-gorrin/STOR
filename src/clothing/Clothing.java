package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * An abstract clothing class
 * It implements Sewable for most of its descriptive aspects,
 * and Trackable for most of its usage tracking.
 */
public abstract class Clothing implements Sewable, Trackable {

    // Sewable interface
    private Material material;
    private Textile textile;
    private Color color;
    private Warmth warmth;

    private Fastener fastener;
    private String detail = "";

    // Trackable interface
    private int totalUses;
    private int usesSinceCleaned;
    private final Instant added;
    private Instant lastUsed;
    private Clean cleanLevel;
    private int usesPerCleanLevel;

    private int ID; // the ID of the item in the Closet
    private boolean IDset;

    // Constructors

    /**
     * Constructor with no traits passed in
     */
    public Clothing() {
        added = Instant.now();

        totalUses = 0;
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
        usesPerCleanLevel = 1;

        IDset = false;
    }

    /**
     * Constructor with all user-defined traits passed in
     * @param material              What material it is made of
     * @param textile               How it is constructed
     * @param color                 What the primary color is
     * @param warmth                How warm it is to wear
     * @param fastener              How it is fastened
     * @param usesPerCleanLevel     How many uses before it changes cleanliness ratingl
     */
    public Clothing(Material material, Textile textile, Color color,
                    Warmth warmth, Fastener fastener, int usesPerCleanLevel) {
        added = Instant.now();

        totalUses = 0;
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
        this.usesPerCleanLevel = usesPerCleanLevel;

        this.material = material;
        this.textile = textile;
        this.color = color;
        this.warmth = warmth;
        this.fastener = fastener;

        IDset = false;
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
     */
    public Clothing(int ID, Instant added, int totalUses, int usesSinceCleaned, Clean cleanLevel,
                    Instant lastUsed, Material material, Textile textile, Color color,
                    Warmth warmth, Fastener fastener, int usesPerCleanLevel, String detail) {
        this.added = added;

        this.totalUses = totalUses;
        this.usesSinceCleaned = usesSinceCleaned;
        this.cleanLevel = cleanLevel;
        this.usesPerCleanLevel = usesPerCleanLevel;

        this.lastUsed = lastUsed;

        this.material = material;
        this.textile = textile;
        this.color = color;
        this.warmth = warmth;
        this.fastener = fastener;

        this.detail = detail;
        this.ID = ID;

        IDset = true;
    }

    // Mutators
    public void setMaterial(Material material) {
        // Set trait only if it does not already have one
        if (this.material == null)
            this.material = material;
    }

    public void setTextile(Textile textile) {
        // Set trait only if it does not already have one
        if (this.textile == null)
            this.textile = textile;
    }

    public void setColor(Color color) {
        // Set trait only if it does not already have one
        if (this.color == null)
            this.color = color;
    }

    public void setWarmth(Warmth warmth) {
        // Set trait only if it does not already have one
        if (this.warmth == null)
            this.warmth = warmth;
    }

    public void setFastener(Fastener fastener) {
        if (this.fastener == null)
            this.fastener = fastener;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setUsesPerCleanLevel(int uses) {
        // set a new usesPerCleanLevel only if one has not yet been set
        if (usesPerCleanLevel == 1)
            usesPerCleanLevel = uses;
    }

    public void setID(int ID) {
        if (!IDset) { // ID should only be set one time
            this.ID = ID;
            IDset = true;
        }
    }

    public void addUse() {
        // increment the use counters, update cleanLevel
        totalUses++;
        usesSinceCleaned++;

        if (usesSinceCleaned % usesPerCleanLevel == 0) {
            if (cleanLevel == Clean.FRESH)
                cleanLevel = Clean.OKAY;
            else if (cleanLevel == Clean.OKAY)
                cleanLevel = Clean.PASSABLE;
            else if (cleanLevel == Clean.PASSABLE)
                cleanLevel = Clean.WASH;
        }

        lastUsed = Instant.now(); // set to today
    }

    public void clean() {
        // reset the sinceCleaned counter, cleanLevel
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
    }

    // Accessors
    public abstract Type getType();

    public String getName(String childName) throws NullPointerException {
        // POST-CONDITION: a name, generated from traits, is returned
        // example: green, loose_knit, cotton

        StringBuilder name = new StringBuilder();
        name.append(color.toString().toLowerCase());

        // add textile to name if it makes sense
        if (material != Material.LEATHER && material != Material.OTHER && textile != Textile.OTHER) {
            name.append(", ");
            if (textile == Textile.BASIC_WEAVE)
                name.append("woven");
            else
                name.append(textile.toString().toLowerCase());
        }
        // add material to name if it makes sense
        if (material != Material.OTHER) {
            name.append(", ");
            name.append(material.toString().toLowerCase());
        }

        return name + " " + childName + Fastener.getDescription(fastener);
    }

    public Material getMaterial() {
        return material;
    }

    public Textile getTextile() {
        return textile;
    }

    public Color getColor() {
        return color;
    }

    public Warmth getWarmth() {
        return warmth;
    }

    public Fastener getFastener() {
        return fastener;
    }

    public String getDetail() {
        return detail;
    }

    public int getTotalUses() {
        return totalUses;
    }

    public int getUsesSinceCleaned() {
        return usesSinceCleaned;
    }

    public Instant getAdded() {
        return added;
    }

    public long getAge() {
        // POST-CONDITION: the number of days since the item was added is returned
        return added.until(Instant.now(), ChronoUnit.DAYS);
    }

    /** @return the last Instant the Clothing was used, or null */
    public Instant getLastUsed() {
        return lastUsed;
    }

    /** @return the index of the Clothing or -1 if it has not yet been assigned */
    public int getID() {
        return ID;
    }

    public int getUsesPerCleanLevel() {
        return usesPerCleanLevel;
    }

    public Clean getCleanLevel() {
        return cleanLevel;
    }

    public boolean possiblyCompatible(Clothing item) {
        // POST-CONDITION: the possibility of a clothing item being compatible is returned
        return Color.compatible(this.color, item.getColor());
    }
}
