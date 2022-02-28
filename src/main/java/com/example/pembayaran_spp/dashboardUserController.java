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

public class dashboardUserController extends helpers implements Initializable  {

    @FXML
    private Label labelTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTotal();
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

    public void showTotal() {
        try {
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT SUM(nominal) as total FROM bayar WHERE nis = '" + session.username +"' AND status = '" + "Belum Lunas" + "'");

            rs.next();
            String total = rs.getString("total");

            if ( total == null ) {
                labelTotal.setText("Lunas");
            } else {
                labelTotal.setText("Rp. " + total);
            }


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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            changePageMouse(event, "login2");
        }
    }
}
