package com.example.pembayaran_spp;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardUserController extends helpers implements Initializable  {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toHalDashboard(MouseEvent event) throws IOException {
        changePageMouse(event, "dashboardUser2");
    }

    public void toHalBayarSPP(MouseEvent event) throws IOException {
        changePageMouse(event, "halBayarSPP2");
    }

    public void toHalDataDiriSiswa(MouseEvent event) throws IOException {
        changePageMouse(event, "halDataDiriSiswa2");
    }

    public void logout(MouseEvent event) throws IOException {
        changePageMouse(event, "login2");
    }
}
