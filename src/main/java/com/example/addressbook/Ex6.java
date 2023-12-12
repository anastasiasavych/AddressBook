package com.example.addressbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Ex6 {

    @FXML
    private Label lblAnswerRadio;

    @FXML
    private Label lblAnswerCheck;

    @FXML
    private CheckBox checkBox4;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private Button btnAnswer;

    @FXML
    private Label lblAnswerCombo;

    @FXML
    private Button btnNextLab;

    @FXML
    private RadioButton radioBtn4;

    @FXML
    private RadioButton radioBtn3;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private Button btnNextPart;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private Button btnAnswer2;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label lblAnswerChoice;

    private Stage dialogStage;

    private ToggleGroup radiotoggleGroup;




    public void initialize(){
        lblAnswerCheck.setText("");

        lblAnswerChoice.setText("");
        ObservableList<String> answers = choiceBox.getItems();
        answers.add("Правильно");
        answers.add("Неправильно");

        comboBox.setItems(FXCollections.observableArrayList("BorderPane", "AncorePane","VBox", "HBox"));

        lblAnswerRadio.setText("");
        radiotoggleGroup = new ToggleGroup();
        this.radioBtn1.setToggleGroup(radiotoggleGroup);
        this.radioBtn2.setToggleGroup(radiotoggleGroup);
        this.radioBtn3.setToggleGroup(radiotoggleGroup);
        this.radioBtn4.setToggleGroup(radiotoggleGroup);
    }


    @FXML
    void checkBoxAnswer(ActionEvent event) {

            String answer = "Ваша відповідь:";
            if (checkBox1.isSelected())
                answer += "\nпогодженість";
            if (checkBox2.isSelected())
                answer += "\nгнучкість";
            if (checkBox3.isSelected())
                answer += "\nдружність";
            if (checkBox4.isSelected())
                answer += "\nскладність";

            this.lblAnswerCheck.setText(answer);
        }


    @FXML
    String choiceBoxAnswer(ActionEvent event) {
        String answer = choiceBox.getValue();
        this.lblAnswerChoice.setText("Відповідь: "+answer);
        return answer;
    }


    @FXML
    void comboBoxPressed(ActionEvent event) {
        lblAnswerCombo.setText("Відповідь: "+comboBox.getValue().toString());
    }


    @FXML
    void radioAnswer(ActionEvent event) {
        if (this.radiotoggleGroup.getSelectedToggle().equals(this.radioBtn4))
            lblAnswerRadio.setText("Відповідь: Code");
        if (this.radiotoggleGroup.getSelectedToggle().equals(this.radioBtn3))
            lblAnswerRadio.setText("Відповідь: Hierarchy");
        if (this.radiotoggleGroup.getSelectedToggle().equals(this.radioBtn2))
            lblAnswerRadio.setText("Відповідь: Layout");
        if (this.radiotoggleGroup.getSelectedToggle().equals(this.radioBtn1))
            lblAnswerRadio.setText("Відповідь: Properties");
    }


    @FXML
    void nextPartBtnOn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ex6_secondPart.fxml"));
            Parent root = loader.load();
            Ex6_secondPart ex6_secondPart = loader.getController();
            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Ex6 (Second part)");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(btnNextPart.getScene().getWindow());
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);
            ex6_secondPart.setDialogStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void nextLabBtnOn() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("videoPlayer.fxml"));
            Parent root = loader.load();
            VideoController videoController = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Video player");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(btnNextLab.getScene().getWindow());
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            videoController.setDialogStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
