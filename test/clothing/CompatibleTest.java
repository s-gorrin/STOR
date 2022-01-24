package clothing;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Compatible class only handles int IDs, so the test will
 * not create any Clothing objects
 */
public class CompatibleTest {

    @Test
    public void add() {
        Compatible record = new Compatible();

        record.add(1, 2);
        record.add(1, 3);
        record.add(1, 5);
        record.add(5, 2);

        Assert.assertTrue(record.check(1, 2));
        Assert.assertTrue(record.check(2, 1));
        Assert.assertTrue(record.check(5, 2));
        Assert.assertFalse(record.check(5, 3));
        Assert.assertFalse(record.check(10, 11));
    }

    @Test
    public void remove() {
        Compatible record = new Compatible();

        record.add(1, 2);
        record.add(1, 3);
        record.add(1, 5);
        record.add(5, 2);

        Assert.assertTrue(record.remove(2));

        Assert.assertTrue(record.check(1, 3));
        Assert.assertFalse(record.check(1, 2));
        Assert.assertFalse(record.check(5, 2));
        Assert.assertFalse(record.check(5, 3));
        Assert.assertFalse(record.check(10, 11));

        record.remove(1, 3);

        Assert.assertFalse(record.check(1, 3));
    }

    @Test
    public void check() {
        Compatible record = new Compatible();

        record.add(1, 2);
        record.add(1, 3);
        record.add(1, 5);
        record.add(5, 2);

        Assert.assertTrue(record.check(1, 2));
        Assert.assertTrue(record.check(2, 1));
        Assert.assertTrue(record.check(5, 2));
        Assert.assertFalse(record.check(5, 3));
        Assert.assertFalse(record.check(10, 11));
    }

    @Test
    public void writeToFile() {
        Compatible record = new Compatible();

        record.add(1, 2);
        record.add(1, 3);
        record.add(1, 5);
        record.add(5, 2);

        try {
            record.writeToFile();
        }
        catch (IOException e) {
            System.out.println("Exception writing compatible: " + e);
        }

        File recordFile = new File(Compatible.FILENAME);
        Assert.assertTrue(recordFile.exists());

        try {
            Scanner reader = new Scanner(recordFile);
            Assert.assertEquals('1', reader.nextLine().charAt(0));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }

    }

    @Test
    public void readFromFile() {
        Compatible record = new Compatible();

        record.add(1, 2);
        record.add(1, 3);
        record.add(1, 5);
        record.add(5, 2);

        try {
            record.writeToFile();
        }
        catch (IOException e) {
            System.out.println("Exception writing compatible: " + e);
        }

        Compatible readIn = new Compatible();

        try {
            readIn.readFromFile();
        }
        catch (IOException e) {
            System.out.println("Problem with reading file back in: " + e);
        }

        Assert.assertTrue(readIn.check(1, 2)); // expected compatibilities
        Assert.assertTrue(readIn.check(1, 3));
        Assert.assertTrue(readIn.check(1, 5));
        Assert.assertTrue(readIn.check(5, 2));

        Assert.assertFalse(readIn.check(5, 3)); // not compatible
        Assert.assertFalse(readIn.check(6, 7)); // not present in table
    }
}