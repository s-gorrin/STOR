/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 1/31/2022
 * Desc: An interface of features of a sewable item - could be clothes, a blanket, boots, etc.
 */

package clothing;

import clothing.trait.*;

public interface Sewable {
    Material material = Material.OTHER;
    Textile textile = Textile.OTHER;
    Color color = Color.OTHER;
    Warmth warmth = Warmth.NEUTRAL;

    // Mutators
    void setMaterial(Material material);
    void setTextile(Textile textile);
    void setColor(Color color);
    void setWarmth(Warmth warmth);

    // Accessors
    Material getMaterial();
    Textile getTextile();
    Color getColor();
    Warmth getWarmth();

    String getName(); // generate a name from traits
}
