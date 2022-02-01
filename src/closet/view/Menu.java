/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: Define a menu for the STOR application and handle the menu user input
 */

package closet.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu {
    private static final int FIRST = 1;

    /**
     * handle user input for menu selection
     * accepting only responses by number for simplicity
     * @param menu  a string containing the options for the calling menu
     * @param last  the number of the last item in the menu
     * @return      the real index of the selected item
     */
    static int prompt(String menu, int last) {
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
                keyboard.nextLine(); // clear buffer to get next input
            }
        }

        keyboard.close();

        return response - 1;
    }

}
