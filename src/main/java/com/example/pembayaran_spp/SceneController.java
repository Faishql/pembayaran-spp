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
        Parent root = FXMLLoader.load(getClass().getResource("dashboardUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
    public void tohaldashboardAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void tohaldashboardUserClick(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("dashboardUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalBayarSPP(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halBayarSPP.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }
    
    @FXML
    void tohalDataDiriSiswa(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataDiriSiswa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }
    
    @FXML
    void tohaldashboardAdminClick(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalDataSiswa(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataSiswa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    void tohalDataSPP(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("halDataSPP.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
           
    }
 
}