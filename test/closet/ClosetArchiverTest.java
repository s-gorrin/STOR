package closet;

import clothing.Pants;
import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClosetArchiverTest {

    @Test
    public void save() {
        Closet closet = new Closet();
        closet.add(new Pants(Material.WOOL, Textile.TWILL, Color.GRAY,  Warmth.WARM, Fastener.BUTTON,
                2, Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        closet.get(0).addUse();
        closet.get(0).addUse();

        ClosetArchiver.save(closet);
        File file = new File(ClosetArchiver.FILENAME);
        Assert.assertTrue(file.exists());

        Scanner reader;

        try {
            reader = new Scanner(file);
            String read = reader.nextLine();

            Assert.assertEquals(read.charAt(0), 'I');
            read = reader.nextLine();
            Assert.assertEquals(read.charAt(0), '0');
        }
        catch (FileNotFoundException e) {
            System.out.println("I asserted this exists two lines ago.. " + e);
        }

        closet = ClosetArchiver.retrieve();
        Assert.assertTrue(((Pants)closet.get(0)).getPockets());
    }

    @Test
    public void retrieve() {
        Closet closet = new Closet();
        closet.add(new Pants(Material.WOOL, Textile.TWILL, Color.GRAY,  Warmth.WARM, Fastener.BUTTON,
                2, Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        closet.get(0).addUse();
        closet.get(0).addUse();

        ClosetArchiver.save(closet);

        Closet savedCloset = ClosetArchiver.retrieve();

        Assert.assertEquals(savedCloset.get(0).getTextile(), Textile.TWILL);
        Assert.assertEquals(savedCloset.get(0).getTotalUses(), 2);
        Assert.assertEquals(savedCloset.get(0).getAdded(), closet.get(0).getAdded());
    }
}