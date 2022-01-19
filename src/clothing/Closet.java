package clothing;

import clothing.trait.Type;

import java.util.HashMap;

public class Closet {
    // A container class for clothing items

    private final HashMap<Integer, Clothing> closet;
    private int ID;

    public Closet() {
        closet = new HashMap<>();
        ID = 0;
    }

    // Mutators
    public int add(Clothing item) {
        // Add an item to the closet and an ID is assigned to it
        // POST_CONDITION: The ID of the added item is returned
        closet.put(++ID, item);
        return ID;
    }

    // Accessors
    public Clothing get(int ID) {
        // POST-CONDITION: The item corresponding with the given ID is returned
        if (ID > 0 && ID <= this.ID)
            return closet.get(ID);
        return null;
    }

    public int getLatestID() {
        // POST-CONDITION: The most recently added ID is returned, or 0 if there are no clothes
        // This can be used to loop through the closet with a for loop as in:
        //      for (int i = 1; i <= closet.getLatestID(); i++) {}
        return ID;
    }

    public int getNumberOf(Type type) {
        // POST-CONDITION: the number of items of the specified type is returned
        int count = 0;

        for (int i = 1; i <= ID; i++) {
            if (closet.get(i).type == type)
                count++;
        }

        return count;
    }
}
