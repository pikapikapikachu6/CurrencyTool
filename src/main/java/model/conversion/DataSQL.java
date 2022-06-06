package model.conversion;
import java.io.File;
import java.sql.*;

/**
 * database part
 * includes all the operations about database
 */
public class DataSQL {
    private static final String dbName = "currency.db";
    private static final String dbURL = "jdbc:sqlite:" + dbName;

    /**
     * create and setup database
     */
    public DataSQL() {
        DataSQL.createDB();
        DataSQL.setupDB();
    }

    /**
     * Used to: create database
     * Used when: app initial process
     * when the database not exists: create database
     * when the database already exists: show messages
     * or other error messages
     */
    public static void createDB() {
        File dbFile = new File(dbName);
        if (dbFile.exists()) {
            System.out.println("Database already created");
            return;
        }
        try (Connection ignored = DriverManager.getConnection(dbURL)) {
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Used to: remove database
     * Used when: user choose to clean database
     */
    public static void removeDB() {
        File dbFile = new File(dbName);
        if (dbFile.exists()) {
            boolean result = dbFile.delete();
            if (!result) {
                System.out.println("Couldn't delete existing db file");
                System.exit(-1);
            } else {
                System.out.println("Removed existing DB file.");
            }
        } else {
            System.out.println("No existing DB file.");
        }
    }

    /**
     * Used to: setup database
     * Used when: app initial process
     * create table schema
     */
    public static void setupDB() {
        String createGameDataTableSQL =
                """
                CREATE TABLE IF NOT EXISTS conversionData (
                    from_currency text NOT NULL,
                    to_currency text NOT NULL,
                    rate double NOT NULL,
                    PRIMARY KEY (from_currency, to_currency)
                );
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement statement = conn.createStatement()) {
            statement.execute(createGameDataTableSQL);
            System.out.println("Created tables");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Used to: add data into database
     * @param from the currency conversion from
     * @param to the currency conversion to
     * @param rate the conversion rate
     * @return add successfully or not
     */
    public Boolean addRate(String from, String to, String rate) {
        System.out.println("start to add rate:");
        String addSingleStudentWithParametersSQL =
                """
                INSERT INTO conversionData(from_currency, to_currency, rate) VALUES
                    (? , ?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(dbURL);
            PreparedStatement preparedStatement = conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, rate);
            preparedStatement.executeUpdate();
            System.out.println("addRate finished\n");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Used to get the rate saved in the database
     * @param from the currency conversion from
     * @param to the currency conversion to
     * @return the conversion rate value
     */
    public String queryRate(String from, String to) {
        String result = null;
        String studentRangeSQL =
                """
                SELECT rate
                FROM conversionData
                WHERE from_currency = ? and to_currency = ?
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(studentRangeSQL)) {
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) result = results.getString("rate");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return result;
    }

    /**
     * Used to: check whether the data exists in the database or not
     * @param from the currency conversion from
     * @param to the currency conversion to
     * @return
     * "1": data exists in the database
     * "0": data not exists in the database
     */
    public String queryRateExist(String from, String to) {
        String result = null;
        String studentRangeSQL =
                """
                SELECT count(*)
                FROM conversionData
                WHERE from_currency = ? and to_currency = ?
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(studentRangeSQL)) {
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) result = results.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return result;
    }

}
