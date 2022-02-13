/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/05/2022
 * Desc: Start the GUI from here
 */

package closet.view;

import closet.Closet;
import clothing.ClosetSerializer;
import closet.Compatible;
import clothing.trait.Trait;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FXController extends Application {
    public static final int PADDING = 5;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 500; // reset to 500

    protected static final Closet closet = new Closet();
    protected static final Compatible compatible = new Compatible();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * set up the scene for various other methods to use
     * @param primaryStage  the javafx stage
     * @param vBox          a VBox containing the elements of the scene
     */
    protected static void handleScene(Stage primaryStage, VBox vBox) {
        vBox.setAlignment(Pos.CENTER);

        ScrollPane sp = new ScrollPane(vBox);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        primaryStage.setScene(new Scene(sp, WIDTH, HEIGHT));
        primaryStage.show();
    }

    /**
     * generate a home button to use in any screen
     * @param primaryStage  the stage of the home menu
     * @return              the home button
     */
    public static Button home(Stage primaryStage) {
        Button home = new Button("home");
        home.setOnAction(n -> mainMenu(primaryStage));
        return home;
    }

    /**
     * generate an exit button to add to any screen for clean program exit
     * @return  the exit button
     */
    public static Button exit() {
        Button exit = new Button("exit");
        exit.setOnAction(actionEvent -> {
            try { ClosetSerializer.writeJSON(closet); }
            catch (IOException e) {
                System.out.println("Problem writing the closet file:" + e.getMessage());
            }

            try { compatible.writeToFile(); }
            catch (IOException e) {
                System.out.println("Problem writing the compatibility file: " + e.getMessage());
            }
            System.exit(0);
        });
        exit.setCancelButton(true);
        return exit;
    }

    /**
     * landing page for the closet management functionality
     * @param primaryStage  the javafx stage
     */
    public static void manageMenu(Stage primaryStage) {
        VBox manageMenu = new VBox(PADDING);
        Button add = new Button("add clothing");
        Button remove = new Button("remove clothing");

        add.setOnAction(ActionEvent -> FXTraitScenes.scene(primaryStage, Trait.TYPE));
        remove.setOnAction(ActionEvent -> FXRemoveClothing.landing(primaryStage));

        manageMenu.getChildren().addAll(add, remove, home(primaryStage), exit());
        handleScene(primaryStage, manageMenu);
    }


    /**
     * primary landing page for the application
     * @param primaryStage  the javafx stage
     */
    public static void mainMenu(Stage primaryStage) {
        VBox vbox = new VBox(PADDING);
        Button outfit = new Button("create an outfit");
        Button manage = new Button("manage closet");

        outfit.setOnAction(actionEvent -> FXOutfit.landing(primaryStage));

        manage.setOnAction(actionEvent -> manageMenu(primaryStage));

        vbox.getChildren().addAll(outfit, manage, exit());
        handleScene(primaryStage, vbox);
    }


    /**
     * starting location for the GUI
     * @param primaryStage  the javafx stage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("STOR");

        try { ClosetSerializer.readJSON(closet); }
        catch (FileNotFoundException e) {
            System.out.println("Failed to locate file \"" + ClosetSerializer.FILENAME + "\", starting new Closet.");
        }

        try { compatible.readFromFile(); }
        catch (IOException e) {
            System.out.println("Failed to locate file\"" + Compatible.FILENAME + "\", starting new table.");
        }

        mainMenu(primaryStage);
    }
}
