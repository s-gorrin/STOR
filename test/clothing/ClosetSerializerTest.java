package clothing;

import closet.Closet;
import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ClosetSerializerTest {

    @Test
    public void writeJSON() {
        ClosetSerializer.setTest();
        Closet closet = new Closet();
        closet.add(new Top(Length.LONG, Function.CASUAL, Length.MEDIUM, Neckline.COLLAR));
        closet.get(0).setColor(Color.GREEN);
        closet.add(new Top(Length.SHORT, Function.WORK, Length.LONG, Neckline.COWL));
        closet.add(new Top(Length.MEDIUM, Function.ATHLETIC, Length.SHORT, Neckline.CREW));
        closet.add(new Pants(Length.SHORT, Function.FORMAL, Length.LONG, true, true));
        closet.add(new Pants(Length.MEDIUM, Function.COMFORTABLE, Length.MEDIUM, true, false));
        closet.add(new Pants(Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        try { ClosetSerializer.writeJSON(closet); }
        catch (IOException e) {
            System.out.println("problem writing json: " + e.getMessage());
        }

        Assert.assertTrue(new File(ClosetSerializer.TEST_FILE).exists());

        try { Files.deleteIfExists(new File(ClosetSerializer.TEST_FILE).toPath()); }
        catch (IOException e) {
            System.out.println("Somehow the file did not exist.");
        }
    }

    @Test
    public void readJSON() {
        ClosetSerializer.setTest();
        Closet closet = new Closet();
        closet.add(new Top(Length.LONG, Function.CASUAL, Length.MEDIUM, Neckline.COLLAR));
        closet.get(0).setColor(Color.GREEN);
        closet.add(new Top(Length.SHORT, Function.WORK, Length.LONG, Neckline.COWL));
        closet.add(new Top(Length.MEDIUM, Function.ATHLETIC, Length.SHORT, Neckline.CREW));
        closet.add(new Pants(Length.SHORT, Function.FORMAL, Length.LONG, true, true));
        closet.add(new Pants(Length.MEDIUM, Function.COMFORTABLE, Length.MEDIUM, true, false));
        closet.add(new Pants(Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        try { ClosetSerializer.writeJSON(closet); }
        catch (IOException e) {
            System.out.println("problem writing json: " + e.getMessage());
        }

        Closet read_in = new Closet();
        try { ClosetSerializer.readJSON(read_in); }
        catch (FileNotFoundException e) {
            System.out.println("json file not found: " + e.getMessage());
        }

        Assert.assertEquals(read_in.get(0).getAdded(), closet.get(0).getAdded());
        Assert.assertEquals(read_in.get(0).getColor(), closet.get(0).getColor());

        try { Files.deleteIfExists(new File(ClosetSerializer.TEST_FILE).toPath()); }
        catch (IOException e) {
            System.out.println("Somehow the file did not exist.");
        }
    }
}