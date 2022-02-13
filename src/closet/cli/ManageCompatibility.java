/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/01/2022
 * Desc: Manage the user IO for determining clothing compatibility
 */

package closet.cli;

import closet.Closet;
import closet.Compatible;
import clothing.Clothing;

public class ManageCompatibility {

    /**
     * prompt to add all compatibility with a new clothing item
     * @param item          The clothing being checked
     * @param closet        The closet where the other clothing is
     * @param compatible    The compatibility record
     */
    public static void add(Clothing item, Closet closet, Compatible compatible) {
        for (int ID : closet.getAllIDs())
            if (item.possiblyCompatible(closet.get(ID)) && ID != item.getID())
                if (Menu.bool("Is " + item.getName() + " compatible with " + closet.get(ID).getName() + "?"))
                    compatible.add(item.getID(), ID);
    }
}
