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
        Dress dress = new Dress(Length.MID_LONG, Function.CASUAL, Length.SHORT,
                Neckline.HENLEY, Volume.LOOSE, false);
        Top top = new Top(Length.LONG, Function.CASUAL, Length.LONG, Neckline.COLLAR);

        // When
        top.setColor(Color.BROWN);

        // Then
        Assert.assertFalse(dress.possiblyCompatible(top));
        Assert.assertFalse(dress.possiblyCompatible(new Pants()));
        Assert.assertFalse(dress.possiblyCompatible(new Skirt()));
    }

    @Test (expected = NullPointerException.class)
    public void getName() {
        // Test the get name method of Dress

        // Given
        Dress dress = new Dress(Length.SHORT, Function.CASUAL, Length.MID_LONG,
                Neckline.OFF_SHOULDER, Volume.LOOSE, false);

        // When
        dress.setColor(Color.BLUE);
        dress.setTextile(Textile.TWILL);
        dress.setMaterial(Material.WOOL);

        // Then
        Assert.assertEquals(dress.getName(), "blue, twill, wool short dress");

        // Then
        Assert.assertEquals(new Dress().getName(), "dress"); // NullPointerException

    }
}
