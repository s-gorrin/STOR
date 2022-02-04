package closet;

import clothing.Dress;
import clothing.Pants;
import clothing.Skirt;
import clothing.Top;
import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

public class ClosetTest {

    /**
     * Fill a Closet with values
     */
    public static void fillCloset(Closet closet) {
        for (int i = 0; i < 5; i++)
            closet.add(new Top());

        for (int i = 0; i < 4; i++)
            closet.add(new Dress());

        for (int i = 0; i < 3; i++)
            closet.add(new Pants());

        for (int i = 0; i < 2; i++)
            closet.add(new Skirt());

        for (int ID : closet.getAll()) {
            // fill all attributes with semi-random values based on ID as a number
            closet.get(ID).setColor(Color.get(ID % Color.size));
            closet.get(ID).setTextile(Textile.get(ID % Textile.size));
            closet.get(ID).setMaterial(Material.get(ID % Material.size));
            closet.get(ID).setWarmth(Warmth.get(ID % Warmth.size));

            if (closet.get(ID).getType().equals(Type.TOP)) {
                ((Top) closet.get(ID)).setLength(Length.get(ID % Length.size));
                ((Top) closet.get(ID)).setFunction(Function.get(ID % Function.size));
                ((Top) closet.get(ID)).setSleeves(Length.get(ID % Length.size));
                ((Top) closet.get(ID)).setNeck(Neckline.get(ID % Neckline.size));
                closet.get(ID).setFastener(Fastener.NONE);
            }

            if (closet.get(ID).getType().equals(Type.DRESS)) {
                ((Dress) closet.get(ID)).setLength(Length.get(ID % Length.size));
                ((Dress) closet.get(ID)).setFunction(Function.get(ID % Function.size));
                ((Dress) closet.get(ID)).setSleeves(Length.get(ID % Length.size));
                ((Dress) closet.get(ID)).setNeck(Neckline.get(ID % Neckline.size));
                ((Dress) closet.get(ID)).setVolume(Volume.get(ID % Volume.size));
                ((Dress) closet.get(ID)).setPockets(false);
                closet.get(ID).setFastener(Fastener.ZIPPER);
            }

            if (closet.get(ID).getType().equals(Type.PANTS)) {
                ((Pants) closet.get(ID)).setLength(Length.get(ID % Length.size));
                ((Pants) closet.get(ID)).setFunction(Function.get(ID % Function.size));
                ((Pants) closet.get(ID)).setWaist(Length.get(ID % Length.size));
                ((Pants) closet.get(ID)).setPockets(true);
                ((Pants) closet.get(ID)).setBeltLoops(true);
                closet.get(ID).setFastener(Fastener.ZIPPER);
            }

            if (closet.get(ID).getType().equals(Type.SKIRT)) {
                ((Skirt) closet.get(ID)).setLength(Length.get(ID % Length.size));
                ((Skirt) closet.get(ID)).setFunction(Function.get(ID % Function.size));
                ((Skirt) closet.get(ID)).setVolume(Volume.get(ID % Volume.size));
                ((Skirt) closet.get(ID)).setWaist(Length.get(ID % Length.size));
                closet.get(ID).setFastener(Fastener.OTHER);
                ((Skirt) closet.get(ID)).setPockets(false);
            }
        }
    }

    @Test
    public void add() {
        Closet closet = new Closet();

        Pants pants = new Pants(Material.WOOL, Textile.TWILL, Color.PURPLE,
                Warmth.WARM, Fastener.BUTTON, 2, Length.LONG,
                Function.CASUAL, Length.MEDIUM, true, true);

        closet.add(pants);
        closet.add(new Top(), 1);

        Assert.assertTrue(closet.contains(0)); // ID 0 is present
        Assert.assertEquals(pants, closet.get(0)); // ID 0 is what it should be
        Assert.assertTrue(closet.contains(1));
        Assert.assertNotEquals(pants, closet.get(1));

        Assert.assertFalse(closet.contains(5)); // an ID that shouldn't be there isn't
    }

    @Test
    public void remove() {
        Closet closet = new Closet();

        Pants pants = new Pants(Material.WOOL, Textile.TWILL, Color.PURPLE,
                Warmth.WARM, Fastener.BUTTON, 2, Length.LONG,
                Function.CASUAL, Length.MEDIUM, true, true);

        closet.add(pants);
        closet.add(new Top(), 1);

        Assert.assertTrue(closet.contains(1));

        closet.remove(1);

        Assert.assertFalse(closet.contains(1));

        Pants removedPants = (Pants)closet.remove(0);

        Assert.assertEquals(pants, removedPants);
        Assert.assertEquals(0, closet.size());
    }

    @Test
    public void getNumberOf() {
        Closet closet = new Closet();

        fillCloset(closet);

        Assert.assertEquals(5, closet.getNumberOf(Type.TOP));
        Assert.assertEquals(4, closet.getNumberOf(Type.DRESS));
        Assert.assertNotEquals(5, closet.getNumberOf(Type.PANTS));
        Assert.assertNotEquals(5, closet.getNumberOf(Type.SKIRT));
    }

    @Test
    public void size() {
        Closet closet = new Closet();

        closet.add(new Dress());

        Assert.assertEquals(1, closet.size());

        fillCloset(closet);

        Assert.assertEquals(15, closet.size());
    }
}