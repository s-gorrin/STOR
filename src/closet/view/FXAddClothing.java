/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/06/2022
 * Desc: Methods for the add clothing gui
 */

package closet.view;

import closet.BackgroundSaver;
import clothing.*;
import clothing.trait.Trait;
import clothing.trait.Type;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FXAddClothing {

    /**
     * get compatibility for an item
     * @param primaryStage  the javafx stage
     * @param item          the item in question
     */
    public static void compatibility(Stage primaryStage, Clothing item) {
        ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
        Button confirm = new Button("confirm");
        VBox vbox = new VBox(FXController.PADDING);
        Stream<Clothing> candidates = FXController.closet.getAllClothing().stream();

        for (Clothing c : candidates.filter(item::possiblyCompatible)
                .filter(c -> item.getID() != c.getID()).collect(Collectors.toList())) {
            ToggleButton button = new ToggleButton();

            button.setText(c.getName());
            button.setId(String.valueOf(c.getID()));

            toggleButtons.add(button);
        }

        confirm.setOnAction(ActionEvent -> {
            for (ToggleButton tb : toggleButtons)
                if (tb.isSelected())
                    FXController.compatible.add(item.getID(), Integer.parseInt(tb.getId()));
            new BackgroundSaver(FXController.closet, FXController.compatible).start(); // save closet in the background
            FXTraitScenes.scene(primaryStage, Trait.TYPE); // go back to add another item
        });

        vbox.getChildren().add(new Text("please select the items that are compatible with\n" + item.getName()));
        vbox.getChildren().addAll(toggleButtons);
        vbox.getChildren().add(confirm);
        FXController.handleScene(primaryStage, vbox);
    }

    /**
     * fill most of the shared stats for clothing labels
     * @param type  the clothing type
     * @return      the string
     */
    private static String clothingStats(Type type) {
        return "Are the following features correct for this " +
                type.toString().toLowerCase() + "?\n" +
                "material: " + FXTraitButtons.material + "\n" +
                "textile: " + FXTraitButtons.textile + "\n" +
                "color: " + FXTraitButtons.color + "\n" +
                "warmth: " + FXTraitButtons.warmth + "\n" +
                "fastener: " + FXTraitButtons.fastener + "\n";
    }

    /**
     * a helper to handle the duplicate code from the clothing confirm pages
     * @param primaryStage  the javafx stage
     * @param stats         stat string from clothing traits
     * @param detail        user-entered detail
     * @param yes           the yes button
     * @param no            the no button
     */
    private static void confirm(Stage primaryStage, String stats, TextField detail, Button yes, Button no) {
        VBox vbox = new VBox(FXController.PADDING);

        Label detailLabel = new Label("add an optional detail:");

        HBox buttons = new HBox(FXController.PADDING);
        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(new Text(stats), detailLabel, detail, buttons);
        FXController.handleScene(primaryStage, vbox);
    }

    /**
     * confirmation page for a Top
     * @param primaryStage  the javafx stage
     */
    public static void confirmTop(Stage primaryStage) {

        String stats = clothingStats(Type.TOP) +
                "length: " + FXTraitButtons.length + "\n" +
                "function: " + FXTraitButtons.function + "\n" +
                "sleeve length: " + FXTraitButtons.sleeves + "\n" +
                "neckline: " + FXTraitButtons.neckline + "\n" +
                "uses per level of cleanliness: " + FXTraitButtons.usesPerClean;

        // get an additional detail from the user
        TextField detail = new TextField();

        Button yes = new Button("yes, confirm");
        Button no = new Button("no, start over");

        yes.setOnAction(ActionEvent -> {
            Top top = new Top(FXTraitButtons.material, FXTraitButtons.textile, FXTraitButtons.color,
                FXTraitButtons.warmth, FXTraitButtons.fastener, FXTraitButtons.usesPerClean,
                FXTraitButtons.length, FXTraitButtons.function, FXTraitButtons.sleeves, FXTraitButtons.neckline);

            top.setDetail(detail.getText()); // this should accept the detail as text
            FXController.closet.add(top);
            compatibility(primaryStage, top);
        });

        no.setOnAction(ActionEvent -> FXTraitScenes.scene(primaryStage, Trait.TYPE));

        confirm(primaryStage, stats, detail, yes, no);
    }

    /**
     * confirmation page for a Pants
     * @param primaryStage  the javafx stage
     */
    public static void confirmPants(Stage primaryStage) {

        String stats = clothingStats(Type.PANTS) +
                "length: " + FXTraitButtons.length + "\n" +
                "function: " + FXTraitButtons.function + "\n" +
                "waist height: " + FXTraitButtons.waist + "\n" +
                "real pockets: " + FXTraitButtons.pockets + "\n" +
                "belt loops: " + FXTraitButtons.beltLoops + "\n" +
                "uses per level of cleanliness: " + FXTraitButtons.usesPerClean;

        // get an additional detail from the user
        TextField detail = new TextField();

        Button yes = new Button("yes, confirm");
        Button no = new Button("no, start over");

        yes.setOnAction(ActionEvent -> {
            Pants pants = new Pants(FXTraitButtons.material, FXTraitButtons.textile, FXTraitButtons.color,
                    FXTraitButtons.warmth, FXTraitButtons.fastener, FXTraitButtons.usesPerClean,
                    FXTraitButtons.length, FXTraitButtons.function, FXTraitButtons.waist, FXTraitButtons.pockets,
                    FXTraitButtons.beltLoops);

            pants.setDetail(detail.getText()); // this should accept the detail as text
            FXController.closet.add(pants);
            compatibility(primaryStage, pants);
        });

        no.setOnAction(ActionEvent -> FXTraitScenes.scene(primaryStage, Trait.TYPE));

        confirm(primaryStage, stats, detail, yes, no);
    }

    /**
     * confirmation page for a Skirt
     * @param primaryStage  the javafx stage
     */
    public static void confirmSkirt(Stage primaryStage) {

        String stats = clothingStats(Type.SKIRT) +
                "length: " + FXTraitButtons.length + "\n" +
                "function: " + FXTraitButtons.function + "\n" +
                "volume: " + FXTraitButtons.volume + "\n" +
                "waist height: " + FXTraitButtons.waist + "\n" +
                "real pockets: " + FXTraitButtons.pockets + "\n" +
                "uses per level of cleanliness: " + FXTraitButtons.usesPerClean;

        // get an additional detail from the user
        TextField detail = new TextField();

        Button yes = new Button("yes, confirm");
        Button no = new Button("no, start over");

        yes.setOnAction(ActionEvent -> {
            Skirt skirt = new Skirt(FXTraitButtons.material, FXTraitButtons.textile, FXTraitButtons.color,
                    FXTraitButtons.warmth, FXTraitButtons.fastener, FXTraitButtons.usesPerClean,
                    FXTraitButtons.length, FXTraitButtons.function, FXTraitButtons.volume, FXTraitButtons.waist,
                    FXTraitButtons.pockets);

            skirt.setDetail(detail.getText()); // this should accept the detail as text
            FXController.closet.add(skirt);
            compatibility(primaryStage, skirt);
        });

        no.setOnAction(ActionEvent -> FXTraitScenes.scene(primaryStage, Trait.TYPE));

        confirm(primaryStage, stats, detail, yes, no);
    }

    /**
     * confirmation page for a Dress
     * @param primaryStage  the javafx stage
     */
    public static void confirmDress(Stage primaryStage) {

        String stats = clothingStats(Type.DRESS) +
                "length: " + FXTraitButtons.length + "\n" +
                "function: " + FXTraitButtons.function + "\n" +
                "sleeve length: " + FXTraitButtons.sleeves + "\n" +
                "neckline: " + FXTraitButtons.neckline + "\n" +
                "volume: " + FXTraitButtons.volume + "\n" +
                "real pockets: " + FXTraitButtons.pockets + "\n" +
                "uses per level of cleanliness: " + FXTraitButtons.usesPerClean;

        // get an additional detail from the user
        TextField detail = new TextField();

        Button yes = new Button("yes, confirm");
        Button no = new Button("no, start over");

        yes.setOnAction(ActionEvent -> {
            Dress dress = new Dress(FXTraitButtons.material, FXTraitButtons.textile, FXTraitButtons.color,
                    FXTraitButtons.warmth, FXTraitButtons.fastener, FXTraitButtons.usesPerClean,
                    FXTraitButtons.length, FXTraitButtons.function, FXTraitButtons.sleeves,
                    FXTraitButtons.neckline, FXTraitButtons.volume, FXTraitButtons.pockets);

            dress.setDetail(detail.getText()); // this should accept the detail as text
            FXController.closet.add(dress);
            compatibility(primaryStage, dress);
        });

        no.setOnAction(ActionEvent -> FXTraitScenes.scene(primaryStage, Trait.TYPE));

        confirm(primaryStage, stats, detail, yes, no);
    }

}
