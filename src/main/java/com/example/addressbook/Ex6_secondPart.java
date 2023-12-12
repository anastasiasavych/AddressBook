package com.example.addressbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ex6_secondPart {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private RadioMenuItem first;

    @FXML
    private TextField textField;

    @FXML
    private RadioMenuItem second;

    @FXML
    private ToggleGroup toggleContextText;


    private Stage dialogStage;

    @FXML
    void toggleContextText(ActionEvent event) {
        if (toggleContextText.getSelectedToggle().equals(this.first))
            textField.setText("RandomText");
        else if (toggleContextText.getSelectedToggle().equals(this.second))
            textField.setText(null);
    }


    @FXML
    void exitButton(ActionEvent event) {
        dialogStage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Success");
        dialogStage.close();


    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}

