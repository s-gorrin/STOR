/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: A generic container class to count and catalogue instances of traits in the Closet
 * This can be used to get a variety of information about a specific trait,
 * and is therefor likely to be generally useful
 * @param <E>       The thing being collected
 */

package closet;

import clothing.trait.Color;

import java.util.*;

public class TraitCounter<E extends Enum<E>> extends HashMap<Integer, E> {
    private static final int CAPACITY = 20; // the size of the longest trait enum plus a few to be safe

    public TraitCounter() {
        super();
    }

    /**
     * Count instances of a specific trait in the container
     * @param trait The thing being counted
     * @return      The number of occurrences of the thing being counted
     */
    public int countAttribute(E trait) {
        int counter = 0;

        for (E item : values()) {
            if (item.equals(trait))
                counter++;
        }

        return counter;
    }

    /**
     * Find the most common attribute of the relevant type
     * Note: due to type erasure, this just returns an ordinal value.
     *      the calling method will have to take that ordinal and find the corresponding value:
     *      Enum.values()[mostCommon(Enum)];
     * @return  the ORDINAL VALUE of the type of attribute with the most occurrences in the class
     */
    public int mostCommon() {
        int[] counts = new int[CAPACITY]; // initialized to 0s per language spec


        for (E item : super.values()) {
            counts[item.ordinal()]++;
        }

        int indexWithMost = 0;
        for (int i = 0; i < CAPACITY; i++) {
            if (counts[i] > counts[indexWithMost])
                indexWithMost = i;
        }

        return indexWithMost;
    }

    /**
     * Get a list of IDs that have the specific trait that is passed in
     *      For example, if E is Color, trait could be Color.BLACK.
     * @param trait The enum item being retrieved
     * @return      An array of IDs that have the trait
     */
    public int[] IDsWithTrait(E trait) {
        int[] IDs = new int[size()];
        int index = 0;

        for (int ID : keySet())
            if (get(ID) == trait)
                IDs[index++] = ID;

        // IDs array is deliberately over-sized, so return an array that is only as big as necessary
        int [] result = new int[index];
        System.arraycopy(IDs, 0, result, 0, index);

        return result;
    }

    /**
     * Determine if more than half of the items in the container have a given trait
     * @param trait the specific trait
     * @return      true if more than half of the container has the trait, false if not
     */
    public boolean moreThanHalf(E trait) {
        int count = countAttribute(trait);

        return size() / 2 < count;
    }

    public static void main(String[] args) {
        TraitCounter<Color> counter = new TraitCounter<>();

        counter.put(0, Color.BLACK);
        counter.put(1, Color.GREEN);
        counter.put(2, Color.PURPLE);
        counter.put(3, Color.BLACK);
        counter.put(4, Color.BLACK);
        counter.put(5, Color.BLACK);
        counter.put(6, Color.GREEN);

        System.out.println("Number of black clothes: " + counter.countAttribute(Color.BLACK));
        System.out.println("Most common color: " + Color.values()[counter.mostCommon()].toString().toLowerCase());
        System.out.print("IDs with the value green: ");
        for (int ID : counter.IDsWithTrait(Color.GREEN))
            System.out.print(ID + " ");
        System.out.println();
    }

}
