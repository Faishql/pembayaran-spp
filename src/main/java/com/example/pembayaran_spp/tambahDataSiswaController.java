package com.example.pembayaran_spp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class tambahDataSiswaController extends dataSiswaController implements Initializable {

    @FXML
    private ComboBox dwKelas;

    @FXML
    private Label labelModal;

    @FXML
    private TextField tfNIS;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNama;

    @FXML
    private Button buttonMain;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dwKelas.setItems(List);

        if (session.getModal == "tambah" ) {
            labelModal.setText("Tambah Data Siswa");
            buttonMain.setText("Tambah");
            buttonMain.setOnAction(e->insertSiswa()) ;
        } else if(session.getModal == "update" ) {
            labelModal.setText("Ubah Data Siswa");
            buttonMain.setText("Ubah");
            buttonMain.setOnAction(e->updateSiswa()) ;
        }
    }



    ObservableList<String> List = FXCollections.observableArrayList("X RPL", "X MM", "X ANI");

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

    public void insertSiswa() {
        String lunas = "belum lunas";
        Integer idKosong = null;

        String query = "INSERT INTO siswa VALUES(" + idKosong + ",'" + tfNIS.getText() + "','" + tfNama.getText() + "','" + dwKelas.getValue() + "','"+ lunas +"')";
        executeQuery(query);

    }

    public void updateSiswa() {
        String lunas = "lunas";
        Integer idKosong = null;

        String query = "UPDATE siswa SET nis = '" + tfNIS.getText() + "', nama = '" + tfNama.getText() + "', kelas = '"
                + dwKelas.getValue() + "', status = '" + lunas + "' WHERE nis = " + session.selectedSiswa +"";
        executeQuery(query);

    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement st;


        try {
            st = connection.createStatement();
            int i = st.executeUpdate(query);
            if (i > 0) {

                Stage stage = (Stage) buttonMain.getScene().getWindow();

                stage.close();
                System.out.println("success");

            } else {
                System.out.println("stuck somewhere");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
