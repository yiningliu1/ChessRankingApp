package com.example.chessapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGamesView implements Initializable {
    @FXML
    private Button btn_Gback;

    @FXML
    private ChoiceBox<String> cb_MembersVG;

    @FXML
    private Label lbl_MNGames;

    @FXML
    private TableColumn<Game, String> col_Loser;

    @FXML
    private TableColumn<Game, String> col_Winner;
    @FXML
    private TableView<Game> tbl_Games;
    public void navBackGV(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }
    ObservableList<String> nameList = FXCollections.observableArrayList(Members.namesList);
    public void gamesSort() throws IOException {
        String name = cb_MembersVG.getValue();
        ArrayList<Game> Games = new ArrayList<>();
        for (int i = 0; i <= Members.gamesList.size() - 1; i++) {
            if (Members.gamesList.get(i).getWinner().equals(name) ||
                    Members.gamesList.get(i).getLoser().equals(name)) {
                Games.add(Members.gamesList.get(i));
            }
            if (Members.gamesList.get(i).getWinner().contains(name + "|") ||
                    Members.gamesList.get(i).getWinner().contains("|" + name)){
                Games.add(Members.gamesList.get(i));
            }
            ObservableList<Game> gamesList = FXCollections.observableArrayList(Games);
            tbl_Games.setItems(gamesList);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_MembersVG.setItems(nameList);
        col_Winner.setCellValueFactory(new PropertyValueFactory<Game, String>("winner"));
        col_Loser.setCellValueFactory(new PropertyValueFactory<Game, String>("loser"));
    }
}
