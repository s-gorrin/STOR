package clothing;

import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

public class PantsTest {

    @Test
    public void possiblyCompatible() {
        // Test compatability of pants with other clothing items

        // Given
        Pants pants = new Pants(Material.COTTON, Textile.TWILL, Color.BROWN,
                Warmth.WARM, Fastener.BUTTON, 3, Length.LONG, Function.FORMAL,
                Length.MID_LONG, true, true);

        Top top = new Top(Length.LONG, Function.CASUAL, Length.LONG, Neckline.COLLAR);

        // When
        top.setColor(Color.WHITE);

        // Then
        Assert.assertTrue(pants.possiblyCompatible(top)); // should work going forward with not-yet defined comparisons
        Assert.assertFalse(pants.possiblyCompatible(new Pants()));

    }

    @Test (expected = NullPointerException.class)
    public void getName() {
        // Given
        Pants pants = new Pants(Material.COTTON, Textile.TWILL, Color.BROWN,
                Warmth.WARM, Fastener.BUTTON, 3, Length.LONG, Function.FORMAL,
                Length.MID_LONG, true, true);

        // Then
        Assert.assertEquals(pants.getName(), "brown, twill, cotton long pants with buttons");
        Assert.assertEquals(new Pants().getName(), "pants"); // NullPointerException

        // When
        pants.setDetail("cuffs");

        // Then
        Assert.assertEquals(pants.getName(), "brown, twill, cotton long pants with buttons with cuffs");
    }
}