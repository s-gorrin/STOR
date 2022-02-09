/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: The primary controller for the STOR application - structure may change
 */

package closet.CLI;

import closet.Closet;
import clothing.ClosetSerializer;
import closet.Compatible;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// TODO: refactor this to HomeController, make Manage and Outfit controllers their own classes
// TODO: Ask about how class breakup should be handled

/**
 * Controller for the STOR application
 *
 * Control flow:
 * Main menu
 *      Outfit
 *          pick items
 *          pick next item
 *          etc
 *          "use" items
 *      Manage
 *          Add
 *              enter a new item
 *              prompt for data fields
 *              prompt for compatibility
 *          Remove
 *              pick item to remove
 *              confirm removal
 *          Info
 *              clothing item
 *                  get info
 *              trait
 *                  get stats
 *          Laundry
 *              select items that have been cleaned
 */
public class Controller {
    private final Closet closet;
    private final Compatible compatible;

    /**
     * Constructor to load a closet file or create a new closet and start program execution
     */
    public Controller() {
        File closetFile = new File(ClosetSerializer.FILENAME);
        closet = new Closet();
        compatible = new Compatible();

        if (closetFile.exists()) {
            try { ClosetSerializer.readJSON(closet); }
            catch (FileNotFoundException e) {
                System.out.println("Closet JSON file not found somehow: " + e);
            }
        }

        try { compatible.readFromFile(); }
        catch (IOException e) {
            System.out.println("Problem reading compatibility file: " + e);
        }
    }

    /**
     * Beginning of application control flow
     * Prompt main menu and act on the response
     */
    public void start() {
        MainMenu.Response response = MainMenu.Response.valueOf(MainMenu.prompt());

        switch (response) {
            case OUTFIT:
                System.out.println("Outfit picker coming soon :)");
                break;
            case MANAGE:
                manage();
                break;
            case EXIT:
                exit();
        }
    }

    /**
     * Safe exit handler for the application
     */
    public void exit() {
        try { ClosetSerializer.writeJSON(closet); }
        catch (IOException e) {
            System.out.println("There was a problem writing JSON: " + e);
        }

        try { compatible.writeToFile(); }
        catch (IOException e) {
            System.out.println("There was a problem saving the compatibility table: " + e);
        }

        System.out.println("Goodbye");
        System.exit(0);
    }

    /**
     * Caller for the "manage" main menu option
     */
    public void manage() {
        ManageClosetMenu.Response response = ManageClosetMenu.Response.valueOf(ManageClosetMenu.prompt());

        switch (response) {
            case ADD:
                AddClothing.landing(this, closet, compatible);
                break;
            case REMOVE:
                System.out.println("remove an item coming soon");
                break;
            case INFO:
                System.out.println("get info coming soon");
                break;
            case BACK:
                start();
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.start();
    }
}
