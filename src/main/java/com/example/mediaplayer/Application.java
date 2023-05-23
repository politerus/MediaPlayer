package com.example.mediaplayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("simple.fxml"));//подключение к fxml файл
        primaryStage.setTitle("Panda Media Player created the Beskorovayny Roman  ");//Отображение Заголовка программы

        Scene scene =new Scene(root);
        scene.setOnMouseClicked(event -> {
            if(event.getClickCount() ==2);
            primaryStage.setFullScreen(true);
        });


        //задаю размеры сцене
        primaryStage.setScene(scene);
        primaryStage.show();//отображение

    }

    public static void main(String[] args) {
        launch();
    }
}