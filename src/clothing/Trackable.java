package clothing;

import clothing.trait.Clean;

import java.util.Date;

// An interface to define a trackable item
public interface Trackable {
    int totalUses = 0;
    int usesSinceCleaned = 0;
    Date added = new Date(0);
    Date lastUsed = new Date(0);
    Clean cleanLevel = Clean.FRESH;
    int usesPerCleanLevel = 1;

    // Setters
    void addUse(); // increment the use counters, update cleanLevel
    void clean(); // reset the sinceCleaned counter, cleanLevel
    void setAdded(Date added);
    void setLastUsed(Date lastUsed);

    // Getters
    int getTotalUses();
    int getUsesSinceCleaned();
    Date getAdded();
    int getAge();
}
