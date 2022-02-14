package com.example.pembayaran_spp;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardAdminController extends helpers implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
}
