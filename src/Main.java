import closet.Closet;
import closet.TraitCounter;
import clothing.*;
import clothing.trait.*;


public class Main {

    /**
     * A non-production method to fill a Closet with values
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

        for (int ID : closet.getAllIDs()) {
            // fill all attributes with semi-random values based on ID as a number
            closet.get(ID).setColor(Color.get(ID % Color.values().length + 1));
            closet.get(ID).setTextile(Textile.get(ID % Textile.values().length + 1));
            closet.get(ID).setMaterial(Material.get(ID % Material.values().length + 1));
            closet.get(ID).setWarmth(Warmth.get(ID % Warmth.values().length + 1));

            if (closet.get(ID).getType().equals(Type.TOP)) {
                ((Top) closet.get(ID)).setLength(Length.get(ID % Length.values().length + 1));
                ((Top) closet.get(ID)).setFunction(Function.get(ID % Function.values().length + 1));
                ((Top) closet.get(ID)).setSleeves(Length.get(ID % Length.values().length + 1));
                ((Top) closet.get(ID)).setNeck(Neckline.get(ID % Neckline.values().length + 1));
                closet.get(ID).setFastener(Fastener.NONE);
            }

            if (closet.get(ID).getType().equals(Type.DRESS)) {
                ((Dress) closet.get(ID)).setLength(Length.get(ID % Length.values().length + 1));
                ((Dress) closet.get(ID)).setFunction(Function.get(ID % Function.values().length + 1));
                ((Dress) closet.get(ID)).setSleeves(Length.get(ID % Length.values().length + 1));
                ((Dress) closet.get(ID)).setNeck(Neckline.get(ID % Neckline.values().length + 1));
                ((Dress) closet.get(ID)).setVolume(Volume.get(ID % Volume.values().length + 1));
                ((Dress) closet.get(ID)).setPockets(false);
                closet.get(ID).setFastener(Fastener.ZIPPER);
            }

            if (closet.get(ID).getType().equals(Type.PANTS)) {
                ((Pants) closet.get(ID)).setLength(Length.get(ID % Length.values().length + 1));
                ((Pants) closet.get(ID)).setFunction(Function.get(ID % Function.values().length + 1));
                ((Pants) closet.get(ID)).setWaist(Length.get(ID % Length.values().length + 1));
                ((Pants) closet.get(ID)).setPockets(true);
                ((Pants) closet.get(ID)).setBeltLoops(true);
                closet.get(ID).setFastener(Fastener.ZIPPER);
            }

            if (closet.get(ID).getType().equals(Type.SKIRT)) {
                ((Skirt) closet.get(ID)).setLength(Length.get(ID % Length.values().length + 1));
                ((Skirt) closet.get(ID)).setFunction(Function.get(ID % Function.values().length + 1));
                ((Skirt) closet.get(ID)).setVolume(Volume.get(ID % Volume.values().length + 1));
                ((Skirt) closet.get(ID)).setWaist(Length.get(ID % Length.values().length + 1));
                closet.get(ID).setFastener(Fastener.OTHER);
                ((Skirt) closet.get(ID)).setPockets(false);
            }
        }
    }

    /**
     * Currently an example class showing adding different Clothing types to the Closet
     * @param args none
     */
    public static void main(String[] args) {
        Closet closet = new Closet();
        fillCloset(closet);

        for (int ID : closet.getAllIDs()) {
            // print the closet
            try {
                System.out.println(closet.get(ID).getID() + ":" + closet.get(ID).getName());
            } catch (NullPointerException e) {
                System.out.println(e + ": A class is missing some critical data.");
            }
        }

        // Example of TraitCounter in use
        TraitCounter<Color> colorCounter= new TraitCounter<>();

        for (int ID : closet.getAllIDs())
            colorCounter.put(ID, closet.get(ID).getColor());

        System.out.println("Most common color in closet (first if tied): " +
            Color.values()[colorCounter.mostCommon()].toString().toLowerCase());
        System.out.println("Are more than half the clothes black: " + colorCounter.moreThanHalf(Color.BLACK));
        System.out.println("How many are gray: " + colorCounter.countAttribute(Color.GRAY));

/*
        // commenting out archiver code to save dealing with archives
        if (!ClosetArchiver.save(closet))
            System.out.println("Closet failed to save.");

        Closet savedCloset = ClosetArchiver.retrieve();

        // if this passes, it means the item in savedCloset is the same as the item in closet :)
        Assert.assertEquals(closet.get(2).getAdded(), savedCloset.get(2).getAdded());
        System.out.println("Success");
 */


    }
}
