package com.example.chessapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.example.chessapp.Members.addRating;

public class ControllerAddGame {

    @FXML
    private javafx.scene.layout.AnchorPane AnchorPane;
    @FXML
    private Button btn_AddGame;

    @FXML
    private Button btn_back;

    @FXML
    private ChoiceBox<String> cb_loser;

    @FXML
    private ChoiceBox<String> cb_winner;

    @FXML
    private Label lbl_AddGame;

    @FXML
    private Label lbl_loser;

    @FXML
    private CheckBox draw;

    @FXML
    private Label lbl_winner;

    public void navBackAG(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }

    ObservableList<String> nameList = FXCollections.observableArrayList(Members.namesList);

    @FXML
    private void initialize() {
        cb_winner.setItems(nameList);
        cb_loser.setItems(nameList);
    }

    public void addGame(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Boolean isDraw = draw.isSelected();
        if (!(cb_winner.getValue().equals(cb_loser.getValue())) && !(cb_winner.getValue().equals("")
                || cb_loser.getValue().equals(""))){
            int winIndex = Members.namesList.indexOf(cb_winner.getValue());
            int loseIndex = Members.namesList.indexOf(cb_loser.getValue());
            int winRating = Members.getRating(winIndex);
            int loseRating = Members.getRating(loseIndex);
            if(isDraw){
                Game tempGame = new Game(cb_winner.getValue() + "|" + cb_loser.getValue(),
                        cb_loser.getValue() + "|" + cb_winner.getValue());
                Members.gamesList.add(tempGame);
                Members.updateRating(winIndex, loseRating);
                Members.updateRating(loseIndex, winRating);
            }
            else {
                Game tempGame = new Game(cb_winner.getValue(), cb_loser.getValue());
                Members.gamesList.add(tempGame);
                Members.updateRating(winIndex, true, loseRating);
                Members.updateRating(loseIndex, false, winRating);
            }
            Members.sortLists();
            Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Rankings");
        }
    }
}
