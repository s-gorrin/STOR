package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

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

    private int ID = -1; // the index of the item in the Closet vector
    // this functionality is getting moved to Closet
    protected HashSet<Clothing> compatible; // clothes which can go with this one

    // Constructors
    public Clothing() {
        // Default constructor. Sets the age and some initial values
        added = Instant.now();

        totalUses = 0;
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
        usesPerCleanLevel = 1;

        compatible = new HashSet<>();
    }

    public Clothing(Material material, Textile textile, Color color,
                    Warmth warmth, Fastener fastener, int usesPerCleanLevel) {
        // Constructor with traits and uses per clean passed in
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

        compatible = new HashSet<>();
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
        if (ID == -1)
            this.ID = ID;
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

    public void addCompatible(Clothing item) {
        compatible.add(item);
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



    public boolean possiblyCompatible(Clothing item) {
        // POST-CONDITION: the possibility of a clothing item being compatible is returned
        return Color.compatible(this.color, item.getColor());
    }

    public boolean isCompatible(Clothing item) {
        // POST-CONDITION a boolean indicating whether the item is compatible with this is returned
        return compatible.contains(item);
    }

}
