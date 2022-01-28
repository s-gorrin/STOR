package closet;

import clothing.trait.Color;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TraitCounterTest {

    @Test
    public void countAttribute() {
        TraitCounter<Color> counter = new TraitCounter<>();

        counter.put(0, Color.BLACK);
        counter.put(1, Color.GREEN);
        counter.put(2, Color.PURPLE);
        counter.put(3, Color.BLACK);
        counter.put(4, Color.BLACK);
        counter.put(5, Color.BLACK);
        counter.put(6, Color.BLUE);

        Assert.assertEquals(4, counter.countAttribute(Color.BLACK));
        Assert.assertEquals(1, counter.countAttribute(Color.GREEN));
        Assert.assertEquals(0, counter.countAttribute(Color.ORANGE));
    }

    @Test
    public void mostCommon() {
        TraitCounter<Color> counter = new TraitCounter<>();

        counter.put(0, Color.BLACK);
        counter.put(1, Color.GREEN);
        counter.put(2, Color.PURPLE);
        counter.put(3, Color.BLACK);
        counter.put(4, Color.BLACK);
        counter.put(5, Color.BLACK);
        counter.put(6, Color.BLUE);

        Assert.assertEquals(Color.BLACK.ordinal(), counter.mostCommon()); // same as below but different access method
        Assert.assertEquals(Color.BLACK, Color.values()[counter.mostCommon()]);
        Assert.assertNotEquals(Color.PURPLE.ordinal(), counter.mostCommon());
    }

    @Test
    public void IDsWithTrait() {
        TraitCounter<Color> counter = new TraitCounter<>();

        counter.put(0, Color.BLACK);
        counter.put(1, Color.GREEN);
        counter.put(2, Color.PURPLE);
        counter.put(3, Color.BLACK);
        counter.put(4, Color.BLACK);
        counter.put(5, Color.BLACK);
        counter.put(6, Color.BLUE);

        Assert.assertEquals(1, counter.IDsWithTrait(Color.GREEN)[0]);

        int[] expected = new int[] {0, 3, 4, 5};
        int[] counted = counter.IDsWithTrait(Color.BLACK);

        // Note: since counted is coming from a HashMap, this order could change unexpectedly
        for (int i = 0; i < 4; i++)
            Assert.assertEquals(expected[i], counted[i]);
    }

    @Test
    public void moreThanHalf() {
        TraitCounter<Color> counter = new TraitCounter<>();

        counter.put(0, Color.BLACK);
        counter.put(1, Color.GREEN);
        counter.put(2, Color.PURPLE);
        counter.put(3, Color.BLACK);
        counter.put(4, Color.BLACK);
        counter.put(5, Color.BLACK);
        counter.put(6, Color.BLUE);

        Assert.assertTrue(counter.moreThanHalf(Color.BLACK));
        Assert.assertFalse(counter.moreThanHalf(Color.GREEN));
        Assert.assertFalse(counter.moreThanHalf(Color.ORANGE)); // test with color that's not there
    }
}