/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/19/2022
 * Desc: Initialize database
 */

/*
to remove a column from a table:
CREATE TABLE t1_backup AS SELECT a, b FROM t1; // generate the a, b part in a loop
DROP TABLE t1;
ALTER TABLE t1_backup RENAME TO t1;
 */

package database;

import closet.Closet;
import clothing.*;

import java.sql.*;

public class Database {
    public static final String NAME = "closet.sqlite";
    public static final String URL = "jdbc:sqlite:" + NAME;

    /**
     * Create SQLite database and tables for all clothing types
     */
    public static void createTables() {
        try (Connection connection = DriverManager.getConnection(URL);
            Statement clothingTable = connection.createStatement();
            Statement topTable = connection.createStatement();
            Statement pantsTable = connection.createStatement();
            Statement skirtTable = connection.createStatement();
            Statement dressTable = connection.createStatement();
            Statement compTable = connection.createStatement()) {
            String sql =
                    "CREATE TABLE IF NOT EXISTS Clothing (" +
                     "clothing_id INTEGER NOT NULL PRIMARY KEY," +
                     "type TEXT NOT NULL," +
                     "added INTEGER NOT NULL," +
                     "total_uses INTEGER," +
                     "uses_since_cleaned INTEGER," +
                     "clean_level TEXT," +
                     "last_used INTEGER," +
                     "material TEXT NOT NULL," +
                     "textile TEXT NOT NULL," +
                     "color TEXT NOT NULL," +
                     "warmth TEXT NOT NULL," +
                     "fastener TEXT NOT NULL," +
                     "uses_per_clean INTEGER," +
                     "detail TEXT)";
            clothingTable.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Top (" +
                    "clothing_id INTEGER NOT NULL PRIMARY KEY," +
                    "length TEXT NOT NULL," +
                    "function TEXT NOT NULL," +
                    "sleeves TEXT NOT NULL," +
                    "neck TEXT NOT NULL)";
            topTable.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Pants (" +
                    "clothing_id INTEGER NOT NULL PRIMARY KEY," +
                    "length TEXT NOT NULL," +
                    "function TEXT NOT NULL," +
                    "waist TEXT NOT NULL," +
                    "pockets INTEGER NOT NULL," +
                    "belt_loops INTEGER NOT NULL)";
            pantsTable.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Skirt (" +
                    "clothing_id INTEGER NOT NULL PRIMARY KEY," +
                    "length TEXT NOT NULL," +
                    "function TEXT NOT NULL," +
                    "volume TEXT NOT NULL," +
                    "waist TEXT NOT NULL," +
                    "pockets INTEGER NOT NULL)";
            skirtTable.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS Dress (" +
                    "clothing_id INTEGER NOT NULL PRIMARY KEY," +
                    "length TEXT NOT NULL," +
                    "function TEXT NOT NULL," +
                    "sleeves TEXT NOT NULL," +
                    "neck TEXT NOT NULL," +
                    "volume TEXT NOT NULL," +
                    "pockets INTEGER NOT NULL)";
            dressTable.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Compatibility (" +
                    "clothing_id INTEGER NOT NULL PRIMARY KEY)";
            compTable.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * fill a closet from the database
     * @param closet    a closet
     */
    public static void loadCloset(Closet closet) {
        for (Top top : Retrieve.tops())
            closet.add(top, top.getID());

        for (Pants pants : Retrieve.pants()) {
            closet.add(pants, pants.getID());
        }

        for (Skirt skirt : Retrieve.skirts())
            closet.add(skirt, skirt.getID());

        for (Dress dress : Retrieve.dresses())
            closet.add(dress, dress.getID());

        closet.setNextID();

        for (Clothing c : closet.getAllClothing()) {
            System.out.println("loaded in: " + c.getName() + " - " + c.getID());
        }
    }

    /**
     * check if a key is in the database
     * @param ID    the key to check
     * @return      true if it's there, false if not
     */
    public static boolean keyInDatabase(int ID) {
        String sql = "SELECT clothing_id " +
                "FROM Clothing " +
                "WHERE clothing_id == " + ID;

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet results = statement.executeQuery(sql);

            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * delete a clothing item from the database
     * @param ID  the ID of a clothing item
     */
    public static void remove(int ID) {
        String getType = "SELECT type FROM Clothing WHERE clothing_id == " + ID;
        String deleteClothing = "DELETE FROM Clothing WHERE clothing_id == " + ID;
        String deleteComp = "DELETE FROM Compatibility WHERE clothing_id == " + ID;

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){

            // get type of clothing and format it as table name
            String type = statement.executeQuery(getType).getString(1);
            type = type.charAt(0) + type.substring(1).toLowerCase();

            @SuppressWarnings("SqlResolve") String deleteType = "DELETE FROM " + type + " WHERE clothing_id == " + ID;

            statement.executeUpdate(deleteClothing);
            statement.executeUpdate(deleteType);
            statement.executeUpdate(deleteComp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the database with closet info that can change
     * @param closet    a closet of Clothing
     */
    public static void updateCloset(Closet closet) {
        for (Clothing clothing : closet.getAllClothing()) {
            System.out.println("updating: " + clothing.getName());
            long last = clothing.getLastUsed() == null ? 0 : clothing.getLastUsed().getEpochSecond();

            String sql = "UPDATE Clothing " +
                    "SET total_uses = " + clothing.getTotalUses() + ", " +
                    "uses_since_cleaned = " + clothing.getUsesSinceCleaned() + ", " +
                    "clean_level = '" + clothing.getCleanLevel() + "', " +
                    "last_used = " + last +
                    " WHERE clothing_id == " + clothing.getID();

            try (Connection conn = DriverManager.getConnection(Database.URL);
                 Statement statement = conn.createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Database.createTables();
    }
}
