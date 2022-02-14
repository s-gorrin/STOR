package closet;

import clothing.ClosetSerializer;
import clothing.Pants;
import clothing.Top;
import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class ClosetArchiverTest {

    @Test
    public void save() {
        ClosetArchiver.setTest();
        Closet closet = new Closet();
        closet.add(new Pants(Material.WOOL, Textile.TWILL, Color.GRAY,  Warmth.WARM, Fastener.BUTTON,
                2, Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        closet.get(0).addUse();
        closet.get(0).addUse();

        ClosetArchiver.save(closet);
        File file = new File(ClosetArchiver.TEST_FILE);
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

        try { Files.deleteIfExists(new File(ClosetArchiver.TEST_FILE).toPath()); }
        catch (IOException e) {
            System.out.println("Somehow the file did not exist.");
        }
        System.out.println("please manually delete test/archive-#.csv file");
    }

    @Test
    public void retrieve() {
        ClosetArchiver.setTest();
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

        closet.add(new Top());
        ClosetArchiver.save(closet);

        Closet badSave = ClosetArchiver.retrieve();
        Assert.assertFalse(badSave.contains(1)); // the new Top was not in the retrieved closet due to MissingData

        try {
            Files.deleteIfExists(new File(ClosetArchiver.TEST_FILE).toPath());
        }
        catch (IOException e) {
            System.out.println("Somehow the file did not exist.");
        }
        System.out.println("please manually delete test/archive-#.csv files");
    }
}