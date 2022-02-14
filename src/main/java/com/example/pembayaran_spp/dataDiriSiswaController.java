package com.example.pembayaran_spp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dataDiriSiswaController extends helpers implements Initializable {

    @FXML
    private Label kelasSiswa;

    @FXML
    private Label namaSiswa;

    @FXML
    private Label nisSiswa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDataDiri();
    }

    public Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pembayaran_spp", "root", "");
            return connection;
        } catch(Exception ex) {
            System.out.println("Error " + ex.getMessage());
            return null;
        }
    }

   public void showDataDiri() {

        try {
            Connection connection = getConnection();
            var res = connection.createStatement().executeQuery("SELECT * FROM siswa WHERE nis = '"+session.username+"'");
            res.next();
            namaSiswa.setText(res.getString("nama"));
            kelasSiswa.setText(res.getString("kelas"));
            nisSiswa.setText(res.getString("nis"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

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
