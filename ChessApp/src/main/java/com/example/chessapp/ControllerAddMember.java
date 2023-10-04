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
import java.util.*;

import static com.example.chessapp.Members.addRating;

public class ControllerAddMember {

    @FXML
    private Button btn_ADback;

    @FXML
    private Button btn_AddMember;

    @FXML
    private ChoiceBox<Integer> cb_GradeLevel;

    @FXML
    private Label lbl_AddMember;

    @FXML
    private Label lbl_GradeLevel;

    @FXML
    private Label lbl_Name;

    @FXML
    private TextField txt_name;

    public void navBackAM(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("members-list.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Rankings");
    }
    ObservableList<Integer> gradeList = FXCollections.observableArrayList(6, 7, 8, 9, 10, 11, 12);
    @FXML
    private void initialize() {
        cb_GradeLevel.setItems(gradeList);
    }
    public void createMember(javafx.event.ActionEvent actionEvent) throws IOException {
        if (!txt_name.getText().equals("") && !cb_GradeLevel.getValue().equals(null)) {
            String memberName = txt_name.getText();
            Integer memberGrade = cb_GradeLevel.getValue();
            Members.namesList.add(memberName);
            Members.gradeList.add(memberGrade);
            addRating(100);
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
