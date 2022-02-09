/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/04/2022
 * Desc: A class that takes every possible option for any Clothing subclass for reading JSON
 */

package clothing;

import clothing.trait.*;

public class SerialClothing extends Clothing {
    protected Type type;
    protected Length length;
    protected Function function;
    protected Length sleeves;
    protected Neckline neck;
    protected Volume volume;
    protected Length waist;
    protected boolean pockets;
    protected boolean beltLoops;


    /**
     * empty default constructor for Gson to use, apparently
     */
    public SerialClothing() {
       super();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLength(Length length) {
        this.length = length;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public void setSleeves(Length sleeves) {
        this.sleeves = sleeves;
    }

    public void setNeck(Neckline neck) {
        this.neck = neck;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public void setWaist(Length waist) {
        this.waist = waist;
    }

    public void setPockets(boolean pockets) {
        this.pockets = pockets;
    }

    public void setBeltLoops(boolean beltLoops) {
        this.beltLoops = beltLoops;
    }

    /**
     * use this to figure out which toClothing class to call
     * @return  the type of the Object
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * implemented to satisfy template
     * @return a never-used name string
     */
    public String getName() {
        return "generic clothing item only used for json serialization";
    }

    /**
     * get a Top from the class
     * @return  a new Top
     */
    public Top toTop() {
        return new Top(super.getID(), super.getAdded(), super.getTotalUses(), super.getUsesSinceCleaned(),
                super.getCleanLevel(), super.getLastUsed(), super.getMaterial(), super.getTextile(),
                super.getColor(), super.getWarmth(), super.getFastener(), super.getUsesPerCleanLevel(),
                super.getDetail(), length, function, sleeves, neck);
    }

    /**
     * get a Pants from the class
     * @return  a new Pants
     */
    public Pants toPants() {
        return new Pants(super.getID(), super.getAdded(), super.getTotalUses(), super.getUsesSinceCleaned(),
                super.getCleanLevel(), super.getLastUsed(), super.getMaterial(), super.getTextile(),
                super.getColor(), super.getWarmth(), super.getFastener(), super.getUsesPerCleanLevel(),
                super.getDetail(), length, function, waist, pockets, beltLoops);
    }


    /**
     * get a Skirt from the class
     * @return  a new Skirt
     */
    public Skirt toSkirt() {
        return new Skirt(super.getID(), super.getAdded(), super.getTotalUses(), super.getUsesSinceCleaned(),
                super.getCleanLevel(), super.getLastUsed(), super.getMaterial(), super.getTextile(),
                super.getColor(), super.getWarmth(), super.getFastener(), super.getUsesPerCleanLevel(),
                super.getDetail(), length, function, volume, waist, pockets);
    }


    /**
     * get a Dress from the class
     * @return  a new Dress
     */
    public Dress toDress() {
        return new Dress(super.getID(), super.getAdded(), super.getTotalUses(), super.getUsesSinceCleaned(),
                super.getCleanLevel(), super.getLastUsed(), super.getMaterial(), super.getTextile(),
                super.getColor(), super.getWarmth(), super.getFastener(), super.getUsesPerCleanLevel(),
                super.getDetail(), length, function, sleeves, neck, volume, pockets);
    }
}
