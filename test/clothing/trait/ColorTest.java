package clothing.trait;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest {

    @Test
    public void compatible() {
        // Test to confirm that the color compatibility tester works as expected

        // Given
        Color red = Color.RED;
        Color black = Color.BLACK;
        Color green = Color.GREEN;
        Color purple = Color.PURPLE;

        // Then
        Assert.assertFalse(Color.compatible(purple, red)); // weird false
        Assert.assertFalse(Color.compatible(green, red)); // regular false
        Assert.assertTrue(Color.compatible(black, purple)); // weird true
        Assert.assertTrue(Color.compatible(black, green)); // regular true
    }
}