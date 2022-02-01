/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: Abstract Clothing class which implements the Sewable and Trackable interfaces
 */

package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
    /**
     * Set trait only if it does not already have one
     * @param material  what is it made out of
     */
     public void setMaterial(Material material) {
        if (this.material == null)
            this.material = material;
    }

    /**
     * Set trait only if it does not already have one
     * @param textile   how the fabric is constructed
     */
    public void setTextile(Textile textile) {
        if (this.textile == null)
            this.textile = textile;
    }

    /**
     * Set trait only if it does not already have one
     * @param color what color is it
     */
    public void setColor(Color color) {
        if (this.color == null)
            this.color = color;
    }

    /**
     * Set trait only if it does not already have one
     * @param warmth    how warm it is
     */
    public void setWarmth(Warmth warmth) {
        if (this.warmth == null)
            this.warmth = warmth;
    }

    /**
     * Set trait only if it does not already have one
     * @param fastener  what type of fastener it has
     */
    public void setFastener(Fastener fastener) {
        if (this.fastener == null)
            this.fastener = fastener;
    }

    /**
     * set the detail parameter
     * @param detail    a descriptive detail about the item
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * set a new usesPerCleanLevel only if one has not yet been set
     * @param uses  how many times it can be used before the next level of dirtiness
     */
    public void setUsesPerCleanLevel(int uses) {
        if (usesPerCleanLevel == 1)
            usesPerCleanLevel = uses;
    }

    /**
     * set an ID - should only be able to happen once
     * @param ID    the ID in the closet and compatibility table
     */
    public void setID(int ID) {
        if (!IDset) { // ID should only be set one time
            this.ID = ID;
            IDset = true;
        }
    }

    /**
     * increment the use counters, update cleanLevel
     */
    public void addUse() {
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

    /**
     * reset the sinceCleaned counter, cleanLevel
     */
    public void clean() {
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
    }

    // Accessors
    public abstract Type getType();

    /**
     * get a descriptive name for the clothing from some of its traits
     * example: green, loose_knit, cotton
     * @param childName                 The name of the calling child class
     * @return                          a String containing the name
     * @throws NullPointerException     thrown if any name-relevant data is absent
     */
    public String getName(String childName) throws NullPointerException {
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

    /**
     * accessor
     * @return  what it's made of
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * accessor
     * @return  how it's constructed
     */
    public Textile getTextile() {
        return textile;
    }

    /**
     * accessor
     * @return  what color it is
     */
    public Color getColor() {
        return color;
    }

    /**
     * accessor
     * @return  how warm it is
     */
    public Warmth getWarmth() {
        return warmth;
    }

    /**
     * accessor
     * @return  what holds it shut
     */
    public Fastener getFastener() {
        return fastener;
    }

    /**
     * accessor
     * @return  a descriptive detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * accessor
     * @return  how many times has it been used
     */
    public int getTotalUses() {
        return totalUses;
    }

    /**
     * accessor
     * @return  how many times has it been used since cleaning
     */
    public int getUsesSinceCleaned() {
        return usesSinceCleaned;
    }

    /**
     * accessor
     * @return  when it was added
     */
    public Instant getAdded() {
        return added;
    }

    /**
     * how many days since the item was added
     * @return  the age of the item in days
     */
    public long getAge() {
        return added.until(Instant.now(), ChronoUnit.DAYS);
    }

    /**
     * accessor
     * @return the last Instant the Clothing was used, or null
     * */
    public Instant getLastUsed() {
        return lastUsed;
    }

    /**
     * accessor
     * @return the index of the Clothing or -1 if it has not yet been assigned
     * */
    public int getID() {
        return ID;
    }

    /**
     * accessor
     * @return  how many times it can be used per level of cleanliness
     */
    public int getUsesPerCleanLevel() {
        return usesPerCleanLevel;
    }

    /**
     * accessor
     * @return  how clean it is
     */
    public Clean getCleanLevel() {
        return cleanLevel;
    }

    /**
     * is the calling item possibly compatible with the passed-in item
     * currently this is just checking compatible colors
     * @param item  an instance of a type of Clothing
     * @return      true if they could be compatible, false if not
     */
    public boolean possiblyCompatible(Clothing item) {
        return Color.compatible(this.color, item.getColor());
    }
}
