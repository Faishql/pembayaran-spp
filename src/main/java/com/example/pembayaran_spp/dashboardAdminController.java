package com.example.pembayaran_spp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class dashboardAdminController extends helpers implements Initializable {

    @FXML
    private Label jumlahKelas;

    @FXML
    private Label jumlahSiswa;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDataKelas(); showDataSiswa();
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

    public void showDataKelas() {

        try {
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(id_kelas) as jumlahKelas FROM spp");
//            ResultSet rs2 = connection.createStatement().executeQuery("SELECT COUNT(id_siswa) as jumlahSiswa FROM siswa");

            rs.next();

            String jk =  rs.getString("jumlahkelas");
//            String js =  rs2.getString("jumlahSiswa");

            jumlahKelas.setText(jk);
//            jumlahSiswa.setText(js);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showDataSiswa() {

        try {
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(id_siswa) as jumlahSiswa FROM siswa");

            rs.next();
            String js =  rs.getString("jumlahSiswa");

            jumlahSiswa.setText(js);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            changePageMouse(event, "login2");
        }
    }
}
