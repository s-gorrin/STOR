package closet;

import closet.view.MainMenu;
import closet.view.ManageClosetMenu;
import closet.view.Menu;

import java.io.File;
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
        File closetFile = new File(ClosetArchiver.FILENAME);
        compatible = new Compatible();

        if (closetFile.exists())
            closet = ClosetArchiver.retrieve();
        else
            closet = new Closet();

        try {
            compatible.readFromFile();
        }
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
        ClosetArchiver.save(closet);
        try {
            compatible.writeToFile();
        }
        catch (IOException e) {
            System.out.println("There was a problem saving the compatibility table: " + e);
        }

        System.out.println("Goodbye");
        System.exit(0); // should this be an "exit success" or something?
    }

    /**
     * Caller for the "manage" main menu option
     */
    public void manage() {
        ManageClosetMenu.Response response = ManageClosetMenu.Response.valueOf(ManageClosetMenu.prompt());

        switch (response) {
            case ADD:
                System.out.println("add an item coming soon");
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
}
