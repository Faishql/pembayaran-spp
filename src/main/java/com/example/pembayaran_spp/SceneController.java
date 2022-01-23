package com.example.pembayaran_spp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
 public void switchToScene1(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("login.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root, 900, 600);
  stage.setScene(scene);
  stage.show();
 }
 
 public void switchToScene2(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("halDataSiswa.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root, 1000, 600);
  stage.setScene(scene);
  stage.show();
 }
}