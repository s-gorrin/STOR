/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/20/2022
 * Desc: Add an item to the database
 */

package database;

import closet.Closet;
import clothing.Clothing;
import clothing.Pants;
import clothing.Skirt;
import clothing.Top;
import clothing.Dress;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class Add {

    /**
     * add a closet to the database
     * @param closet    a closet
     */
    public static void addCloset(Closet closet) {
        for (Clothing clothing : closet.getAllClothing()) {
            if (!Database.keyInDatabase(clothing.getID()))
                switch (clothing.getType()) {
                    case TOP:
                        top((Top) clothing);
                        break;
                    case PANTS:
                        pants((Pants) clothing);
                        break;
                    case SKIRT:
                        skirt((Skirt) clothing);
                        break;
                    case DRESS:
                        dress((Dress) clothing);
                        break;
                }
        }
    }

    /**
     * Add a clothing item
     * @param clothing  the Clothing
     */
    public static void clothing(Clothing clothing) {
        String sql = "INSERT INTO Clothing (clothing_id, type, added, total_uses, uses_since_cleaned, clean_level," +
                "last_used, material, textile, color, warmth, fastener, uses_per_clean, detail) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, clothing.getID());
            statement.setString(2, clothing.getType().name());
            statement.setLong(3, clothing.getAdded().getEpochSecond());
            statement.setInt(4, clothing.getTotalUses());
            statement.setInt(5, clothing.getUsesSinceCleaned());
            statement.setString(6, clothing.getCleanLevel().name());

            if (clothing.getLastUsed() != null)
                statement.setLong(7, clothing.getLastUsed().getEpochSecond());

            statement.setString(8, clothing.getMaterial().name());
            statement.setString(9, clothing.getTextile().name());
            statement.setString(10, clothing.getColor().name());
            statement.setString(11, clothing.getWarmth().name());
            statement.setString(12, clothing.getFastener().name());
            statement.setInt(13, clothing.getUsesPerCleanLevel());
            statement.setString(14, clothing.getDetail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a Top to the database
     * @param top   a Top
     */
    public static void top(Top top) {
        clothing(top);

        String sql = "INSERT INTO Top (clothing_id, length, function, sleeves, neck) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, top.getID());
            statement.setString(2, top.getLength().name());
            statement.setString(3, top.getFunction().name());
            statement.setString(4, top.getSleeves().name());
            statement.setString(5, top.getNeck().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a Pants to the database
     * @param pants a Pants
     */
    public static void pants(Pants pants) {
        clothing(pants);

        String sql = "INSERT INTO Pants (clothing_id, length, function, waist, pockets, belt_loops) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, pants.getID());
            statement.setString(2, pants.getLength().name());
            statement.setString(3, pants.getFunction().name());
            statement.setString(4, pants.getWaist().name());
            statement.setInt(5, pants.getPockets() ? 1 : 0);
            statement.setInt(6, pants.getBeltLoops() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a Skirt to the database
     * @param skirt a Skirt
     */
    public static void skirt(Skirt skirt) {
        clothing(skirt);

        String sql = "INSERT INTO Skirt (clothing_id, length, function, volume, waist, pockets) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, skirt.getID());
            statement.setString(2, skirt.getLength().name());
            statement.setString(3, skirt.getFunction().name());
            statement.setString(4, skirt.getVolume().name());
            statement.setString(5, skirt.getWaist().name());
            statement.setInt(6, skirt.getPockets() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a Dress to the database
     * @param dress a Dress
     */
    public static void dress(Dress dress) {
        clothing(dress);

        String sql = "INSERT INTO Dress (clothing_id, length, function, sleeves, neck, volume, pockets) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, dress.getID());
            statement.setString(2, dress.getLength().name());
            statement.setString(3, dress.getFunction().name());
            statement.setString(4, dress.getSleeves().name());
            statement.setString(5, dress.getNeck().name());
            statement.setString(6, dress.getVolume().name());
            statement.setInt(7, dress.getPockets() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add the item to the compatibility table
     * @param ID            the current item
     * @param compatible    a list of compatible items
     */
    public static void compatibility(int ID, List<Integer> compatible) {
        String drop = "DROP TABLE IF EXISTS Old_comp";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()) {
            for (Integer id : compatible) {
                @SuppressWarnings("SqlResolve") String row = "UPDATE Compatibility SET \"" + id + "\" = "
                        + ID + " WHERE clothing_id == " + ID;
                conn.prepareStatement(row).executeUpdate();
            }

            statement.executeUpdate(drop); // drop the old compatibility table after making the new one
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * reset the compatibility table to re-save because this is designed badly :)
     */
    public static void resetCompatibility(Set<Integer> IDs) {
        String rename = "ALTER TABLE Compatibility RENAME TO Old_comp";
        String create = "CREATE TABLE IF NOT EXISTS Compatibility (" +
                "clothing_id INTEGER NOT NULL PRIMARY KEY)";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(rename);
            statement.executeUpdate(create);

            for (int ID : IDs) {
                String column = "ALTER TABLE Compatibility ADD COLUMN '" + ID + "' INTEGER";
                String row = "INSERT INTO Compatibility (clothing_id) VALUES (" + ID + ")";
                conn.prepareStatement(column).executeUpdate();
                conn.prepareStatement(row).executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
