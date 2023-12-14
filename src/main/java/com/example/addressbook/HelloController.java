package com.example.addressbook;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;


public class HelloController {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Label labelCount;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnOther;

    @FXML
    private TableView<Person> tableAddressBook;

    @FXML
    private TableColumn<Person,String> columnPIP;

    @FXML
    private TableColumn<Person,String> columnPhone;

    private EditController editController;


    private Stage newStage;
    private Stage editDialogStage;
    private Parent root;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private ObservableList<Person> backupList;

    public void setNewStage(Stage newStage){
        this.newStage = newStage;
    }


    public void initialize(){
        try {
            fxmlLoader.setLocation(getClass().getResource("edit.fxml"));
            root = fxmlLoader.load();
            editController = fxmlLoader.getController();
            EditController editController = fxmlLoader.getController();
            editController.setHelloController(this);
            editController.setAddressBook(addressBookImpl);
        }catch (IOException e){
            e.printStackTrace();
        }
        columnPIP.setCellValueFactory(new PropertyValueFactory<Person,String>("pip"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("phone"));
        addressBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());

        addressBookImpl.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(addressBookImpl.getPersonList());
        tableAddressBook.setItems(addressBookImpl.getPersonList());

        btnSearch.setOnAction(event -> searchBtnOn(event));
        btnDelete.setOnAction(event -> deleteBtnOn(event));

    }


    @FXML
    void searchBtnOn(ActionEvent event) {
        addressBookImpl.getPersonList().clear();
        for (Person person : backupList){
            if(person.getPip().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    person.getPhone().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                addressBookImpl.getPersonList().add(person);
            }
        }
    }



    @FXML
    private void updateCountLabel(){
        labelCount.setText("Кількість записів: "+addressBookImpl.getPersonList().size());
    }



    @FXML
    private void deleteBtnOn(ActionEvent event) {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            addressBookImpl.delete(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a person to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    public void addBtnOn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
            Parent root = loader.load();
            EditController editController = loader.getController();

            editController.setHelloController(this);

            editController.setAddressBook(addressBookImpl);

            Stage newStage = new Stage();
            newStage.setTitle("Додавання запису");
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(btnAdd.getScene().getWindow());
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            editController.setDialogStage(newStage);

            newStage.showAndWait();

            if (editController.isOkClicked()) {
                Person newPerson = editController.getPerson();
                if (newPerson != null) {
                    addressBookImpl.add(newPerson);
                    updateTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateTable() {
        Platform.runLater(() -> {
            tableAddressBook.setItems(addressBookImpl.getPersonList());
        });
    }


    @FXML
    public void editBtnOn() {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
                Parent root = loader.load();
                EditController editController = loader.getController();
                editController.setPerson(selectedPerson);

                Stage editDialogStage = new Stage();
                editDialogStage.setTitle("Редагування запису");
                editDialogStage.initModality(Modality.WINDOW_MODAL);
                editDialogStage.initOwner(btnEdit.getScene().getWindow());
                Scene scene = new Scene(root);
                editDialogStage.setScene(scene);

                editController.setDialogStage(editDialogStage);

                editDialogStage.showAndWait();

                if (editController.isOkClicked()) {
                    addressBookImpl.update(selectedPerson);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void otherBtnOn(ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ex6.fxml"));
                Parent root = loader.load();
                Ex6 ex6 = loader.getController();

                Stage otherDialogStage = new Stage();
                otherDialogStage.setTitle("Ex6");
                otherDialogStage.initModality(Modality.WINDOW_MODAL);
                otherDialogStage.initOwner(btnOther.getScene().getWindow());
                otherDialogStage.setMinHeight(480);
                otherDialogStage.setMinWidth(855);
                otherDialogStage.setMaxHeight(480);
                otherDialogStage.setMaxWidth(855);
                Scene scene = new Scene(root);
                otherDialogStage.setScene(scene);

                ex6.setDialogStage(otherDialogStage);

                otherDialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }




    @FXML
    public void exitBtnOn(ActionEvent event){
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public CollectionAddressBook getAddressBookImpl() {
        return addressBookImpl;
    }

}
