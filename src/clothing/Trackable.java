package clothing;

import clothing.trait.Clean;

import java.time.Instant;

// An interface to define a trackable item
public interface Trackable {
    int totalUses = 0;
    int usesSinceCleaned = 0;
    Instant added = Instant.now();
    Instant lastUsed = Instant.now();
    Clean cleanLevel = Clean.FRESH;
    int usesPerCleanLevel = 1;

    // Mutators
    void addUse(); // increment the use counters, update cleanLevel
    void clean(); // reset the sinceCleaned counter, cleanLevel

    // Accessors
    int getTotalUses();
    int getUsesSinceCleaned();
    Instant getAdded();
    long getAge();
    Instant getLastUsed();
}
