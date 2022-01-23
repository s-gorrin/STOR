import clothing.*;
import clothing.trait.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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
                System.out.println(closet.get(ID).getID() + ":" + closet.get(ID).getName());
            }
            catch (NullPointerException e) {
                System.out.println(e + ": A class is missing some critical data.");
            }
        }

        ClosetArchiver.save(closet);


        try {
            Scanner reader = new Scanner(new File(ClosetArchiver.FILENAME));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int count = 0;
                for (int i = 0; i < line.length(); ++i) {
                    if (line.charAt(i) == ',')
                        count++;
                }
                System.out.println(line.charAt(0) + "" + line.charAt(1) + ": " + count);
            }

            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Exception in Main: " + e);
            System.exit(0);
        }



    }
}
