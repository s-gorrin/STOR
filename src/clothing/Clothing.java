package clothing;

import clothing.trait.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/*
    An abstract clothing class
    It implements Sewable for most of its descriptive aspects,
    and Trackable for most of its usage tracking.
 */
public abstract class Clothing implements Sewable, Trackable {
    // Sewable interface
    private Material material;
    private Textile textile;
    private Color color;
    private Warmth warmth;

    // Trackable interface
    private int totalUses;
    private int usesSinceCleaned;
    private final Instant added;
    private Instant lastUsed;
    private Clean cleanLevel;
    private int usesPerCleanLevel;

    // Constructors
    public Clothing() {
        // Default constructor. Sets the age and some initial values
        added = Instant.now();

        totalUses = 0;
        usesSinceCleaned = 0;
        cleanLevel = Clean.FRESH;
        usesPerCleanLevel = 1;
    }

    public Clothing(Material material, Textile textile, Color color, Warmth warmth, int usesPerCleanLevel) {
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

    public void setUsesPerCleanLevel(int uses) {
        // set a new usesPerCleanLevel only if one has not yet been set
        if (usesPerCleanLevel == 1)
            usesPerCleanLevel = uses;
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
    public String getName() {
        // POST-CONDITION: a name, generated from traits, is returned
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

        return name.toString();
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

    public Instant getLastUsed() {
        // POST-CONDITION: the instant last used is returned. If it has never been used, null is returned
        if (lastUsed != null)
            return lastUsed;
        return null;
    }

}
