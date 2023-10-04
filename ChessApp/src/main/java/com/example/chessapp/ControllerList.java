package com.example.chessapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerList implements Initializable {

    @FXML
    private Button btn_ViewGames;

    @FXML
    private Button btn_AddGame;
    @FXML
    private Button btn_EditMember;
    @FXML
    private Button btn_AddMemberL;

    @FXML
    private TableView<Member> tbl_Rank;

    @FXML
    private TableColumn<Member, String> col_Name;

    @FXML
    private TableColumn<Member, Integer> col_grade;

    @FXML
    private TableColumn<Member, Integer> col_rank;

    @FXML
    private TextField txt_Search;

    public void navAddGame(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-game.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Add Game");
    }
    public void navViewGames(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view-games.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("View Games");
    }
    public void navAddMember(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-member.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Add Member");
    }
    public void navEditMember(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("edit-member.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Edit Member");
    }
    ObservableList<Member> memberList = FXCollections.observableArrayList(Members.createMembers());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_Name.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        col_rank.setCellValueFactory(new PropertyValueFactory<Member, Integer>("rank"));
        col_grade.setCellValueFactory(new PropertyValueFactory<Member, Integer>("grade"));
        tbl_Rank.setItems(memberList);
    }

    public void filterList() throws IOException {
        String filter = txt_Search.getText();
        ObservableList<Member> filteredList = FXCollections.observableArrayList();
        for(int i=0; i < memberList.size(); i++){
            if(memberList.get(i).getName().toLowerCase().contains(filter.toLowerCase())){
                filteredList.add(memberList.get(i));
            }
        }
        tbl_Rank.setItems(filteredList);
    }
}
