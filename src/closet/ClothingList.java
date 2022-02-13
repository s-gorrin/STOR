/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/12/2022
 * Desc: methods to get sub-list of clothing from the closet
 */

package closet;

import clothing.Clothing;

import java.util.*;
import java.util.stream.Collectors;

public class ClothingList {

    /**
     * get the n oldest items from the closet without using the ID field
     * @param closet    the closet
     * @param n         the number of items to get
     * @return          a List of items
     */
    public static List<Integer> nOldest(Closet closet, int n) {
        List<Clothing> allClothing = new ArrayList<>(closet.getAllClothing());
        allClothing.sort(Comparator.comparing(Clothing::getAdded));


        List<Integer> result = allClothing.stream().map(Clothing::getID).collect(Collectors.toList());
        if (result.size() >= n)
            return result.subList(0, n);
        return result;
    }

    /**
     * get the n least-used items from the closet without using the ID field
     * @param closet    the closet
     * @param n         the number of items to get
     * @return          a List of items
     */
    public static List<Integer> nLeastUsed(Closet closet, int n) {
        List<Clothing> allClothing = new ArrayList<>(closet.getAllClothing());
        allClothing.sort(Comparator.comparingInt(Clothing::getTotalUses));

        List<Integer> result = allClothing.stream().map(Clothing::getID).collect(Collectors.toList());
        if (result.size() >= n)
            return result.subList(0, n);
        return result;
    }

}
