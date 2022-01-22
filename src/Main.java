import clothing.*;
import clothing.trait.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    /**
     * Currently an example class showing adding different Clothing types to the Closet
     * @param args none
     */
    public static void main(String[] args) {
        Closet closet = new Closet();

        for (int i = 0; i < 5; i++)
            closet.add(new Top());

        for (int i = 0; i < 4; i++)
            closet.add(new Dress());

        for (int i = 0; i < 3; i++)
            closet.add(new Pants());

        for (int i = 0; i < 2; i++)
            closet.add(new Skirt());

        for (int ID : closet.getAll()) {

            closet.get(ID).setColor(Color.BLACK);
            closet.get(ID).setTextile(Textile.BASIC_WEAVE);
            closet.get(ID).setMaterial(Material.COTTON);

            if (closet.get(ID).getType().equals(Type.DRESS))
                ((Dress)closet.get(ID)).setLength(Length.LONG);

            if (closet.get(ID).getType().equals(Type.PANTS))
                ((Pants)closet.get(ID)).setLength(Length.MEDIUM);

            if (closet.get(ID).getType().equals(Type.SKIRT))
                ((Skirt)closet.get(ID)).setLength(Length.SHORT);

            try {
                System.out.println(closet.get(ID).getName());
            }
            catch (NullPointerException e) {
                System.out.println(e + ": A class is missing some critical data.");
            }
        }

    }
}
