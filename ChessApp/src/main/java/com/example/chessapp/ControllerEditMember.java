package com.example.chessapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEditMember {
    ObservableList<Integer> gradeList = FXCollections.observableArrayList(6,7,8,9,10,11,12);
    ObservableList<String> nameList = FXCollections.observableArrayList(Members.namesList);
    @FXML
    private Button btn_EMback;

    @FXML
    private Button btn_delete;
    @FXML
    private ChoiceBox<Integer> cb_GradeLevelEM;
    @FXML
    private ChoiceBox<String> cb_Members;
    @FXML
    private Label lbl_EditMember;

    @FXML
    private Label lbl_GradeLevelEM;

    @FXML
    private Label lbl_NameEM;

    @FXML
    private Label lbl_RankEM;

    @FXML
    private TextField txt_NameEM;

    @FXML
    private TextField txt_RankEM;

    @FXML
    private Button btn_Done;
    @FXML
    private void initialize(){
        cb_Members.setItems(nameList);
        cb_GradeLevelEM.setItems(gradeList);
    }
    public void navBackEM(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }
    public void editMember(javafx.event.ActionEvent actionEvent) throws IOException {
        Members.edit(cb_Members.getValue(), txt_NameEM.getText(), txt_RankEM.getText(), cb_GradeLevelEM.getValue());
        Members.sortLists();
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }
    public void deleteMember(javafx.event.ActionEvent actionEvent) throws IOException {
        Members.delete(cb_Members.getValue());
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }
}
