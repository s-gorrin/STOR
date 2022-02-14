/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/04/2022
 * Desc: A class to serialize Clothing objects to json and convert them back
 */

package clothing;

import closet.Closet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ClosetSerializer {
    public static final String FILENAME = "closet.json";
    public static final String TEST_FILE = "test/closet.json";
    private static final int TOO_MANY_ARCHIVES = 40;

    private static boolean test = false;

    public static void setTest() {
        test = true;
    }

    /**
     * Write a closet to JSON
     * @param closet        the closet being written
     * @throws IOException  file writing exception
     */
    public static void writeJSON(Closet closet) throws IOException {
        Gson gson = new GsonBuilder().create();
        FileWriter writer;

        if (test)
            writer = new FileWriter(TEST_FILE);

        else
            writer = new FileWriter(FILENAME);
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
        String filename = FILENAME;
        if (test)
            filename = TEST_FILE;

        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            reader.beginArray(); // This line is critical
            while (reader.hasNext()) {
                SerialClothing item = gson.fromJson(reader, SerialClothing.class);

                if (item.sleeves != null && item.volume == null)        // top
                    closet.add(item.toTop(), item.getID());
                else if (item.volume == null && item.waist != null)     // pants
                    closet.add(item.toPants(), item.getID());
                else if (item.volume != null && item.sleeves == null)   // skirt
                    closet.add(item.toSkirt(), item.getID());
                else if (item.volume != null)                           // dress
                    closet.add(item.toDress(), item.getID());
            }
            reader.close();

            // After reading, copy the file to the archive
            int count = Objects.requireNonNull(new File("archive/").listFiles()).length;
            Files.copy(Paths.get(FILENAME), Paths.get("archive/archive-" + count + ".json"));

            if (count >= TOO_MANY_ARCHIVES)
                dumpOldSaves();
        }
        catch (IOException e) {
            System.out.println("There was a problem in ReadJSON: " + e);
        }
    }

    /**
     * when there are too many archives, write the newest 10 to the oldest 10
     * these numbers may change
     */
    private static void dumpOldSaves() {
        for (int i = 1; i <= TOO_MANY_ARCHIVES/2; i++) {
            try{
                Path sourcePath = new File("archive/archive-" + (i + TOO_MANY_ARCHIVES/2) + ".json").toPath();
                Path targetPath = Paths.get("archive/archive-" + i + ".json");
                File file = targetPath.toFile();
                if(file.isFile()){
                    Files.delete(targetPath);
                }
                Files.move(sourcePath, targetPath);
            }
            catch (IOException e) {
                System.out.println("problem with dumping old saves");
            }
        }
    }
}
