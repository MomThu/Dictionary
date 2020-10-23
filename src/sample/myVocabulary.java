package sample;

import sun.nio.cs.ext.MacIceland;

import java.sql.*;

public class myVocabulary {
    public static void addToMyVocab (String eng, String vie){
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MyVocab.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql ="INSERT INTO vocab(ENG, VIE)" +
                    "VALUES (?, ?)";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, eng);
            stmt.setString(2, vie);
            stmt.execute();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void deleteVocab(String eng) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MyVocab.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "DELETE from vocab where ENG LIKE '" + eng + "';";
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void editVocab(String eng, String vie) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MyVocab.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "UPDATE vocab set VIE = '" + vie + "' where word LIKE '" + eng + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
