package com.example.pembayaran_spp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

public class dataSiswaController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void back(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();
           
    }

    @FXML
    private TableView<siswa> tableSiswa;

    @FXML
    private TableColumn<siswa, Integer> no;

    @FXML
    private TableColumn<siswa, String> nama;

    @FXML
    private TableColumn<siswa, String> kelas;

    @FXML
    private TableColumn<siswa, String> nis;

    @FXML
    private TableColumn<siswa, String> status;

    ObservableList<siswa> List = FXCollections.observableArrayList(
        new siswa(1, "Faishal", "XI", "1023asas", "lunas"),
        new siswa(1, "Faishal", "XI", "1023adadas", "lunas")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {
        no.setCellValueFactory(new PropertyValueFactory<siswa, Integer>("no"));
        nama.setCellValueFactory(new PropertyValueFactory<siswa, String>("nama"));
        kelas.setCellValueFactory(new PropertyValueFactory<siswa, String>("kelas"));
        nis.setCellValueFactory(new PropertyValueFactory<siswa, String>("nis"));
        status.setCellValueFactory(new PropertyValueFactory<siswa, String>("status"));

        tableSiswa.setItems(List);
        
    }

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private void openTambahSiswa(ActionEvent actionevent) throws IOException {
        Parent root = FXMLLoader.load(index.class.getResource("tambahDataSiswa.fxml"));
        Stage tambahDataSiswa = new Stage();
        tambahDataSiswa.setResizable(false);
        tambahDataSiswa.setTitle("Login");
        tambahDataSiswa.setScene(new Scene(root));
        tambahDataSiswa.centerOnScreen();
        tambahDataSiswa.initOwner(rootAnchorPane.getScene().getWindow());
        tambahDataSiswa.initModality(Modality.APPLICATION_MODAL);
        
        tambahDataSiswa.show();
    }

    @FXML
    void tohaldashboardAdminClick(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
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

    @FXML
    void logout(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.show();
           
    }
    

}
