/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: A class to track compatibility of Clothing items, by ID
 */

package closet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Compatible {
    public static final String FILENAME = "comp.csv";

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
     * Remove a compatibility between two items but don't remove the whole items
     * @param first     first ID
     * @param second    second ID
     */
    public void remove(int first, int second) {
        record.get(first).remove(second);
        record.get(second).remove(first);
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
     * Store the compatibility record in a file.
     * File format: w, x, y, z
     * Where w is the ID and x, y, x are compatible IDs
     */
    public void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(FILENAME);

        for (Integer ID : record.keySet()) {
            StringBuilder line = new StringBuilder(ID + ", ");
            for (Integer val : record.get(ID))
                line.append(val).append(", ");

            line.replace(line.length() - 2, line.length(), "\n");
            writer.write(line.toString());
        }

        writer.flush();
        writer.close();
    }

    /**
     * Read the compatibility record from a file
     * For each line, index 0 is the ID,
     * and all subsequent numbers are compatible items with that ID
     */
    public void readFromFile() throws IOException {
        Scanner reader = new Scanner(new File(FILENAME));

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(", ");
            for (int i = 1; i < line.length; i++)
                add(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
        }

        reader.close();
    }
}
