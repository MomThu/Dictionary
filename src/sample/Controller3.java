package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller3 {
    @FXML
    private ListView<String> listView2;

    @FXML
    static ObservableList<String> list2 = FXCollections.observableArrayList();

    @FXML
    private WebView webView2;

    public static void addVocabFromDb() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MyVocab.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vocab;");
            list2.clear();
            while (rs.next()) {
                String eng = rs.getString("ENG");
                //String vie = rs.getString("VIE");
                list2.add(eng);
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

    public static boolean isExist (String word) {
        for(int i = 0; i < list2.size(); i++) {
            if (word.equals(list2.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void searchVocab (MouseEvent event) {
        String clickList = listView2.getSelectionModel().getSelectedItem();
        WebEngine webEngine = webView2.getEngine();
        webEngine.loadContent(Main.dc.dictionaryLookup(clickList), "text/html");
        webEngine.setUserStyleSheetLocation(getClass().getResource("webview.css").toString());
    }

    public void OnKeyPressVocab (KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String clickList = listView2.getSelectionModel().getSelectedItem();
            WebEngine webEngine = webView2.getEngine();
            webEngine.loadContent(Main.dc.dictionaryLookup(clickList), "text/html");
            webEngine.setUserStyleSheetLocation(getClass().getResource("webview.css").toString());
        }
    }

    public void initialize() {
        addVocabFromDb();
        listView2.setItems(list2);
    }
}
