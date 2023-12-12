package com.example.addressbook;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent root  = fxmlLoader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
      primaryStage.setTitle("Адресна книга");

        Scene scene = new Scene(root,600,600);
        primaryStage.setScene(scene);

        HelloController helloController = fxmlLoader.getController();
        helloController.setNewStage(primaryStage);


        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);


        primaryStage.show();
        testData();
    }

    private void testData(){
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();
        addressBook.print();
    }


    public static void main(String[] args) {launch();}
    }