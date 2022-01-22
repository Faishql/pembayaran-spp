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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class dataSiswaController implements Initializable{

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
    

}
