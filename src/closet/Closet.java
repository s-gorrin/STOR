/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: A container class for clothing to handle storage and recall of items
 */

package closet;

import clothing.Clothing;
import clothing.trait.Type;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Closet {
    // This is a HashMap because the ID is used in several places,
    // which means the ID cannot be an index, or removals would be very complicated
    private final HashMap<Integer, Clothing> closet;
    private int nextID;

    /**
     * Class constructor
     */
    public Closet() {
        closet = new HashMap<>();
        nextID = 0;
    }

    // Mutators
    /**
     * Add an item to the closet and set its ID attribute
     * POST_CONDITION: The index of the added item in the Vector is returned
     * @param item  the Clothing to be added
     * @return      the ID of the clothing that was added
     */
    public int add(Clothing item) {
        item.setID(nextID);
        closet.put(nextID, item);

        return nextID++;
    }

    /**
     * Add an item to the closet and set its ID attribute
     * POST_CONDITION: The index of the added item in the Vector is returned
     * @param item  the Clothing to be added
     * @param ID    the ID of the clothing, from a saved Closet file
     * @return      the ID of the clothing that was added
     */
    public int add(Clothing item, int ID) {
        item.setID(ID);
        closet.put(ID, item);

        nextID = ID + 1; // set up nextID for next non-closet addition
        return ID;
    }

    /**
     * POST-CONDITION: the Clothing corresponding to the ID is removed from closet and returned
     * @param ID    the ID of the item to be removed
     * @return      the Clothing that was removed
     */
    public Clothing remove(int ID) {
        return closet.remove(ID);
    }

    // Accessors

    /**
     * POST-CONDITION: The item corresponding with the given ID is returned
     * @param ID    the ID of the item to access
     * @return      the item corresponding with the given ID or null if it is absent
     */
    public Clothing get(int ID) {
        return closet.get(ID);
    }

    /**
     * POST-CONDITION: whether the key is in the closet is returned
     * @param ID    the item being checked
     * @return      true if the item is present, false if not
     */
    public boolean contains(int ID) {
        return closet.containsKey(ID);
    }

    /**
     * POST-CONDITION: The number of items in the closet is returned
     * @return the size of the closet
     */
    public int size() {
        return closet.size();
    }

    /**
     * POST-CONDITION: the number of items of the specified type is returned
     * @param type  the type of Clothing being counted
     * @return      the number of Clothing of that type in the closet
     */
    public int getNumberOf(Type type) {
        int count = 0;

        for (Clothing clothing : closet.values()) {
            if (clothing.getType() == type)
                count++;
        }

        return count;
    }

    /**
     * POST-CONDITION: a Set of all items in the closet is returned
     * @return  the set of all items
     */
    public Set<Integer> getAllIDs() {
        return closet.keySet();
    }

    /**
     * get all items in closet
     * @return  a Collection of all values in the HashMap
     */
    public Collection<Clothing> getAllClothing() {
        return closet.values();
    }
}
