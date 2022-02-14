package com.example.pembayaran_spp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dataSiswaController extends helpers{

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }

    public void toHalDashboard(MouseEvent event) throws IOException {
        changePageMouse(event, "dashboardAdmin2");
    }

    public void toHalDataSiswa(MouseEvent event) throws IOException {
        changePageMouse(event, "halDataSiswa2");
    }

    public void toHalDataSPP(MouseEvent event) throws IOException {
        changePageMouse(event, "halDataSPP2");
    }

    public void logout(MouseEvent event) throws IOException {
        changePageMouse(event, "login2");
    }

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private void openTambahSiswa(ActionEvent actionevent) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("tambahDataSiswa.fxml"));
        Stage tambahDataSiswa = new Stage();
        tambahDataSiswa.setResizable(false);
        tambahDataSiswa.setTitle("Login");
        tambahDataSiswa.setScene(new Scene(root));
        tambahDataSiswa.centerOnScreen();
        tambahDataSiswa.initOwner(rootAnchorPane.getScene().getWindow());
        tambahDataSiswa.initModality(Modality.APPLICATION_MODAL);

        tambahDataSiswa.show();
    }

    public void insertSiswa() {
        String query = "INSERT INTO siswa VALUES(" +
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
