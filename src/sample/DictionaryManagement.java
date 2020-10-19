package sample;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.sql.*;
public class DictionaryManagement extends Dictionary {

    // thêm từ vào mảng
    public void addNewWord(String eng, String vie) {
        Word newWord = new Word(eng, vie);
        words.add(newWord);
    }

    //thêm từ vào database
    public void addNewWordToDb(String eng, String vie) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dict_hh.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql ="INSERT INTO av(id, word, html, description, pronounce)" +
                    "VALUES (?, ?, ?, ?, ?)";
            stmt = c.prepareStatement(sql);
            stmt.setString(2, eng);
            stmt.setString(3, vie);
            stmt.execute();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    //xóa từ khỏi mảng
    public void deleteWord(String english) {
        for(int i = 0; i<words.size(); i++) {
            if(words.get(i).getWord_target().equals(english)) {
                words.remove(i);
            }
        }
    }

    //xóa từ khỏi database
    public void deleteWordFromDb(String eng) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dict_hh.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "DELETE from av where word LIKE '" + eng + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    //sửa từ trong mảng
    public void editWord(String english, String explain) {
        for(int i=0; i<words.size(); i++) {
            if(words.get(i).getWord_target().equals(english)) {
                words.get(i).setWord_explain(explain);
            }
        }
    }

    //sửa từ trong Database
    public void editWordFromDb(String eng, String vie) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dict_hh.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "UPDATE av set html = '" + vie + "' where word LIKE '" + eng + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    //thêm từ từ bàn phím
    public void insertFromCommandline() {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            String eng = sc.nextLine();
            String vie = sc.nextLine();
            addNewWord(eng, vie);
        }
    }
    /*public void insertFromFile() {
        try {
            Scanner sc = new Scanner(new File("dictionaries.txt"));
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] word = s.split("\\t");
                addNewWord(word[0], word[1]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't not open file");
        }
    }*/

    //thêm từ từ file db
    public void insertFromFile() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dict_hh.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM av;");

            while (rs.next()) {
                //int id = rs.getInt("id");
                String word = rs.getString("word");
                String html = rs.getString("html");
                //String description = rs.getString("");
                //String pronounce = rs.getString("pronounce");
                addNewWord(word, html);
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    //tìm kiếm từ
    public String dictionaryLookup(String search) {
        int ok=0;
        for(int i=0; i<words.size(); i++) {
            if(search.equals(words.get(i).getWord_target()))  {
                ok = 1;
                return words.get(i).getWord_explain();
            }
        }
        return "Not found";
    }

    public void dictionaryExportToFile() {
        try {
            Formatter ft = new Formatter("cout.txt");
            for (int i = 0; i < words.size(); i++) {
                ft.format("%s : %s%n", words.get(i).getWord_target(), words.get(i).getWord_explain());
            }
            ft.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }

}

