package ru.penza.builtfurniture;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        stage.setTitle("Авторизация");
       // stage.getIcons().add(new Image("file:src/main/resources/picture/ico.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}