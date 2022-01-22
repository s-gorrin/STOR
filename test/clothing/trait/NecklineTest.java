package clothing.trait;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NecklineTest {

    @Test
    public void list() {
        // There should be no underscores in list output
        Assert.assertEquals(Neckline.list(), Neckline.list().replace('_', '#'));
    }
}