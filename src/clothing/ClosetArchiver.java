package clothing;


import clothing.trait.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to store and retrieve Closet data to and from a CSV file
 */
public class ClosetArchiver {
    public static final String FILENAME = "closet.csv";

    /**
     * Get a CSV string representation for the Clothing attributes
     * @param clothing  an instance of the Clothing class
     * @param type      the Type of the Clothing which can only come from a child
     * @return          a CSV formatted string of Clothing attributes
     */
    private static String clothingToCSV(Clothing clothing, Type type) {
        return clothing.getID() + ", " +
                type + ", " +
                clothing.getAdded() + ", " +
                clothing.getTotalUses() + ", " +
                clothing.getUsesSinceCleaned() + ", " +
                clothing.getCleanLevel() + ", " +
                clothing.getLastUsed() + ", " +
                clothing.getMaterial() + ", " +
                clothing.getTextile() + ", " +
                clothing.getColor() + ", " +
                clothing.getWarmth() + ", " +
                clothing.getFastener() + ", " +
                clothing.getUsesPerCleanLevel() + ", " +
                clothing.getDetail() + ", ";
    }

    /**
     * write a line of Top to the file
     * @param writer        a FileWriter
     * @param top           an instance of the Top class
     * @throws IOException  writer exception
     */
    private static void writeTop(FileWriter writer, Top top) throws IOException {
        String CSV = clothingToCSV(top, top.getType());

        CSV += top.getLength() + ", " +
                top.getFunction() + ", " +
                top.getSleeves() + ", " +
                top.getNeck() + ", " +
                ", , ,\n"; // volume, waist, pockets, beltLoops

        writer.write(CSV);
    }

    /**
     * write a line of Skirt to the file
     * @param writer        a FileWriter
     * @param skirt         an instance of the Skirt class
     * @throws IOException  writer exception
     */
    private static void writeSkirt(FileWriter writer, Skirt skirt) throws IOException {
        String CSV = clothingToCSV(skirt, skirt.getType());

        CSV += skirt.getLength() + ", " +
                skirt.getFunction() + ", " +
                ", , " + // sleeves, neck,
                skirt.getVolume() + ", " +
                skirt.getWaist() + ", " +
                skirt.getPockets() + ",\n"; // beltLoops

        writer.write(CSV);
    }

    /**
     * write a line of Pants to the file
     * @param writer        a FileWriter
     * @param pants         an instance of the Pants class
     * @throws IOException  writer exception
     */
    private static void writePants(FileWriter writer, Pants pants) throws IOException {
        String CSV = clothingToCSV(pants, pants.getType());

        CSV += pants.getLength() + ", " +
                pants.getFunction() + ", " +
                ", , , " + // sleeves, neck, volume,
                pants.getWaist() + ", " +
                pants.getPockets() + ", " +
                pants.getBeltLoops() + "\n";

        writer.write(CSV);
    }

    /**
     * write a line of Dress to the file
     * @param writer        a FileWriter
     * @param dress         an instance of the Dress class
     * @throws IOException  writer exception
     */
    private static void writeDress(FileWriter writer, Dress dress) throws IOException {
        String CSV = clothingToCSV(dress, dress.getType());

        CSV += dress.getLength() + ", " +
                dress.getFunction() + ", " +
                dress.getSleeves() + ", " +
                dress.getNeck() + ", " +
                dress.getVolume() + ", " +
                ", " + // waist
                dress.getPockets() + ",\n"; // beltLoops

        writer.write(CSV);
    }

    /**
     * Take a Closet object and store it as a CSV file
     * @param closet    an instance of the Closet class
     * @return          true on success, false on failure
     */
    public static boolean save(Closet closet) {
        File archive = new File(FILENAME);
        FileWriter writer;

        try {
            writer = new FileWriter(archive);
            writer.write("ID, type, added, totalUses, usesSinceCleaned, cleanLevel, lastUsed, " +
                    "material, textile, color, warmth, fastener, usesPerCleanLevel, detail, length, " +
                    "function, sleeves, neck, volume, waist, pockets, beltLoops\n"); // CSV header
            for (int ID : closet.getAll()) {
                switch (closet.get(ID).getType()) {
                    case TOP:
                        writeTop(writer, ((Top)closet.get(ID)));
                        break;
                    case SKIRT:
                        writeSkirt(writer, ((Skirt)closet.get(ID)));
                        break;
                    case PANTS:
                        writePants(writer, ((Pants)closet.get(ID)));
                        break;
                    case DRESS:
                        writeDress(writer, ((Dress)closet.get(ID)));
                        break;
                    default:
                        writer.write("\n");
                }
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Exception in ClosetArchiver: " + e);
            return false;
        }

        return true;
    }

    /**
     * Read the file and generate a Closet from the contents
     * After retrieval, it renames the file to preserve closet state at load
     * @return  A Closet object
     */
    public static Closet retrieve() {
        // Closet probably need a copy constructor for this to work

        return new Closet();
    }

    public static void main(String[] args) {
        ClosetArchiver.save(new Closet());
    }
}
