/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/05/2022
 * Desc: Generate scenes for clothing traits
 */

package closet.view;

import clothing.trait.Trait;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXTraitScenes {


    /**
     * create the scene for selecting a material
     * @param primaryStage  the stage
     *
     */
    public static void scene(Stage primaryStage, Trait trait) {
        VBox vbox = new VBox(FXController.PADDING);

        String message;
        if (trait == Trait.TYPE)
            message = "What type of clothing would you like to add?";
        else
            message = "Select a " + trait.toString().toLowerCase() + " option for the " +
                    FXTraitButtons.type.toString().toLowerCase() + ":";
        vbox.getChildren().add(new Text(message));

        switch (trait) {
            case TYPE:
                vbox.getChildren().addAll(FXTraitButtons.typeButtons(primaryStage));
                break;
            case MATERIAL:
                vbox.getChildren().addAll(FXTraitButtons.materialButtons(primaryStage));
                break;
            case TEXTILE:
                vbox.getChildren().addAll(FXTraitButtons.textileButtons(primaryStage));
                break;
            case COLOR:
                vbox.getChildren().addAll(FXTraitButtons.colorButtons(primaryStage));
                break;
            case WARMTH:
                vbox.getChildren().addAll(FXTraitButtons.warmthButtons(primaryStage));
                break;
            case FASTENER:
                vbox.getChildren().addAll(FXTraitButtons.fastenerButtons(primaryStage));
                break;
            case LENGTH:
                vbox.getChildren().addAll(FXTraitButtons.lengthButtons(primaryStage));
                break;
            case FUNCTION:
                vbox.getChildren().addAll(FXTraitButtons.functionButtons(primaryStage));
                break;
            case SLEEVES:
                vbox.getChildren().addAll(FXTraitButtons.sleeveButtons(primaryStage));
                break;
            case NECK:
                vbox.getChildren().addAll(FXTraitButtons.neckButtons(primaryStage));
                break;
            case VOLUME:
                vbox.getChildren().addAll(FXTraitButtons.volumeButtons(primaryStage));
                break;
            case WAIST:
                vbox.getChildren().addAll(FXTraitButtons.waistButtons(primaryStage));
                break;
            case POCKETS:
                vbox.getChildren().addAll(FXTraitButtons.boolButtons(primaryStage, "pockets"));
                break;
            case BELT_LOOPS:
                vbox.getChildren().addAll(FXTraitButtons.boolButtons(primaryStage, "beltLoops"));
                break;
            case USES_PER_CLEAN:
                vbox.getChildren().addAll(FXTraitButtons.number(primaryStage, 10, "usesPerClean"));
                break;
        }


        vbox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vbox, FXController.WIDTH, FXController.HEIGHT));
        primaryStage.show();
    }


    /*
    method (here or elsewhere) that gets called from the last button for a class:
    creates the clothing item that was prompted for from traits in FXTraitButtons
    with a "confirm and add item" button, so it only happens on click
     */
}
