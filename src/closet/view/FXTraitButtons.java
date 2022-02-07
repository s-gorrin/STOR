/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/07/2022
 * Desc: Generate buttons for clothing traits
 */

package closet.view;

import clothing.trait.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.EnumSet;

public class FXTraitButtons {
    protected static Clean clean;
    protected static Color color;
    protected static Fastener fastener;
    protected static Function function;
    protected static Length length;
    protected static Length sleeves;
    protected static Length waist;
    protected static Material material;
    protected static Neckline neckline;
    protected static Textile textile;
    protected static Type type;
    protected static Volume volume;
    protected static Warmth warmth;

    public static boolean pockets;
    public static boolean beltLoops;

    public static int usesPerClean;

    /**
     * generate buttons from an enum
     * @param trait the enum
     * @param <E>   the enum type
     * @return      an arraylist of buttons
     */
    public static <E extends Enum<E>> ArrayList<Button> make(Class<E> trait) {
        ArrayList<Button> buttons = new ArrayList<>();
        for (E en : EnumSet.allOf(trait))
            buttons.add(new Button(en.toString().toLowerCase()));

        return buttons;
    }

    /**
     * generate buttons to select clothing type to add
     * @param ps    the javafx stage
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> typeButtons(Stage ps) {
        ArrayList<Button> buttons = make(Type.class);
        for (Button b : buttons) {
            b.setOnAction(ActionEvent -> {
                type = Type.get(buttons.indexOf(b));
                FXTraitScenes.scene(ps, Trait.MATERIAL);
            });
        }

        buttons.add(FXController.home(ps));

        return buttons;
    }

    /**
     * generate buttons to assign a material
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> materialButtons(Stage ps) {
        ArrayList<Button> buttons = make(Material.class);
        for (Button b : buttons)
            b.setOnAction(actionEvent -> {
                material = Material.get(buttons.indexOf(b));
                FXTraitScenes.scene(ps, Trait.TEXTILE);
            });

        return buttons;
    }

    /**
     * generate buttons to assign a textile
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> textileButtons(Stage ps) {
        ArrayList<Button> buttons = make(Textile.class);
        for (Button b : buttons) {
            b.setOnAction(actionEvent -> {
                textile = Textile.get(buttons.indexOf(b));
                FXTraitScenes.scene(ps, Trait.COLOR);
            });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a color
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> colorButtons(Stage ps) {
        ArrayList<Button> buttons = make(Color.class);
        for (Button b : buttons) {
            b.setOnAction(actionEvent -> {
                color = Color.get(buttons.indexOf(b));
                FXTraitScenes.scene(ps, Trait.WARMTH);
            });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a warmth
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> warmthButtons(Stage ps) {
        ArrayList<Button> buttons = make(Warmth.class);
        for (Button b : buttons) {
            b.setOnAction(actionEvent -> {
                warmth = Warmth.get(buttons.indexOf(b));
                FXTraitScenes.scene(ps, Trait.FASTENER);
            });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a fastener
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> fastenerButtons(Stage ps) {
        ArrayList<Button> buttons = make(Fastener.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            fastener = Fastener.get(buttons.indexOf(b));
            FXTraitScenes.scene(ps, Trait.LENGTH);
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a length
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> lengthButtons(Stage ps) {
        ArrayList<Button> buttons = make(Length.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            length = Length.get(buttons.indexOf(b));
            FXTraitScenes.scene(ps, Trait.FUNCTION);
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a function
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> functionButtons(Stage ps) {
        ArrayList<Button> buttons = make(Function.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            function = Function.get(buttons.indexOf(b));

            switch (type) {
                case PANTS:
                    FXTraitScenes.scene(ps, Trait.WAIST);
                    break;
                case SKIRT:
                    FXTraitScenes.scene(ps, Trait.VOLUME);
                    break;
                default:
                    FXTraitScenes.scene(ps, Trait.SLEEVES);
                    break;
            }
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign sleeves
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> sleeveButtons(Stage ps) {
        ArrayList<Button> buttons = make(Length.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            sleeves = Length.get(buttons.indexOf(b));
            FXTraitScenes.scene(ps, Trait.NECK);
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a neck
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> neckButtons(Stage ps) {
        ArrayList<Button> buttons = make(Neckline.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            neckline = Neckline.get(buttons.indexOf(b));
            if (type == Type.DRESS)
                FXTraitScenes.scene(ps, Trait.VOLUME);
            else
                FXTraitScenes.scene(ps, Trait.USES_PER_CLEAN);
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a volume
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> volumeButtons(Stage ps) {
        ArrayList<Button> buttons = make(Volume.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            volume = Volume.get(buttons.indexOf(b));
            if (type == Type.SKIRT)
                FXTraitScenes.scene(ps, Trait.WAIST);
            else
                FXTraitScenes.scene(ps, Trait.POCKETS);
        });
        }

        return buttons;
    }

    /**
     * generate buttons to assign a waist
     * @param ps    the Stage to pass to the next method
     * @return      an arraylist of buttons
     */
    public static ArrayList<Button> waistButtons(Stage ps) {
        ArrayList<Button> buttons = make(Length.class);
        for (Button b : buttons) { b.setOnAction(actionEvent -> {
            waist = Length.get(buttons.indexOf(b));
            FXTraitScenes.scene(ps, Trait.POCKETS);
        });
        }

        return buttons;
    }

    /**
     * create buttons for boolean choices
     * @param ps        the stage
     * @param purpose   what the boolean is picking
     * @return          an arraylist of buttons
     */
    public static ArrayList<Button> boolButtons(Stage ps, String purpose) {
        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add(new Button("true"));
        buttons.get(0).setOnAction(ActionEvent -> {
            if (purpose.equals("pockets")) {
                pockets = true;
                if (type == Type.PANTS)
                    FXTraitScenes.scene(ps, Trait.BELT_LOOPS);
                else
                    FXTraitScenes.scene(ps, Trait.USES_PER_CLEAN);
            }
            else if (purpose.equals("beltLoops")) {
                beltLoops = true;
                FXTraitScenes.scene(ps, Trait.USES_PER_CLEAN);
            }
        });

        buttons.add(new Button("false"));
        buttons.get(1).setOnAction(ActionEvent -> {
            if (purpose.equals("pockets")) {
                pockets = false;
                if (type == Type.PANTS)
                    FXTraitScenes.scene(ps, Trait.BELT_LOOPS);
                else
                    FXTraitScenes.scene(ps, Trait.USES_PER_CLEAN);
            }
            else if (purpose.equals("beltLoops")) {
                beltLoops = false;
                FXTraitScenes.scene(ps, Trait.USES_PER_CLEAN);
            }
        });

        return buttons;
    }

    public static ArrayList<Button> number(Stage ps, int num, String purpose) {
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            buttons.add(new Button(String.valueOf(i + 1)));
            int finalI = i;
            buttons.get(i).setOnAction(ActionEvent -> {
                if (purpose.equals("usesPerClean")) {
                    usesPerClean = finalI + 1;

                    switch (type) {
                        case TOP:
                            FXAddClothing.confirmTop(ps);
                            break;
                        case PANTS:
                            FXAddClothing.confirmPants(ps);
                            break;
                        case SKIRT:
                            FXAddClothing.confirmSkirt(ps);
                            break;
                        case DRESS:
                            FXAddClothing.confirmDress(ps);
                            break;
                    }
                }
            });
        }

        return buttons;
    }

}
