package clothing;

import clothing.trait.Type;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * A container class for clothing items to handle
 */
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
            if (clothing.type == type)
                count++;
        }

        return count;
    }

    /**
     * POST-CONDITION: a Set of all items in the closet is returned
     * @return  the set of all items
     */
    public Set<Integer> getAll() {
        return closet.keySet();
    }
}
