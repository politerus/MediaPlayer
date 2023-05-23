package com.example.mediaplayer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class Controller {
    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider volumeSlider;

    @FXML
    private  Slider rewindSlider;

    @FXML
    private void OPenButton(){

        FileChooser fileChooser = new FileChooser();//открыте файла(выбор что проигрывать)

        //создаю фильт который будет воспринимать разные форматы  аудио и видео файлов

        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("Select file (*.mp4 )", " *.mp4");
        fileChooser.getExtensionFilters().add(mp4Filter);

        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toURI().toString();
        // String filePath = fileChooser.showOpenDialog(null).toURI().toString();

        if (filePath != null){

            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);//привязали медиа вью с медиаплеером



            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height= mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width")); //ширина всегда меняется
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));//высота всегда меняется

          /*  SimpleMetroArcGauge eq = new SimpleMetroArcGauge();
            eq.setMinValue(-18.0);
            eq.setMaxValue(18.0);
            eq.setPrefSize(300,100);



           */

            mediaPlayer.play();//запуск медиа плеера
        }






    }
    // Создается ползунок перемотки и задается исчезание
    @FXML
    private void  rewindSlider(){
        rewindSlider.setOnMouseClicked(mouseEvent -> {
            rewindSlider.setMin(0);
            rewindSlider.setMax(1);
            rewindSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (mediaPlayer != null) {
                    Duration duration = mediaPlayer.getTotalDuration();
                    mediaPlayer.seek(duration.multiply(newValue.doubleValue()));
                    rewindSlider.setStyle("-fx-opacity: 0;");
                    rewindSlider.setOnMouseEntered(event -> rewindSlider.setStyle("-fx-opacity: 1;"));
                    rewindSlider.setOnMouseExited(event -> rewindSlider.setStyle("-fx-opacity: 0;"));


                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(3), new KeyValue(volumeSlider.opacityProperty(), 0))
                    );
                    timeline.play();

                }
            });
        });


    }




    // Создается ползунок громкости и задается исчезание
    @FXML
    private  void volumeSlider(){
        volumeSlider.setValue(mediaPlayer.getVolume()*100);
        volumeSlider.setStyle("-fx-opacity: 0;");
        volumeSlider.setOnMouseEntered(event -> volumeSlider.setStyle("-fx-opacity: 1;"));
        volumeSlider.setOnMouseExited(event -> volumeSlider.setStyle("-fx-opacity: 0;"));


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(volumeSlider.opacityProperty(), 0))
        );
        timeline.play();

        volumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeSlider.getValue()/100))
        ;

    }
    @FXML
    private void playButton(){
        mediaPlayer.setRate(1.0);
        mediaPlayer.play();

    }

    @FXML
    private void pauseButton(){
        mediaPlayer.pause();
    }
    @FXML
    private void stopButton(){
        mediaPlayer.stop();
    }
    @FXML
    private void slowButton(){
        mediaPlayer.setRate(0.5);
    }
    @FXML
    private void fastButton(){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void exitButton(){
        System.exit(0);

    }



}