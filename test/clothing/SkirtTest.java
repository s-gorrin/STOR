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
        Skirt skirt = new Skirt(Length.SHORT, Length.MID_LONG, Volume.LOOSE, false, Function.CASUAL);
        Top top = new Top(Length.LONG, Length.LONG, Neckline.COLLAR, Function.CASUAL);

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

    @Test
    public void getName() {
        // Test the get name method of Skirt

        // Given
        Skirt skirt = new Skirt(Length.SHORT, Length.MID_LONG, Volume.LOOSE, false, Function.CASUAL);

        // Then
        Assert.assertEquals(skirt.getName(), "short skirt");

        // When
        skirt.setColor(Color.BLUE);
        skirt.setTextile(Textile.TWILL);

        // Then - won't add base descriptors until all three are present
        Assert.assertEquals(skirt.getName(), "short skirt");

        // When
        skirt.setMaterial(Material.WOOL);

        // Then
        Assert.assertEquals(skirt.getName(), "blue, twill, wool short skirt");

    }
}