/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/10/2022
 * Desc: Create a new thread to save the closet in the background when a new item is added
 */

package closet;

import clothing.ClosetSerializer;

import java.io.IOException;

public class BackgroundSaver extends Thread {
    private final Closet closet;
    private final Compatible compatible;

    public BackgroundSaver(Closet closet, Compatible compatible) {
        this.closet = closet;
        this.compatible = compatible;
    }

    public void run() {
        try {
            ClosetSerializer.writeJSON(closet);
            compatible.writeToFile();
        }
        catch (IOException e) {
            System.out.println("background save had a problem");
        }
    }
}

//create new thread, thread.start()
