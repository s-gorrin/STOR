package clothing;

import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

public class DressTest {
    @Test
    public void possiblyCompatible() {
        // Test compatibility between Dress and other clothes
        // Note: dresses have no compatibility with any of these clothing types

        // Given
        Dress dress = new Dress(Length.SHORT, Length.MID_LONG, Volume.LOOSE,
                Neckline.HENLEY, false, Function.CASUAL);
        Top top = new Top(Length.LONG, Length.LONG, Neckline.COLLAR, Function.CASUAL);

        // When
        top.setColor(Color.BROWN);

        // Then
        Assert.assertFalse(dress.possiblyCompatible(top));
        Assert.assertFalse(dress.possiblyCompatible(new Pants()));
        Assert.assertFalse(dress.possiblyCompatible(new Skirt()));
    }

    @Test
    public void getName() {
        // Test the get name method of Dress

        // Given
        Dress dress = new Dress(Length.SHORT, Length.MID_LONG, Volume.LOOSE,
                Neckline.OFF_SHOULDER, false, Function.CASUAL);

        // Then
        Assert.assertEquals(dress.getName(), "mid_long dress");

        // When
        dress.setColor(Color.BLUE);
        dress.setTextile(Textile.TWILL);

        // Then - won't add base descriptors until all three are present
        Assert.assertEquals(dress.getName(), "mid_long dress");

        // When
        dress.setMaterial(Material.WOOL);

        // Then
        Assert.assertEquals(dress.getName(), "blue, twill, wool mid_long dress");

    }
}
