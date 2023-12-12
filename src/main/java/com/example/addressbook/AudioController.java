package com.example.addressbook;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AudioController implements Initializable {

    @FXML
    private Slider volume;

    @FXML
    private ProgressBar audioProgress;

    @FXML
    private Label label_audio;


    private File file;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;

    private ArrayList<File> songs;
    private int songNumber;

    private Stage dialogStage;
    private double currentProgress;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        file = new File("music");
        files = file.listFiles();
        songs = new ArrayList<File>();

        if (files != null) {
            for (File f : files) {
                songs.add(f);
                System.out.println(f);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volume.getValue() * 0.01);
            }
        });
    }




        @FXML
    void play(ActionEvent event) {
            mediaPlayer.play();
            label_audio.setText(songs.get(songNumber).getName());
        }

    @FXML
    void pause(ActionEvent event) {
        mediaPlayer.pause();
        label_audio.setText(songs.get(songNumber).getName());
    }

    @FXML
    void next(ActionEvent event) {
        if(songNumber < songs.size()-1){
            songNumber++;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            label_audio.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            label_audio.setText(songs.get(songNumber).getName());
        }else {
            songNumber = 0;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            label_audio.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            label_audio.setText(songs.get(songNumber).getName());
        }}


    @FXML
    void previous(ActionEvent event) {
        if(songNumber >0){
            songNumber--;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            label_audio.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            label_audio.setText(songs.get(songNumber).getName());
        }
        else {
            songNumber  = songs.size()-1;
            mediaPlayer.stop();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            label_audio.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            label_audio.setText(songs.get(songNumber).getName());
        }}


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}

