/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/01/2022
 * Desc: Add an item of clothing to a Closet
 */

package closet.view;

import closet.Closet;
import closet.Controller;
import clothing.Top;
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
    public static void landing(Controller controller, Closet closet) {
        int response = 0;

        while (response != 5) {
            response = Menu.prompt(LANDING_MENU, LAST) + 1; // +1 so numbers match menu

            switch (response) {
                case 1:
                    AddClothing.top(closet);
                    break;
                case 2:
                    System.out.println("add pants");
                    break;
                case 3:
                    System.out.println("add skirt");
                    break;
                case 4:
                    System.out.println("add dress");
                    break;
                default:
                    break;
            }
        }

        controller.manage(); // return to calling method
    }

    /**
     * add a Top to the closet with user input for every user-definable option
     * @param closet the closet that the top will be added to
     */
    public static void top(Closet closet) {
        Top top = new Top();

        top.setMaterial(Material.get(Menu.prompt(PREAMBLE + Material.list() + PROMPT, Material.size)));
        top.setTextile(Textile.get(Menu.prompt(PREAMBLE + Textile.list() + PROMPT, Textile.size)));
        top.setColor(Color.get(Menu.prompt(PREAMBLE + Color.list() + PROMPT, Color.size)));
        top.setWarmth(Warmth.get(Menu.prompt(PREAMBLE + Warmth.list() + PROMPT, Warmth.size)));
        top.setFastener(Fastener.get(Menu.prompt(PREAMBLE + Fastener.list() + PROMPT, Fastener.size)));
        top.setUsesPerCleanLevel(Menu.prompt("How many times can this be worn\n" +
                "before it becomes a new level of \"dirty\" (1 - 10)?" + PROMPT, 10));
        top.setLength(Length.get(Menu.prompt(PREAMBLE + "hem " + Length.list() + PROMPT, Length.size)));
        top.setFunction(Function.get(Menu.prompt(PREAMBLE + Function.list() + PROMPT, Function.size)));
        top.setSleeves(Length.get(Menu.prompt(PREAMBLE + "sleeve " + Length.list() + PROMPT, Length.size)));
        top.setNeck(Neckline.get(Menu.prompt(PREAMBLE + Neckline.list() + PROMPT, Neckline.size)));

        closet.add(top);
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
