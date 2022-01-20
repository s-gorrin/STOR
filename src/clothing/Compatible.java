package clothing;

import java.util.HashMap;
import java.util.HashSet;

/**
 * A class to track compatibility of Clothing items, by ID
 * (so they don't have to do it themselves)
 */
public class Compatible {

    private final HashMap<Integer, HashSet<Integer>> record;

    /**
     * Class constructor
     */
    public Compatible() {
        record = new HashMap<>();
    }

    /**
     * Add compatibility for two items
     * @param first     the ID of a Clothing item
     * @param second    the ID of a Clothing item
     */
    public void add(int first, int second) {
        if (first == second) { // no item is compatible with itself
            record.put(first, new HashSet<>());
            return;
        }

        // if first is compatible with second, the inverse is also true, so add each to the other's list
        if (!record.containsKey(first))
            record.put(first, new HashSet<>());
        record.get(first).add(second);

        if (!record.containsKey(second))
            record.put(second, new HashSet<>());
        record.get(second).add(first);
    }

    /**
     * Remove an item and all its connections from the compatibility record
     * @param ID    the ID to be removed
     * @return      true if the ID was present, false if not
     */
    public boolean remove(int ID) {
        for (HashSet<Integer> set : record.values())
            set.remove(ID);

        return record.remove(ID) != null;
    }

    /**
     * Check if two items are compatible with each other
     * @param first     the ID of a Clothing item
     * @param second    the ID of a Clothing item
     * @return          true if they are compatible, false if not or if either is absent
     */
    public boolean check(int first, int second) {
        // make sure both items are present
        if (!record.containsKey(first) || !record.containsKey(second))
            return false;

        return record.get(first).contains(second);
    }

    /**
     * Store the compatibility record in a file
     * @return true if successful, false if not
     */
    public boolean writeToFile() {
        return true;
    }

    /**
     * Read the compatibility record from a file
     * @return true if successful, false if not
     */
    public boolean readFromFile() {
        return true;
    }
}
