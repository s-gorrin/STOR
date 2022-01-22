package clothing.trait;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test class for the static method of the Fastener enum
 */
public class FastenerTest {

    @Test
    public void getDescription() {
        Assert.assertEquals(Fastener.getDescription(Fastener.BUTTON), " with buttons");
        Assert.assertEquals(Fastener.getDescription(Fastener.NONE), "");
    }
}