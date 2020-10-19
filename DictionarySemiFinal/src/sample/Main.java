package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;
import java.sql.*;
public class Main extends Application {
    public static DictionaryCommandline dc = new DictionaryCommandline();

    public static void main(String[] args) {
        dc.insertFromFile();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tab1.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);

        primaryStage.show();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
