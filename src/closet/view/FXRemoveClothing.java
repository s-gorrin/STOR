/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/09/2022
 * Desc: Remove clothes from the closet
 */

package closet.view;

import clothing.Clothing;
import clothing.trait.Color;
import clothing.trait.Type;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FXRemoveClothing {

    /**
     * landing page for the clothing removal functionality
     * @param primaryStage  the javafx stage
     */
    public static void landing(Stage primaryStage) {
        Label label = new Label("select removal filtering criteria");
        Button type = new Button("select by type");
        Button color = new Button("select by color");
        Button age = new Button("select by age");
        Button uses = new Button("select by uses");

        type.setOnAction(a -> type(primaryStage));
        color.setOnAction(a -> color(primaryStage));
        age.setOnAction(a -> age(primaryStage));
        uses.setOnAction(a -> uses(primaryStage));

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(label, type, color, age, uses, FXController.home(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * a button to go back to the landing page
     * @param ps    the javafx stage
     * @return      a button that calls landing()
     */
    public static Button landingButton(Stage ps) {
        Button landing = new Button("back");
        landing.setOnAction(ActionEvent -> landing(ps));
        return landing;
    }

    /**
     * select which type to remove items of
     * @param primaryStage  the javafx stage
     */
    public static void type(Stage primaryStage) {
        Label label = new Label("select which type to pick from");
        ArrayList<Button> buttons = FXTraitButtons.make(Type.class);

        for (Button b : buttons) {
            b.setOnAction(ActionEvent -> selectRemovals(primaryStage, FXController.closet.getAllClothing().stream()
                    .filter(c -> c.getType() == Type.get(buttons.indexOf(b)))
                    .map(Clothing::getID).collect(Collectors.toList())));
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * select which color to remove items of
     * @param primaryStage  the javafx stage
     */
    public static void color(Stage primaryStage) {
        Label label = new Label("select which color to pick from");
        ArrayList<Button> buttons = FXTraitButtons.make(Color.class);

        for (Button b : buttons) {
            b.setOnAction(ActionEvent -> selectRemovals(primaryStage, FXController.closet.getAllClothing().stream()
                    .filter(c -> c.getColor() == Color.get(buttons.indexOf(b)))
                    .map(Clothing::getID).collect(Collectors.toList())));
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * select how many of the oldest items to view
     * @param primaryStage  the javafx stage
     */
    public static void age(Stage primaryStage) {
        Label label = new Label("select how many of the oldest");
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 10; i += 2) {
            Button button = new Button(String.valueOf(i));
            int finalI = i;
            button.setOnAction(a -> selectRemovals(primaryStage, FXController.closet.getAllIDs().stream()
                    .sorted().collect(Collectors.toList()).subList(0, finalI)));
            buttons.add(button);
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    //todo implement methods below
    /**
     * select how many of the lowest-use items to view
     * @param primaryStage  the javafx stage
     */
    public static void uses(Stage primaryStage) {
        ArrayList<Button> buttons = new ArrayList<>();
    }

    public static void selectRemovals(Stage primaryStage, List<Integer> candidates) {}
}
