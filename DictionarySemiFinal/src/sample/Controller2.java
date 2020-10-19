package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;


public class Controller2 {
    @FXML
    private TextArea textField1;

    @FXML
    private TextArea textField2;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    public void translate_api1(ActionEvent event) {
        String Text = textField1.getText();
        api_translate.setUrlVi();
        String Text2 = api_translate.TranslateFromJson(Text);
        textField2.setText(Text2);
    }

    public void translate_api2(ActionEvent event) {
        String Text = textField1.getText();
        api_translate.setUrlEn();
        String Text2 = api_translate.TranslateFromJson(Text);
        textField2.setText(Text2);
    }

    public void initialize() {
        textField1.setWrapText(true);
        textField2.setWrapText(true);
    }
}
