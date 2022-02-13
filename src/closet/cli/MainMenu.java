/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: The main menu of the application
 */

package closet.cli;

public class MainMenu extends Menu {
    // Note: this must be changed if menu options are added or removed
    private static final int LAST = 3;

    public enum Response {
        OUTFIT, MANAGE, EXIT
    }

    private static final String MENU =
            "Please select an option by number:\n" +
            "1. build an outfit\n" +
            "2. manage closet\n" +
            "3. exit\n" +
            "> ";

    /**
     * get an option from the main menu
     * accepting only responses by number for simplicity
     * @return  a response from the main menu options
     */
    public static String prompt() {
        return Response.values()[Menu.prompt(MENU, LAST)].toString();
    }

    public static void main(String[] args) {
        System.out.println(MainMenu.prompt());
    }
}
