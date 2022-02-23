package com.example.pembayaran_spp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class tambahDataSPPController implements Initializable {

    @FXML
    private Button buttonMain;

    @FXML
    private TextField kelas;

    @FXML
    private TextField nominal;

    @FXML
    private TextField tfId;

    @FXML
    private DatePicker tanggal;

    @FXML
    private Label labelModal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (session.getModal == "tambah" ) {
            labelModal.setText("Tambah Data SPP");
            buttonMain.setText("Tambah");
            buttonMain.setOnAction(e->insertSPP()) ;
        } else if(session.getModal == "update" ) {
            labelModal.setText("Ubah Data SPP");
            buttonMain.setText("Ubah");

            kelas.setText(session.selectedKelas);
            nominal.setText(String.valueOf(session.selectedNominal));
//            tanggal.setValue(LocalDate.parse(session.selectedTanggal));

            buttonMain.setOnAction(e->updateSPP()) ;
        }
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

    public void insertSPP() {
        Integer idKosong = null;
        String year = String.valueOf(tanggal.getValue().getYear());
        String month = String.valueOf(tanggal.getValue().getMonth());
        String tanggalAll = month + ' ' + ' ' + year;

        String query = "INSERT INTO spp VALUES(" + idKosong + ",'" + kelas.getText() + "'," + nominal.getText() + ",'" + tanggalAll  + "')";
        executeQuery(query);

    }

    public void updateSPP(){
        Integer idUpdate = 21;
        String year = String.valueOf(tanggal.getValue().getYear());
        String month = String.valueOf(tanggal.getValue().getMonth());
        String tanggalAll = month + ' ' + ' ' + year;

        String query = "UPDATE spp SET kelas = '" + kelas.getText() + "', nominal_spp = " + nominal.getText() + ", tanggal = '"
                + tanggalAll + "' WHERE id_kelas = " + idUpdate +"";
        executeQuery(query);

    }

//    public void updateSiswa(ActionEvent event) {
//        String lunas = "lunas";
//        Integer idKosong = null;
//        Integer idUpdate = 1;
//
//        String query = "UPDATE siswa SET nis = '" + tfNIS.getText() + "', nama = '" + tfNama.getText() + "', kelas = '"
//                + dwKelas.getValue() + "', status = '" + lunas + "' WHERE id_siswa = " + idUpdate +"";
//        executeQuery(query);
//
//    }
//
//    public void deleteSiswa(ActionEvent event){
//
//        Integer idDelete = 9;
//        String query = " DELETE FROM siswa WHERE id = " + idDelete + "";
//        executeQuery(query);
//
//    }
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
