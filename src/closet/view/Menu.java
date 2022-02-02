/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: Define a menu for the STOR application and handle the menu user input
 */

package closet.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static final int FIRST = 1;

    /**
     * handle user input for menu selection
     * accepting only responses by number for simplicity
     * [note: with a gui, this method would be replaced by clickable buttons of some kind]
     * @param menu  a string containing the options for the calling menu
     * @param last  the number of the last item in the menu
     * @return      the real index of the selected item
     */
    public static int prompt(String menu, int last) {
        Scanner keyboard = new Scanner(System.in);
        int response = 0;

        System.out.print(menu);

        while (response == 0) {
            try {
                response = keyboard.nextInt();
                if (response < FIRST || response > last)
                    throw new IndexOutOfBoundsException();
            }
            catch (InputMismatchException e) {
                System.out.print("Please enter a number in the list.\n> ");
            }
            catch (IndexOutOfBoundsException e) {
                System.out.print("Please enter a number between "
                        + FIRST + " and " + last + ".\n> ");
                response = 0;
            }
            finally {
                if (keyboard.hasNextLine())
                    keyboard.nextLine(); // clear buffer to get next input
            }
        }

        // in a finished command line app, there would be a clear console here

        return response - 1;
    }


    public static boolean bool(String message) {
        Scanner keyboard = new Scanner(System.in);
        String instructions = "\nPlease enter y or yes for yes, anything else for no: ";

        System.out.print(message + instructions);
        String response = keyboard.next().toLowerCase();

        return response.equals("y") || response.equals("yes") || response.equals("1");
    }

}
