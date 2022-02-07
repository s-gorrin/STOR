/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/04/2022
 * Desc: A class to serialize Clothing objects to json and convert them back
 */

package closet;

import clothing.Clothing;
import clothing.Pants;
import clothing.SerialClothing;
import clothing.Top;
import clothing.trait.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ClosetSerializer {
    public static final String FILENAME = "closet.json";

    /**
     * Write a closet to JSON
     * @param closet        the closet being written
     * @throws IOException  file writing exception
     */
    public static void writeJSON(Closet closet) throws IOException {
        Gson gson = new GsonBuilder().create();

        FileWriter writer = new FileWriter(FILENAME);
        writer.write(gson.toJson(closet.getAllClothing()));
        writer.close();
    }

    /**
     * Read json into an intermediate SerialClothing class which can contain any clothing trait.
     * From there, use methods to extract the relevant Clothing subclass and add it to the Closet
     * @param closet                    a closet to fill with Clothing from JSON
     * @throws FileNotFoundException    exception from file reading
     */
    public static void readJSON(Closet closet) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();

        try (JsonReader reader = new JsonReader(new FileReader(FILENAME))) {
            reader.beginArray(); // This line is critical
            while (reader.hasNext()) {
                SerialClothing item = gson.fromJson(reader, SerialClothing.class);

                switch (item.getType()) {
                    case TOP:
                        closet.add(item.toTop(), item.getID());
                        break;
                    case PANTS:
                        closet.add(item.toPants(), item.getID());
                        break;
                    case SKIRT:
                        closet.add(item.toSkirt(), item.getID());
                        break;
                    case DRESS:
                        closet.add(item.toDress(), item.getID());
                        break;
                }
            }
            reader.close();

            // After reading, copy the file to the archive
            int count = Objects.requireNonNull(new File("archive/").listFiles()).length;
            Files.copy(Paths.get(FILENAME), Paths.get("archive/archive-" + count + ".json"));
        }
        catch (IOException e) {
            System.out.println("There was a problem in ReadJSON: " + e);
        }
    }


    public static void main(String[] args) {
        Closet closet = new Closet();
        closet.add(new Top(Length.LONG, Function.CASUAL, Length.MEDIUM, Neckline.COLLAR));
        closet.get(0).setColor(Color.GREEN);
        closet.add(new Top(Length.SHORT, Function.WORK, Length.LONG, Neckline.COWL));
        closet.add(new Top(Length.MEDIUM, Function.ATHLETIC, Length.SHORT, Neckline.CREW));
        closet.add(new Pants(Length.SHORT, Function.FORMAL, Length.LONG, true, true));
        closet.add(new Pants(Length.MEDIUM, Function.COMFORTABLE, Length.MEDIUM, true, false));
        closet.add(new Pants(Length.LONG, Function.FORMAL, Length.MEDIUM, true, true));

        try { ClosetSerializer.writeJSON(closet); }
        catch (IOException e) {
            System.out.println("problem writing json: " + e.getMessage());
        }

        Closet read_in = new Closet();
        try { ClosetSerializer.readJSON(read_in); }
        catch (FileNotFoundException e) {
            System.out.println("json file not found: " + e.getMessage());
        }

        for (Clothing c : closet.getAllClothing()) {
            System.out.print("ID: " + c.getID() + " type: " + c.getType().toString().toLowerCase() +
                    " length: ");
            if (c.getType() == Type.TOP)
                System.out.println(((Top)c).getLength());
            if (c.getType() == Type.PANTS)
                System.out.println((((Pants)c).getLength()));
        }
    }
}
