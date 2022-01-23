package clothing;


import clothing.trait.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

/**
 * A class to store and retrieve Closet data to and from a CSV file
 */
public class ClosetArchiver {
    public static final String FILENAME = "archive/closet.csv";

    /**
     * Get a CSV string representation for the Clothing attributes
     * @param clothing  an instance of the Clothing class
     * @param ID        the clothing ID, also from individual items
     * @param type      the Type of the Clothing which can only come from a child
     * @return          a CSV formatted string of Clothing attributes
     */
    private static String clothingToCSV(Clothing clothing, int ID, Type type) {
        return ID + ", " +
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
        String CSV = clothingToCSV(top, top.getID(), top.getType());

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
        String CSV = clothingToCSV(skirt, skirt.getID(), skirt.getType());

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
        String CSV = clothingToCSV(pants, pants.getID(), pants.getType());

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
        String CSV = clothingToCSV(dress, dress.getID(), dress.getType());

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
     * an easy conversion from a String to a boolean
     * @param s a String
     * @return  a boolean derived from s
     */
    private static boolean bool(String s) {
        return s.equals("true");
    }

    /**
     * create an instance of the Top class from a CSV line
     * @param l  A line from the file
     * @return   A new Top
     */
    private static Top topFromCSV(String[] l) {
        Instant lastUsed = null;
        if (!l[6].equals("null"))
            lastUsed = Instant.parse(l[6]);

        return new Top(parseInt(l[0]), Instant.parse(l[2]), parseInt(l[3]), parseInt(l[4]),
                Clean.valueOf(l[5].toUpperCase()), lastUsed, Material.valueOf(l[7].toUpperCase()),
                Textile.valueOf(l[8].toUpperCase()), Color.valueOf(l[9].toUpperCase()),
                Warmth.valueOf(l[10].toUpperCase()), Fastener.valueOf(l[11].toUpperCase()),
                parseInt(l[12]), l[13], Length.valueOf(l[14].toUpperCase()),
                Function.valueOf(l[15].toUpperCase()), Length.valueOf(l[16].toUpperCase()),
                Neckline.valueOf(l[17].toUpperCase()));
    }

    /**
     * create an instance of the Skirt class from a CSV line
     * @param l  A line from the file
     * @return   A new Skirt
     */
    private static Skirt skirtFromCSV(String[] l) {
        Instant lastUsed = null;
        if (!l[6].equals("null"))
            lastUsed = Instant.parse(l[6]);

        return new Skirt(parseInt(l[0]), Instant.parse(l[2]), parseInt(l[3]), parseInt(l[4]),
                Clean.valueOf(l[5].toUpperCase()), lastUsed, Material.valueOf(l[7].toUpperCase()),
                Textile.valueOf(l[8].toUpperCase()), Color.valueOf(l[9].toUpperCase()),
                Warmth.valueOf(l[10].toUpperCase()), Fastener.valueOf(l[11].toUpperCase()),
                parseInt(l[12]), l[13], Length.valueOf(l[14].toUpperCase()),
                Function.valueOf(l[15].toUpperCase()), Volume.valueOf(l[18].toUpperCase()),
                Length.valueOf(l[19].toUpperCase()), bool(l[20]));
    }

    /**
     * create an instance of the Pants class from a CSV line
     * @param l  A line from the file
     * @return   A new Pants
     */
    private static Pants pantsFromCSV(String[] l) {
        Instant lastUsed = null;
        if (!l[6].equals("null"))
            lastUsed = Instant.parse(l[6]);

        return new Pants(parseInt(l[0]), Instant.parse(l[2]), parseInt(l[3]), parseInt(l[4]),
                Clean.valueOf(l[5].toUpperCase()), lastUsed, Material.valueOf(l[7].toUpperCase()),
                Textile.valueOf(l[8].toUpperCase()), Color.valueOf(l[9].toUpperCase()),
                Warmth.valueOf(l[10].toUpperCase()), Fastener.valueOf(l[11].toUpperCase()),
                parseInt(l[12]), l[13], Length.valueOf(l[14].toUpperCase()),
                Function.valueOf(l[15].toUpperCase()), Length.valueOf(l[19].toUpperCase()),
                bool(l[20]), bool(l[21]));
    }

    /**
     * create an instance of the Dress class from a CSV line
     * @param l  A line from the file
     * @return   A new Dress
     */
    private static Dress dressFromCSV(String[] l) {
        Instant lastUsed = null;
        if (!l[6].equals("null"))
            lastUsed = Instant.parse(l[6]);

        return new Dress(parseInt(l[0]), Instant.parse(l[2]), parseInt(l[3]), parseInt(l[4]),
                Clean.valueOf(l[5].toUpperCase()), lastUsed, Material.valueOf(l[7].toUpperCase()),
                Textile.valueOf(l[8].toUpperCase()), Color.valueOf(l[9].toUpperCase()),
                Warmth.valueOf(l[10].toUpperCase()), Fastener.valueOf(l[11].toUpperCase()),
                parseInt(l[12]), l[13], Length.valueOf(l[14].toUpperCase()),
                Function.valueOf(l[15].toUpperCase()), Length.valueOf(l[16].toUpperCase()),
                Neckline.valueOf(l[17].toUpperCase()), Volume.valueOf(l[18].toUpperCase()),
                bool(l[20]));
    }

    /**
     * Add a line of CSV file to the Closet
     * @param line      a line from the CSV
     * @param closet    a Closet instance
     */
    private static void addLine(String[] line, Closet closet) {
        int ID = Integer.parseInt(line[0]);
        String type = line[1];

        switch (Type.valueOf(type.toUpperCase())) {
            case TOP:
                closet.add(topFromCSV(line), ID);
                break;
            case SKIRT:
                closet.add(skirtFromCSV(line), ID);
                break;
            case PANTS:
                closet.add(pantsFromCSV(line), ID);
                break;
            case DRESS:
                closet.add(dressFromCSV(line), ID);
                break;
            default:
                System.out.println("Error: CSV line with no type");
        }
    }

    /**
     * Read the file and generate a Closet from the contents
     * After retrieval, it renames the file to preserve closet state at load
     * @return  A Closet object
     */
    public static Closet retrieve() {
        Closet closet = new Closet();

        try {
            Scanner reader = new Scanner(new File(FILENAME));
            String[] fields = reader.nextLine().split(", ");

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(", ");
                addLine(line, closet);
            }

            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Exception in ClosetArchiver: " + e);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Exception in ClosetArchiver: Some class was missing data.");
        }

        int count = Objects.requireNonNull(new File("archive/").listFiles()).length;
        try {
            Files.move(Paths.get(FILENAME), Paths.get("archive/archive-" + count + ".csv"));
        }
        catch (IOException e) {
            System.out.println("Exception in ClosetArchiver: " + e);
        }

        return closet;
    }

    public static void main(String[] args) {
        ClosetArchiver.save(new Closet());

        Closet fromArchive = ClosetArchiver.retrieve();
    }
}
