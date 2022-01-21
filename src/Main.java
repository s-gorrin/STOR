import clothing.*;
import clothing.trait.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Clothing> closet = new HashMap<>();

        //System.out.println(Instant.now());

        int nextID = 1;

        closet.put(nextID++, new Dress(Length.NONE, Length.MEDIUM, Volume.LOOSE,
                Neckline.BOAT, false, Function.FORMAL));
        closet.put(nextID++, new Top());
        closet.put(nextID++, new Top(Length.LONG, Length.LONG, Neckline.CREW, Function.CASUAL));
        closet.put(nextID, new Pants());

        closet.get(4).setColor(Color.BLACK);
        closet.get(3).setColor(Color.ORANGE);
        closet.get(3).setTextile(Textile.TIGHT_KNIT);
        closet.get(2).setColor(Color.GRAY);

        try {
            System.out.println(closet.get(2).getName());
        }
        catch (NullPointerException e) {
            System.out.printf("the %s is missing some data%n", closet.get(2).getType());
        }

        // Just adding some values. Closet will eventually be a class with methods
        for (Clothing item : closet.values()) {
            item.setColor(Color.BLACK);
            item.setMaterial(Material.COTTON);
            item.setFastener(Fastener.NONE);
            item.setTextile(Textile.BASIC_WEAVE);
            item.setWarmth(Warmth.NEUTRAL);
            item.setUsesPerCleanLevel(1);

            try {
                System.out.println(item.getName()); // print descriptions
            }
            catch (NullPointerException e) {
                System.out.printf("%s, ID: %d: missing data%n", item.getType(), item.getID());
            }
        }

    }
}
