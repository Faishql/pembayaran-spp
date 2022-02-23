package com.example.pembayaran_spp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class tambahDataSiswaController extends dataSiswaController implements Initializable {

    @FXML
    private ComboBox dwKelas;

    @FXML
    private ComboBox dwStatus;

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

        dwKelas.setItems(getKelasList());
        dwStatus.setItems(statusOption);

        if (session.getModal == "tambah" ) {
            labelModal.setText("Tambah Data Siswa");
            buttonMain.setText("Tambah");
            buttonMain.setOnAction(e->insertSiswa()) ;
        } else if(session.getModal == "update" ) {
            labelModal.setText("Ubah Data Siswa");
            buttonMain.setText("Ubah");

            tfNama.setText(session.selectedNamaSiswa);
            dwKelas.setValue(session.selectedKelasSiswa);
            tfNIS.setText(session.selectedNisSiswa);
            dwStatus.setValue(session.selectedStatusSiswa);

            buttonMain.setOnAction(e->updateSiswa()) ;
        }
    }



    ObservableList<String> statusOption = FXCollections.observableArrayList("Lunas", "Belum Lunas");

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

    public ObservableList getKelasList(){
        ObservableList sppList = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query = "SELECT * FROM spp";
        Statement st;
        ResultSet rs;

        try {

            st = connection.createStatement();
            rs = st.executeQuery(query);
            String spp;

            while (rs.next()) {
                spp = rs.getString("kelas");
                sppList.add(spp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sppList;
    }

    public void insertSiswa() {
        Integer idKosong = null;

        if (tfNIS.getText() == null || tfNIS.getText().trim().isEmpty() || tfNama.getText() == null || tfNama.getText().trim().isEmpty() || dwKelas.getValue() == null || dwStatus.getValue() == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Isi semua data", ButtonType.YES);
            warning.showAndWait();
        } else {
            String query = "INSERT INTO siswa VALUES(" + idKosong + ",'" + tfNIS.getText() + "','" + tfNama.getText() + "','" + dwKelas.getValue() + "','"+ dwStatus.getValue() +"')";
            executeQuery(query);
        }

    }



    public void updateSiswa() {
        Integer idKosong = null;

        if (tfNIS.getText() == null || tfNIS.getText().trim().isEmpty() || tfNama.getText() == null || tfNama.getText().trim().isEmpty() || dwKelas.getValue() == null || dwStatus.getValue() == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Isi semua data", ButtonType.YES);
            warning.showAndWait();
        } else {
            String query = "UPDATE siswa SET nis = '" + tfNIS.getText() + "', nama = '" + tfNama.getText() + "', kelas = '"
                    + dwKelas.getValue() + "', status = '" + dwStatus.getValue() + "' WHERE nis = " + session.selectedNisSiswa +"";
            executeQuery(query);
        }



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
