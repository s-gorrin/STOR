package clothing;

import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

public class TopTest {
    // A test class for the Top class, not including set/get functions

    @Test
    public void possiblyCompatible() {
        // Given
        Top top = new Top(Material.LINEN, Textile.BASIC_WEAVE, Color.BLACK,
                Warmth.COOL, Fastener.BUTTON, 2, Length.SHORT, Function.WORK,
                Length.MID_LONG, Neckline.COLLAR);

        // Then
        Assert.assertFalse(top.possiblyCompatible(new Dress()));
        Assert.assertTrue(top.possiblyCompatible(new Top())); // update: multiple tops are possible in an outfit
        Assert.assertTrue(top.possiblyCompatible(new Pants()));
    }

    @Test (expected = NullPointerException.class)
    public void getName() {
        // Given
        Top top = new Top(Material.LINEN, Textile.BASIC_WEAVE, Color.BLACK,
                Warmth.COOL, Fastener.BUTTON, 2, Length.SHORT, Function.WORK,
                Length.MID_LONG, Neckline.COLLAR);

        Top defaultTop = new Top();

        // When
        defaultTop.setFastener(Fastener.ZIPPER);

        // Then
        Assert.assertEquals(top.getName(), "black, woven, linen top with buttons");
        Assert.assertEquals(defaultTop.getName(), "a top with a zipper"); // NullPointerException
    }
}