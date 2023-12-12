package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    @FXML
    private TextField txtPip;

    @FXML
    private TextField txtPhone;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;


    private Person person;

    private boolean okClicked = false;

    public Person getPerson() {
        return person;
    }

    private Stage dialogStage;

    public void setAddressBook(CollectionAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    private CollectionAddressBook addressBook;
    ;
    private HelloController helloController;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    Stage stage = new Stage();

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.setText(person.getPip());
        txtPhone.setText(person.getPhone());

    }

    public void initialize() {
        // Створення нового об'єкта Person
        person = new Person("", "");
        txtPip.textProperty().bindBidirectional(person.pipProperty());
        txtPhone.textProperty().bindBidirectional(person.phoneProperty());
    }
        @FXML
        void okBtnOn () {
            person.setPip(txtPip.getText());
            person.setPhone(txtPhone.getText());
            okClicked = true;
            dialogStage.close();
        }

        @FXML
        void cancelBtnOn () {
            dialogStage.close();
        }

        public void setDialogStage (Stage dialogStage){
            this.dialogStage = dialogStage;
        }



}
