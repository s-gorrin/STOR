package database;

import clothing.Top;
import clothing.trait.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

public class RetrieveTest {
    public static final int TEST_ID = 10000;

    @Test
    public void tops() {
        Database.createTables();

        Add.top(new Top(TEST_ID, Instant.now(), 0, 0, Clean.FRESH,
                null, Material.COTTON, Textile.TWILL, Color.BLACK,
                Warmth.WARM, Fastener.BUTTON, 2, "test",
                Length.LONG, Function.FORMAL, Length.LONG, Neckline.COLLAR));

        Assert.assertTrue(Database.keyInDatabase(TEST_ID));
        Assert.assertFalse(Database.keyInDatabase(TEST_ID * 10));

        List<Top> retrieved = Retrieve.tops();

        for (Top top : retrieved) {
            Assert.assertEquals(Type.TOP, top.getType());
            Assert.assertNotNull(top.getSleeves());
        }

        Database.remove(TEST_ID);

        Assert.assertFalse(Database.keyInDatabase(TEST_ID));
    }
}