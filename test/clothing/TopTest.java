package clothing;

import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopTest {
    // A test class for the Top class, not including set/get functions

    @Test
    public void possiblyCompatible() {
        // Given
        Top top = new Top(Material.LINEN, Textile.BASIC_WEAVE, Color.BLACK,
                Warmth.COOL, Fastener.BUTTON, 2, Length.SHORT,
                Length.MID_LONG, Neckline.COLLAR, Function.WORK);

        // Then
        Assert.assertFalse(top.possiblyCompatible(new Top()));
        Assert.assertFalse(top.possiblyCompatible(new Dress()));
        Assert.assertTrue(top.possiblyCompatible(new Pants()));
    }

    @Test
    public void getName() {
        // Given
        Top top = new Top(Material.LINEN, Textile.BASIC_WEAVE, Color.BLACK,
                Warmth.COOL, Fastener.BUTTON, 2, Length.SHORT,
                Length.MID_LONG, Neckline.COLLAR, Function.WORK);

        Top defaultTop = new Top();

        // When
        defaultTop.setFastener(Fastener.ZIPPER);

        // Then
        Assert.assertEquals(top.getName(), "black, woven, linen top with buttons");
        Assert.assertEquals(defaultTop.getName(), "a top with a zipper");
    }
}