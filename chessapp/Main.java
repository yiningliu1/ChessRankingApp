package com.example.chessapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("members-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rankings");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
            Members.readNames();
            Members.readGrades();
            Members.readRatings();
            Members.readGames();
            launch();
            Members.saveNames();
            Members.saveGrades();
            Members.saveRatings();
            Members.saveGames();
        }
    }
