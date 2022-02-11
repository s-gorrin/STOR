/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/08/2022
 * Desc: the outfit picker functionality for the STOR application
 */

package closet.view;

import clothing.Clothing;
import clothing.trait.Color;
import clothing.trait.Type;
import clothing.trait.Warmth;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FXOutfit {
    protected static List<Clothing> weather = new ArrayList<>(FXController.closet.getAllClothing());
    protected static Set<Integer> outfit = new HashSet<>();


    /**
     * first page of the outfit picker
     * @param primaryStage  the javafx stage
     */
    public static void landing(Stage primaryStage) {
        Label prompt = new Label("select a weather or go back home");

        Button warm = new Button("it's warm out");
        Button neutral = new Button("it's moderate out");
        Button cold = new Button("it's cold out");

        warm.setOnAction(ActionEvent -> {
            weather = weather.stream().filter(c -> c.getWarmth() == Warmth.COLD || c.getWarmth() == Warmth.COOL)
                    .collect(Collectors.toList());
            pickFirstBy(primaryStage);
        });

        neutral.setOnAction(ActionEvent -> {
            weather = weather.stream().filter(c -> c.getWarmth() != Warmth.COLD && c.getWarmth() != Warmth.HOT)
                    .collect(Collectors.toList());
            pickFirstBy(primaryStage);
        });

        cold.setOnAction(ActionEvent -> {
            weather = weather.stream().filter(c -> c.getWarmth() == Warmth.WARM || c.getWarmth() == Warmth.HOT)
                    .collect(Collectors.toList());
            pickFirstBy(primaryStage);
        });

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(prompt, warm, neutral, cold, FXController.home(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * back button to get back to the landing page
     * @param ps    the javafx stage
     */
    public static Button landingButton(Stage ps) {
        Button back = new Button("back");
        back.setOnAction(ActionEvent -> landing(ps));
        return back;
    }

    /**
     * choose the criteria by which to pick the first item of an outfit
     * @param primaryStage  the javafx stage
     */
    public static void pickFirstBy(Stage primaryStage) {
        Label label = new Label("how would you like\nto choose the first item?");
        Button type = new Button("by TYPE of clothing");
        Button color = new Button("by COLOR of clothing");

        type.setOnAction(ActionEvent -> firstByType(primaryStage));
        color.setOnAction(ActionEvent -> firstByColor(primaryStage));

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(label, type, color, landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * choose a type for the first item in the outfit
     * @param primaryStage  the javafx stage
     */
    public static void firstByType(Stage primaryStage) {
        Label label = new Label("select a type of clothing to be the first item");
        ArrayList<Button> buttons = FXTraitButtons.make(Type.class);

        for (Button b : buttons) {
            b.setOnAction(ActionEvent ->
                    pickFirst(primaryStage, weather.stream().filter(c -> c.getType() == Type.get(buttons.indexOf(b)))
                            .collect(Collectors.toList())));
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);

    }

    /**
     * choose a color for the first item in the outfit
     * @param primaryStage  the javafx stage
     */
    public static void firstByColor(Stage primaryStage) {
        Label label = new Label("select a color of clothing to be the first item");
        ArrayList<Button> buttons = FXTraitButtons.make(Color.class);

        for (Button b : buttons) {
            b.setOnAction(ActionEvent ->
                    pickFirst(primaryStage, weather.stream().filter(c -> c.getColor() == Color.get(buttons.indexOf(b)))
                            .collect(Collectors.toList())));
        }

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(landingButton(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

    // This will leave the option to limit the whole suggestion process to the warmth-filtered list
    /**
     * pick the first item in the outfit
     * @param primaryStage      the javafx stage
     * @param firstItemChoices  remaining choices for the weather after the first item has been chosen
     */
    public static void pickFirst(Stage primaryStage, List<Clothing> firstItemChoices) {
        Label label = new Label("pick your first item");
        List<Button> buttons = new ArrayList<>();

        for (Clothing c : firstItemChoices) {
            Button b = new Button(c.getName());
            b.setId(String.valueOf(c.getID()));

            b.setOnAction(ActionEvent -> pickRest(primaryStage, c.getID()));
            buttons.add(b);
        }

        Button back = new Button("back");
        back.setOnAction(ActionEvent -> pickFirstBy(primaryStage));

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(buttons);
        vBox.getChildren().add(back);
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * use toggle buttons to pick the rest of an outfit
     * @param primaryStage  the javafx stage
     * @param firstItem     the item picked in pickFirst()
     */
    public static void pickRest(Stage primaryStage, int firstItem) {
        ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
        Label label = new Label("select additional items to go with\n" +
                FXController.closet.get(firstItem).getName());
        Button choose = new Button("see outfit");

        outfit.add(firstItem);

        // could replace weather with a closet for more closet
        for (Clothing c : weather.stream().filter(c -> FXController.compatible.check(firstItem, c.getID()))
                .collect(Collectors.toList())) {
            ToggleButton tb = new ToggleButton(c.getName());
            tb.setId(String.valueOf(c.getID()));

            toggleButtons.add(tb);
        }

        choose.setOnAction(ActionEvent -> {
            for (ToggleButton tb : toggleButtons)
                if (tb.isSelected())
                    outfit.add(Integer.parseInt(tb.getId()));
            confirmOutfit(primaryStage);
        });

        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().add(label);
        vBox.getChildren().addAll(toggleButtons);
        vBox.getChildren().add(choose);
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * confirm an outfit or start over
     * @param primaryStage  the javafx stage
     */
    public static void confirmOutfit(Stage primaryStage) {
        Label label = new Label("confirm the following outfit or start over");
        Button confirm = new Button("confirm outfit");
        Button restart = new Button("start over");

        StringBuilder items = new StringBuilder();
        for (int ID : outfit)
            items.append(FXController.closet.get(ID).getName()).append("\n\n");

        confirm.setOnAction(ActionEvent -> {
            for (int ID : outfit)
                FXController.closet.get(ID).addUse();

            outfit.clear();
            summary(primaryStage, items.toString());
        });

        restart.setOnAction(ActionEvent -> {
            outfit.clear();
            landing(primaryStage);
        });

        HBox hBox = new HBox(FXController.PADDING);
        hBox.getChildren().addAll(confirm, restart);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(label, new Text(items.toString()), hBox);
        FXController.handleScene(primaryStage, vBox);
    }

    /**
     * show the selected outfit
     * @param primaryStage  the javafx stage
     * @param confirmed     the string of the confirmed outfit
     */
    public static void summary(Stage primaryStage, String confirmed) {
        VBox vBox = new VBox(FXController.PADDING);
        vBox.getChildren().addAll(new Text(confirmed), FXController.home(primaryStage));
        FXController.handleScene(primaryStage, vBox);
    }

}
