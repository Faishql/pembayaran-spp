package com.example.pembayaran_spp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneController {

 private Stage stage;
 private Scene scene;
 private Parent root;
 
    public void tohaldashboardUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboardUser2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }
    public void tohaldashboardAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void tohaldashboardUserClick(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("dashboardUser2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalBayarSPP(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halBayarSPP2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }
    
    @FXML
    void tohalDataDiriSiswa(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataDiriSiswa2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }
    
    @FXML
    void tohaldashboardAdminClick(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalDataSiswa(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataSiswa2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalDataSPP(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataSPP2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }
 
}