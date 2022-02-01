/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: The menu for the manage closet option from the main menu
 */

package closet.view;

public class ManageClosetMenu extends Menu {
    // Note: this must be changed if menu options are added or removed
    private static final int LAST = 4;

    public enum Response {
        ADD, REMOVE, INFO, BACK
    }

    private static final String MENU =
            "Please select an option by number:\n" +
            "1. add an item\n" +
            "2. remove an item\n" +
            "3. get information\n" +
            "4. go back\n" +
            "> ";

    /**
     * get an option from the manage closet menu
     * @return  a response from the manage closet menu options
     */
    public static String prompt() {
        return Response.values()[prompt(MENU, LAST)].toString();
    }

    public static void main(String[] args) {
        System.out.println(prompt());
    }

}
