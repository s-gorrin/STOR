/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/01/2022
 * Desc: Add an item of clothing to a Closet
 */

package closet.CLI;

import closet.Closet;
import closet.Compatible;
import clothing.*;
import clothing.trait.*;

public class AddClothing {
    private static final String PREAMBLE = "Please select a number from the list of ";
    private static final String PROMPT = "\n> ";

    private static final int LAST = 5;
    private static final String LANDING_MENU =
            "What would you like to add:\n" +
            "1. top\n" +
            "2. pants\n" +
            "3. skirt\n" +
            "4. dress\n" +
            "5. go back\n" +
            "> ";

    /**
     * main menu for the add clothing methods
     * @param controller the calling controller so this can hand back control
     * @param closet     the closet so this can add items to it
     */
    public static void landing(Controller controller, Closet closet, Compatible compatible) {
        int response = 0;
        int ID;

        while (response != 5) {
            if (response != 0)
                System.out.println("\nAddition successful!\n");
            response = Menu.prompt(LANDING_MENU, LAST) + 1; // +1 so numbers match menu

            switch (response) {
                case 1:
                    ID = AddClothing.top(closet);
                    break;
                case 2:
                    ID = AddClothing.pants(closet);
                    break;
                case 3:
                    ID = AddClothing.skirt(closet);
                    break;
                case 4:
                    ID = AddClothing.dress(closet);
                    break;
                default:
                    ID = -1;
                    break;
            }

            if (ID != -1)
                ManageCompatibility.add(closet.get(ID), closet, compatible);
        }

        controller.manage(); // return to calling method
    }

    /**
     * prompt for the shared properties of all clothing
     * @param clothing  an item of clothing
     */
    private static void clothing(Clothing clothing) {
        clothing.setMaterial(Material.get(Menu.prompt(PREAMBLE + Material.list() + PROMPT, Material.size)));
        clothing.setTextile(Textile.get(Menu.prompt(PREAMBLE + Textile.list() + PROMPT, Textile.size)));
        clothing.setColor(Color.get(Menu.prompt(PREAMBLE + Color.list() + PROMPT, Color.size)));
        clothing.setWarmth(Warmth.get(Menu.prompt(PREAMBLE + Warmth.list() + PROMPT, Warmth.size)));
        clothing.setFastener(Fastener.get(Menu.prompt(PREAMBLE + Fastener.list() + PROMPT, Fastener.size)));
        clothing.setUsesPerCleanLevel(Menu.prompt("How many times can this be worn\n" +
                "before it becomes a new level of \"dirty\" (1 - 10)?" + PROMPT, 10));
    }

    /**
     * add a Top to the closet with user input for every user-definable option
     * @param closet    the closet being added-to
     * @return          the ID of the top
     */
    public static int top(Closet closet) {
        Top top = new Top();

        clothing(top);

        top.setLength(Length.get(Menu.prompt(PREAMBLE + "hem " + Length.list() + PROMPT, Length.size)));
        top.setFunction(Function.get(Menu.prompt(PREAMBLE + Function.list() + PROMPT, Function.size)));
        top.setSleeves(Length.get(Menu.prompt(PREAMBLE + "sleeve " + Length.list() + PROMPT, Length.size)));
        top.setNeck(Neckline.get(Menu.prompt(PREAMBLE + Neckline.list() + PROMPT, Neckline.size)));

        closet.add(top);
        return top.getID();
    }

    /**
     * add a Pants to the closet with user input for every user-definable option
     * @param closet    the closet being added-to
     * @return          the ID of the pants
     */
    public static int pants(Closet closet) {
        Pants pants = new Pants();

        clothing(pants);

        pants.setLength(Length.get(Menu.prompt(PREAMBLE + "hem " + Length.list() + PROMPT, Length.size)));
        pants.setFunction(Function.get(Menu.prompt(PREAMBLE + Function.list() + PROMPT, Function.size)));
        pants.setWaist(Length.get(Menu.prompt(PREAMBLE + "waist " + Length.list() + PROMPT, Length.size)));
        pants.setPockets(Menu.bool("Do these pants have real pockets?"));
        pants.setBeltLoops(Menu.bool("Do these pants have belt loops?"));

        closet.add(pants);
        return pants.getID();
    }

    /**
     * add a Skirt to the closet with user input for every user-definable option
     * @param closet    the closet being added-to
     * @return          the ID of the top
     */
    public static int skirt(Closet closet) {
        Skirt skirt = new Skirt();

        clothing(skirt);

        skirt.setLength(Length.get(Menu.prompt(PREAMBLE + "hem " + Length.list() + PROMPT, Length.size)));
        skirt.setFunction(Function.get(Menu.prompt(PREAMBLE + Function.list() + PROMPT, Function.size)));
        skirt.setVolume(Volume.get(Menu.prompt(PREAMBLE + Volume.list() + PROMPT, Volume.size)));
        skirt.setWaist(Length.get(Menu.prompt(PREAMBLE + "waist " + Length.list() + PROMPT, Length.size)));
        skirt.setPockets(Menu.bool("Does this skirt have real pockets?"));

        closet.add(skirt);
        return skirt.getID();
    }

    /**
     * add a Dress to the closet with user input for every user-definable option
     * @param closet    the closet being added-to
     * @return          the ID of the top
     */
    public static int dress(Closet closet) {
        Dress dress = new Dress();

        clothing(dress);

        dress.setLength(Length.get(Menu.prompt(PREAMBLE + "hem " + Length.list() + PROMPT, Length.size)));
        dress.setFunction(Function.get(Menu.prompt(PREAMBLE + Function.list() + PROMPT, Function.size)));
        dress.setSleeves(Length.get(Menu.prompt(PREAMBLE + "sleeve " + Length.list() + PROMPT, Length.size)));
        dress.setNeck(Neckline.get(Menu.prompt(PREAMBLE + Neckline.list() + PROMPT, Neckline.size)));
        dress.setVolume(Volume.get(Menu.prompt(PREAMBLE + Volume.list() + PROMPT, Volume.size)));
        dress.setPockets(Menu.bool("Does this dress have real pockets?"));

        closet.add(dress);
        return dress.getID();
    }

    public static void main(String[] args) {
        Closet closet = new Closet();

        AddClothing.top(closet);

        int ID = 0;

        System.out.println(closet.get(ID).getMaterial());
        System.out.println(closet.get(ID).getTextile());
        System.out.println(closet.get(ID).getColor());
        System.out.println(closet.get(ID).getName());

        System.out.println(ID);
    }
}
