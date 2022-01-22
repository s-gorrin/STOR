package clothing;

import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkirtTest {

    @Test
    public void possiblyCompatible() {
        // Test compatibility between Skirts and other clothes

        // Given
        Skirt skirt = new Skirt(Length.SHORT, Function.CASUAL, Length.MID_LONG, Volume.LOOSE, false);
        Top top = new Top(Length.LONG, Function.CASUAL, Length.LONG, Neckline.COLLAR);

        // When
        top.setColor(Color.BROWN);

        // Then
        Assert.assertTrue(skirt.possiblyCompatible(top));
        Assert.assertFalse(skirt.possiblyCompatible(new Pants()));
        Assert.assertFalse(skirt.possiblyCompatible(new Skirt()));

        // When
        skirt.setColor(Color.PURPLE);

        // Then
        Assert.assertFalse(skirt.possiblyCompatible(top));
    }

    @Test (expected = NullPointerException.class)
    public void getName() {
        // Test the get name method of Skirt

        // Given
        Skirt skirt = new Skirt(Length.SHORT, Function.CASUAL, Length.MID_LONG, Volume.LOOSE, false);

        // When
        skirt.setColor(Color.BLUE);
        skirt.setTextile(Textile.TWILL);
        skirt.setMaterial(Material.WOOL);

        // Then
        Assert.assertEquals(skirt.getName(), "blue, twill, wool short skirt");

        // Then
        Assert.assertEquals(new Skirt().getName(), "short skirt"); // NullPointerException

    }
}