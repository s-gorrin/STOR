package clothing.trait;

import org.junit.Assert;
import org.junit.Test;

public class CleanTest {

    @Test
    public void list() {
        Assert.assertEquals(Clean.list(), "1. fresh\n2. okay\n3. passable\n4. wash");
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void get() {
        Assert.assertEquals(Clean.get(1), Clean.FRESH);
        Clean test = Clean.get(56); // throws exception
        Assert.assertNull(test);
    }
}