package sample;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    @FXML
    private TextField text;

    @FXML
    private TextArea text2;

    @FXML
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea vietnamese;

    @FXML
    private WebView webView;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem add;

    @FXML
    private MenuItem remove;

    @FXML
    private MenuItem edit;

    //load các từ có chung kí tự đầu vào listview
    public void loadPre (String words) {
        list.clear();
        ArrayList<String> pre = Main.dc.dictionarySearcher(words);
        for(int i=1; i<=pre.size(); i++) {
            list.add(pre.get(i-1));
        }
    }

    //button search trong scene1 & scene4
    @FXML
    public void Search (ActionEvent event) {
        String Text = text.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Main.dc.dictionaryLookup(Text), "text/html");
        webEngine.setUserStyleSheetLocation(getClass().getResource("webview.css").toString());
        listView.setVisible(false);
    }

    @FXML
    public void AddToVocabulary(ActionEvent event) {
        String eng = text.getText();
        String vie = Main.dc.dictionaryLookup(eng);
        if(Controller3.isExist(eng) == false) {
            myVocabulary.addToMyVocab(eng, vie);
            Controller3.addVocabFromDb();
        } else {
            System.out.println("This word exists.");
        }
    }

    //action click vào listView để hiện ra nghĩa từ
    public void OnclickListView(MouseEvent event) {
        String clickList = listView.getSelectionModel().getSelectedItem();
        text.setText(clickList);
        String Text = text.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Main.dc.dictionaryLookup(Text), "text/html");
        webEngine.setUserStyleSheetLocation(getClass().getResource("webview.css").toString());
        listView.setVisible(false);
    }

    //action thêm từ
    public void AddNewWord(ActionEvent event) {
        String word = text.getText();
        if(Main.dc.dictionaryLookup(word).equals("Not found")) {
            String explain = text2.getText();
            Main.dc.addNewWord(word, explain);
            Main.dc.addNewWordToDb(word, explain);
        } else {
            System.out.println("Add failed. This word exists.");
        }
    }

    //action xóa từ
    public void RemoveWord(ActionEvent event) {
        String word = text.getText();
        if(Main.dc.dictionaryLookup(word).equals("Not found")) {
            System.out.println("This word isn't exist. Can't delete this word.");
        } else {
            Main.dc.deleteWord(word);
            Main.dc.deleteWordFromDb(word);
        }
    }

    //action sửa từ
    public void EditWord(ActionEvent event) {
        String word = text.getText();
        String explain = text2.getText();
        Main.dc.editWord(word, explain);
        Main.dc.editWordFromDb(word, explain);
    }

    //action âm thanh
    public void Audio(ActionEvent event) {
        String word = text.getText();
        textToSpeech.speech(word);
    }

    public void apiSpeech(ActionEvent event) {
        String word = text.getText();
        api_speech.textToSpeechApi("en-GB", "en-GB-Susan", word);
    }

    //action ấn down/up trên listview
    public void OnKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.DOWN) {
            listView.getSelectionModel().selectNext();
        }
        else if(keyEvent.getCode() == KeyCode.UP) {
            listView.getSelectionModel().selectPrevious();
        }
    }

    //bắt sự kiện On Action enter cho textfiled
    public void textFieldOnAction(ActionEvent event) {
        String clickList = listView.getSelectionModel().getSelectedItem();
        text.setText(clickList);
        String Text = text.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Main.dc.dictionaryLookup(Text), "text/html");
        webEngine.setUserStyleSheetLocation(getClass().getResource("webview.css").toString());
        listView.setVisible(false);
    }

    //hàm chạy all time trong ctrinh
    public void initialize() {
        listView.setVisible(false);
        listView.setItems(list);
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()) {
                loadPre(newValue);
                listView.setVisible(true);
                listView.getSelectionModel().selectFirst();
            } else {
                list.clear();
                listView.setVisible(false);
                listView.getSelectionModel().clearSelection();
            }
        });
    }

}
