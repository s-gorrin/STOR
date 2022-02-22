/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/22/2022
 * Desc: Add an item to the database in the background
 */

package database;

import clothing.*;
import clothing.trait.Type;

public class BackgroundAdd extends Thread {
    private final Clothing clothing;
    private final Type type;

    public BackgroundAdd(Clothing clothing, Type type) {
        this.clothing = clothing;
        this.type = type;
    }

    synchronized public void run() {
        switch (type) {
            case TOP:
                Add.top((Top) clothing);
                break;
            case PANTS:
                Add.pants((Pants) clothing);
                break;
            case SKIRT:
                Add.skirt((Skirt) clothing);
                break;
            case DRESS:
                Add.dress((Dress) clothing);
                break;
        }
    }
}
