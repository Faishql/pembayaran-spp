package com.example.pembayaran_spp;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class bayarSPPController extends helpers implements Initializable{

    @FXML
    private TableView<Siswa> tableBayarSPP;

    @FXML
    private TableColumn<Siswa, Integer> no;

    @FXML
    private TableColumn<Siswa, String> nama;

    @FXML
    private TableColumn<Siswa, String> kelas;

    @FXML
    private TableColumn<Siswa, String> nis;

    @FXML
    private TableColumn<Siswa, String> status;

    @FXML
    private TableColumn<?, ?> aksi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showSiswa();
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

    public ObservableList<Siswa> getSiswaList(){
        ObservableList<Siswa> siswaList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM siswa";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Siswa siswa;

            while (rs.next()) {
                siswa = new Siswa(rs.getInt("id_siswa"), rs.getString("nis"),
                        rs.getString("nama"), rs.getString("kelas"), rs.getString("status"));
                siswaList.add(siswa);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  siswaList;
    }

    public void showSiswa() {
        ObservableList<Siswa> list = getSiswaList();

        no.setCellValueFactory(new PropertyValueFactory<Siswa, Integer>("no"));
        nis.setCellValueFactory(new PropertyValueFactory<Siswa, String>("nis"));
        nama.setCellValueFactory(new PropertyValueFactory<Siswa, String>("nama"));
        kelas.setCellValueFactory(new PropertyValueFactory<Siswa, String>("kelas"));
        status.setCellValueFactory(new PropertyValueFactory<Siswa, String>("status"));

//        addButtonToTable();

        tableBayarSPP.setItems(list);
    }

//    private void addButtonToTable() {
//        TableColumn<Siswa, Void> aksi = new TableColumn("Aksi");
//
//        Callback<TableColumn<Siswa, Void>, TableCell<Siswa, Void>> cellFactory = new Callback<TableColumn<Siswa, Void>, TableCell<Siswa, Void>>() {
//            @Override
//            public TableCell<Siswa, Void> call(final TableColumn<Siswa, Void> param) {
//                final TableCell<Siswa, Void> cell = new TableCell<Siswa, Void>() {
//
//                    private final Button btn = new Button("Action");
//
//                    {
//                        btn.setOnAction((ActionEvent event) -> {
//                            Siswa Siswa = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedSiswa: " + Siswa);
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//    }

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




