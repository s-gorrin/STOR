package clothing.trait;

import org.junit.Assert;
import org.junit.Test;

/**
 * test class for the static method of the Fastener enum
 */
public class FastenerTest {

    @Test
    public void getDescription() {
        Assert.assertEquals(Fastener.getDescription(Fastener.BUTTON), " with buttons");
        Assert.assertEquals(Fastener.getDescription(Fastener.NONE), "");
    }

    @Test
    public void list() {
        Assert.assertEquals(Fastener.list(),
                "1. button\n" +
                "2. zipper\n" +
                "3. drawstring\n" +
                "4. snap\n" +
                "5. elastic\n" +
                "6. other\n" +
                "7. none");
    }
}