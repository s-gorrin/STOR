package clothing;

import clothing.trait.Color;
import clothing.trait.Fastener;
import org.junit.Assert;
import org.junit.Test;

public class ClothingTest {
    // Test the non-trivial functions of the Clothing class via its derived classes

    @Test
    public void addUse() {
        // Testing the use functionality

        // Given
        Pants pants = new Pants();

        // Then
        Assert.assertEquals(pants.getTotalUses(), 0);

        // When
        pants.addUse();
        pants.addUse();
        pants.addUse();

        // Then
        Assert.assertEquals(pants.getTotalUses(), 3);
        Assert.assertEquals(pants.getAge(), 0);

    }

    @Test
    public void clean() {
        // Testing the clean functionality

        // Given
        Pants pants = new Pants();

        // Then
        Assert.assertEquals(pants.getTotalUses(), 0);

        // When
        pants.addUse();
        pants.addUse();
        pants.addUse();

        // Then
        Assert.assertEquals(pants.getTotalUses(), 3);
        Assert.assertEquals(pants.getUsesSinceCleaned(), 3);

        // When
        pants.clean();

        // Then
        Assert.assertEquals(pants.getUsesSinceCleaned(), 0);
    }

    @Test
    public void getFastenerDescription() {
        // Given
        Pants pants = new Pants();
        Top top = new Top();

        // When
        pants.setFastener(Fastener.ZIPPER);

        // Then
        Assert.assertEquals(pants.getFastenerDescription(), " with a zipper");
        Assert.assertEquals(top.getFastenerDescription(), "");
    }

    @Test
    public void possiblyCompatible() {
        // Test compatibility of colors only as it currently stands in abstract class

        // Given
        Pants pants = new Pants();
        Top top = new Top();

        // When
        pants.setColor(Color.BLACK);
        top.setColor(Color.GRAY);

        // Then
        Assert.assertTrue(((Clothing)pants).possiblyCompatible(top)); // down-casting to be sure to get super
    }
}