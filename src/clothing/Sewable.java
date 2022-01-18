package clothing;

import clothing.trait.*;

//  An interface of features of a sewable item - could be clothes, a blanket, boots, etc.
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
