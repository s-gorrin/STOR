/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/09/2022
 * Desc: Remove clothes from the closet
 */

package closet.view;

import closet.ClothingList;
import clothing.Clothing;
import clothing.trait.Color;
import clothing.trait.Type;
import database.Database;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        for (int i = 2; i <= 12; i += 2) {
            Button button = new Button(String.valueOf(i));
            int n = i;
            button.setOnAction(a -> selectRemovals(primaryStage, ClothingList.nOldest(FXController.closet, n)));
            buttons.add(button);
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * select how many of the lowest-use items to view
     * @param primaryStage  the javafx stage
     */
    public static void uses(Stage primaryStage) {
        Label label = new Label("select how many of the lowest-use");
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 2; i <= 12; i += 2) {
            Button button = new Button(String.valueOf(i));
            int n = i;
            button.setOnAction(a -> selectRemovals(primaryStage, ClothingList.nLeastUsed(FXController.closet, n)));
            buttons.add(button);
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * select items to be removed from the closet
     * @param primaryStage  the javafx stage
     * @param candidates    possible items for removal
     */
    public static void selectRemovals(Stage primaryStage, List<Integer> candidates) {
        Label label = new Label("select clothes to remove\nfrom the closet forever");
        ArrayList<ToggleButton> buttons = new ArrayList<>();
        Button confirm = new Button("view selections");

        for (int ID : candidates) {
            ToggleButton button = new ToggleButton(FXController.closet.get(ID).getName());

            button.setId(String.valueOf(ID));
            buttons.add(button);
        }

        confirm.setOnAction(ActionEvent -> {
            List<Integer> remove = new ArrayList<>();
            for (ToggleButton tb : buttons)
                if (tb.isSelected())
                    remove.add(Integer.parseInt(tb.getId()));

            confirmRemovals(primaryStage, remove);
        });

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().addAll(confirm, landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * confirm items to be removed
     * @param primaryStage  the javafx stage
     * @param remove        the list of items to be removed
     */
    private static void confirmRemovals(Stage primaryStage, List<Integer> remove) {
        Label label = new Label("items to be removed:");
        Button confirm = new Button("remove items from closet");
        Button cancel = new Button("do not remove items");
        StringBuilder names = new StringBuilder();

        for (int ID : remove)
            names.append(FXController.closet.get(ID).getName()).append("\n");

        Text text = new Text(names.toString());

        confirm.setOnAction(ActionEvent -> {
            for (int ID : remove) {
                FXController.closet.remove(ID);
                FXController.compatible.remove(ID);

                Database.remove(ID);
            }

            landing(primaryStage);
        });

        cancel.setOnAction(ActionEvent -> landing(primaryStage));

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(label, text, confirm, cancel);
        FXController.handleScene(primaryStage, vBox);
    }
}
