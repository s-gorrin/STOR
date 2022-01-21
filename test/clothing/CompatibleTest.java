package clothing;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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

        record.remove(2);

        Assert.assertFalse(record.check(1, 2));
        Assert.assertFalse(record.check(5, 2));
        Assert.assertFalse(record.check(5, 3));
        Assert.assertFalse(record.check(10, 11));
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
}