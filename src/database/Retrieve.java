/**
 * Name: Seth Gorrin
 * Class: CS-622
 * Date: 2/20/2022
 * Desc: Add an item to the database
 */

package database;

import clothing.Dress;
import clothing.Pants;
import clothing.Skirt;
import clothing.Top;
import clothing.trait.*;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Retrieve {

    /**
     * get tops from the database
     * @return  a List of tops
     */
    public static List<Top> tops() {
        List<Top> tops = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM Clothing " +
                     "JOIN Top " +
                     "WHERE Clothing.type IS 'TOP' AND Top.clothing_id == Clothing.clothing_id";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet r = statement.executeQuery(sql);
            while (r.next()) {
                tops.add(new Top(r.getInt(1),
                        Instant.ofEpochSecond(r.getLong(3)),
                        r.getInt(4),
                        r.getInt(5),
                        Clean.valueOf(r.getString(6)),
                        Instant.ofEpochSecond(r.getLong(7)),
                        Material.valueOf(r.getString(8)),
                        Textile.valueOf(r.getString(9)),
                        Color.valueOf(r.getString(10)),
                        Warmth.valueOf(r.getString(11)),
                        Fastener.valueOf(r.getString(12)),
                        r.getInt(13),
                        r.getString(14),
                        Length.valueOf(r.getString(16)),
                        Function.valueOf(r.getString(17)),
                        Length.valueOf(r.getString(18)),
                        Neckline.valueOf(r.getString(19))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tops;
    }

    /**
     * get pants from the database
     * @return  a List of pants
     */
    public static List<Pants> pants() {
        List<Pants> pants = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM Clothing " +
                     "JOIN Pants " +
                     "WHERE Clothing.type IS 'PANTS' AND Pants.clothing_id == Clothing.clothing_id";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet r = statement.executeQuery(sql);
            while (r.next()) {
                pants.add(new Pants(r.getInt(1),
                        Instant.ofEpochSecond(r.getLong(3)),
                        r.getInt(4),
                        r.getInt(5),
                        Clean.valueOf(r.getString(6)),
                        Instant.ofEpochSecond(r.getLong(7)),
                        Material.valueOf(r.getString(8)),
                        Textile.valueOf(r.getString(9)),
                        Color.valueOf(r.getString(10)),
                        Warmth.valueOf(r.getString(11)),
                        Fastener.valueOf(r.getString(12)),
                        r.getInt(13),
                        r.getString(14),
                        Length.valueOf(r.getString(16)),
                        Function.valueOf(r.getString(17)),
                        Length.valueOf(r.getString(18)),
                        r.getInt(19) == 1,
                        r.getInt(20) == 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pants;
    }

    /**
     * get skirts from the database
     * @return  a List of skirts
     */
    public static List<Skirt> skirts() {
        List<Skirt> skirts = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM Clothing " +
                "JOIN Skirt " +
                "WHERE Clothing.type IS 'SKIRT' AND Skirt.clothing_id == Clothing.clothing_id";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet r = statement.executeQuery(sql);
            while (r.next()) {
                skirts.add(new Skirt(r.getInt(1),
                        Instant.ofEpochSecond(r.getLong(3)),
                        r.getInt(4),
                        r.getInt(5),
                        Clean.valueOf(r.getString(6)),
                        Instant.ofEpochSecond(r.getLong(7)),
                        Material.valueOf(r.getString(8)),
                        Textile.valueOf(r.getString(9)),
                        Color.valueOf(r.getString(10)),
                        Warmth.valueOf(r.getString(11)),
                        Fastener.valueOf(r.getString(12)),
                        r.getInt(13),
                        r.getString(14),
                        Length.valueOf(r.getString(16)),
                        Function.valueOf(r.getString(17)),
                        Volume.valueOf(r.getString(18)),
                        Length.valueOf(r.getString(19)),
                        r.getInt(20) == 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skirts;
    }

    /**
     * get dresses from the database
     * @return  a List of dresses
     */
    public static List<Dress> dresses() {
        List<Dress> dresses = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM Clothing " +
                "JOIN Dress " +
                "WHERE Clothing.type IS 'DRESS' AND Dress.clothing_id == Clothing.clothing_id";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet r = statement.executeQuery(sql);
            while (r.next()) {
                dresses.add(new Dress(r.getInt(1),
                        Instant.ofEpochSecond(r.getLong(3)),
                        r.getInt(4),
                        r.getInt(5),
                        Clean.valueOf(r.getString(6)),
                        Instant.ofEpochSecond(r.getLong(7)),
                        Material.valueOf(r.getString(8)),
                        Textile.valueOf(r.getString(9)),
                        Color.valueOf(r.getString(10)),
                        Warmth.valueOf(r.getString(11)),
                        Fastener.valueOf(r.getString(12)),
                        r.getInt(13),
                        r.getString(14),
                        Length.valueOf(r.getString(16)),
                        Function.valueOf(r.getString(17)),
                        Length.valueOf(r.getString(18)),
                        Neckline.valueOf(r.getString(19)),
                        Volume.valueOf(r.getString(20)),
                        r.getInt(21) == 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dresses;
    }

    /**
     * retrieve a compatibility record from the database
     * @return  a compatibility record
     */
    public static HashMap<Integer, HashSet<Integer>> compatibility() {
        HashMap<Integer, HashSet<Integer>> record = new HashMap<>();
        String sql = "SELECT * " +
                     "FROM Compatibility";

        try (Connection conn = DriverManager.getConnection(Database.URL);
             Statement statement = conn.createStatement()){
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                int ID = results.getInt(1);
                record.put(ID, new HashSet<>());
                for (int i = 2; i <= results.getMetaData().getColumnCount(); i++) {
                    results.getInt(i);
                    if (!results.wasNull())
                        record.get(ID).add(Integer.parseInt(results.getMetaData().getColumnName(i)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }
}
