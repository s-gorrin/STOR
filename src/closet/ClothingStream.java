/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/05/2022
 * Desc: a class to pick an outfit from the contents of a closet
 */

package closet;

import clothing.Clothing;
import clothing.trait.Type;
import clothing.trait.Warmth;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// note: this has been somewhat replaced with the FXOutfit class, but my still be used in other functionality
public class ClothingStream {

    public enum Temperature {
        WARM, NEUTRAL, COLD
    }


    /**
     * use streams to get all the clothes within a temperature range from the Closet
     * @param temp  the requested temperature range
     * @return      a List of clothing IDs
     */
    public static Stream<Clothing> getByTemp(Closet closet, Temperature temp) {
        Stream<Clothing> clothes = closet.getAllClothing().stream();

        switch (temp) {
            case COLD:
                return clothes.filter(c -> c.getWarmth() == Warmth.COOL || c.getWarmth() == Warmth.COLD);
            case WARM:
                return clothes.filter(c -> c.getWarmth() == Warmth.WARM || c.getWarmth() == Warmth.HOT);
            default:
                return clothes.filter(c -> c.getWarmth() != Warmth.COLD && c.getWarmth() != Warmth.HOT);
        }
    }

    /**
     * Take a closet, convert it to a stream, and call stream getByType with it
     * @param closet    a closet
     * @param type      the type to filter by
     * @return          a filtered stream
     */
    public static Stream<Clothing> getByType(Closet closet, Type type) {
        return getByType(closet.getAllClothing().stream(), type);
    }

    /**
     * get the IDs of all clothing items of a given type
     * @param type  The type to be gotten
     * @return      a List of IDs of the type
     */
    public static Stream<Clothing> getByType(Stream<Clothing> clothes, Type type) {

        switch (type) {
            case TOP:
                return clothes.filter(c -> c.getType() == Type.TOP);
            case PANTS:
                return clothes.filter(c -> c.getType() == Type.PANTS);
            case SKIRT:
                return clothes.filter(c -> c.getType() == Type.SKIRT);
            case DRESS:
                return clothes.filter(c -> c.getType() == Type.DRESS);
            default:
                return clothes;
        }
    }



}
