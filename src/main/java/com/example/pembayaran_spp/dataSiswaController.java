package com.example.pembayaran_spp;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class dataSiswaController extends helpers  implements Initializable {

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }

    @FXML
    public TableView<Siswa> tableSiswa;

    @FXML
    private TableColumn<SPP, String> no;

    @FXML
    private TableColumn<Siswa, String> nama;

    @FXML
    private TableColumn<Siswa, String> kelas;

    @FXML
    private TableColumn<Siswa, String> nis;

    @FXML
    private TableColumn<Siswa, String> status;

    @FXML
    public Button openTambah;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == openTambah ) {
            session.getModal = "tambah";
            openTambahSiswa();
        } else {
            session.getModal = "update";
            openTambahSiswa();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showSiswa();
        addButtonToTable();
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
                        rs.getString("nama"), rs.getString("kelas"), "Belum Lunas");
                siswaList.add(siswa);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  siswaList;
    }



    public void showSiswa() {
        ObservableList<Siswa> list = getSiswaList();

        no.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SPP, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SPP, String> p) {
                return new ReadOnlyObjectWrapper(tableSiswa.getItems().indexOf(p.getValue()) + 1 +  "");
            }
        });
        nis.setCellValueFactory(new PropertyValueFactory<Siswa, String>("nis"));
        nama.setCellValueFactory(new PropertyValueFactory<Siswa, String>("nama"));
        kelas.setCellValueFactory(new PropertyValueFactory<Siswa, String>("kelas"));
        status.setCellValueFactory(new PropertyValueFactory<Siswa, String>("status"));



        tableSiswa.setItems(list);


    }


    void executeQuery(String query) {
        Connection connection = getConnection();
        Statement st;


        try {
            st = connection.createStatement();

            int i = st.executeUpdate(query);
            if (i > 0) {

                System.out.println("success");

            } else {
                System.out.println("stuck somewhere");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addButtonToTable() {
        TableColumn aksi = new TableColumn("Aksi");
        aksi.setPrefWidth(186);
        aksi.setStyle("-fx-selection-bar: #c4c4c4; -fx-alignment: CENTER;");

        Callback<TableColumn<Siswa, Void>, TableCell<Siswa, Void>> cellFactory = new Callback<TableColumn<Siswa, Void>, TableCell<Siswa, Void>>() {
            @Override
            public TableCell call(final TableColumn param) {
                final TableCell cell = new TableCell() {

                    private final Button btnUpdate = new Button("Update");

                    {
                        btnUpdate.setStyle("-fx-background-color: #FFC900; -fx-text-fill: #fff;");
                        btnUpdate.setOnMouseEntered(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                btnUpdate.setCursor(Cursor.HAND); //Change cursor to hand
                            }
                        });
                        btnUpdate.setOnAction(e-> {
                            Siswa selectedId = tableSiswa.getSelectionModel().getSelectedItem();
                            if (!(selectedId == null)) {
                                try {
                                    session.selectedNisSiswa = tableSiswa.getSelectionModel().getSelectedItem().getNis();
                                    session.selectedNamaSiswa = tableSiswa.getSelectionModel().getSelectedItem().getNama();
                                    session.selectedKelasSiswa = tableSiswa.getSelectionModel().getSelectedItem().getKelas();
                                    session.selectedStatusSiswa = tableSiswa.getSelectionModel().getSelectedItem().getStatus();

                                    handleButtonAction(e);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                Alert warning = new Alert(Alert.AlertType.WARNING, "Pilih baris data siswa terlebih dahulu", ButtonType.YES, ButtonType.CANCEL);
                                warning.showAndWait();
                            }

                        });


                    }


                    private final Button btnDelete = new Button("Delete");

                    {
                        btnDelete.setStyle("-fx-background-color: #DA1212; -fx-text-fill: #fff;");

                        btnDelete.setOnMouseEntered(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                btnDelete.setCursor(Cursor.HAND); //Change cursor to hand
                            }
                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete ?", ButtonType.YES, ButtonType.CANCEL);
                            alert.setTitle("Konformasi Hapus");
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                Siswa selectedId = tableSiswa.getSelectionModel().getSelectedItem();
                                if (!(selectedId == null)) {
                                    String query = " DELETE FROM siswa WHERE id_siswa = " + selectedId.getNo() + "";
                                    executeQuery(query);

                                    deleteBayar();
                                    deleteUser();
                                } else {
                                    Alert warning = new Alert(Alert.AlertType.WARNING, "Pilih baris data siswa terlebih dahulu", ButtonType.YES, ButtonType.CANCEL);
                                    warning.showAndWait();
                                }

                            }

                        });
                    }

                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hbox = new HBox( btnUpdate , btnDelete);
                            hbox.setSpacing(10);
                            hbox.setStyle("-fx-alignment: CENTER;");
                            setGraphic(hbox);

                        }
                    }

                };
                return cell;
            }
        };

        aksi.setCellFactory(cellFactory);

        tableSiswa.getColumns().add(aksi);

    }

    public void deleteBayar() {

        try {
            String nisSelected = tableSiswa.getSelectionModel().getSelectedItem().getNis();
            String query2 = "DELETE FROM bayar WHERE nis = '" + nisSelected + "' ";
            executeQuery(query2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteUser() {

        try {
            String nisSelected = tableSiswa.getSelectionModel().getSelectedItem().getNis();
            String query2 = "DELETE FROM users WHERE username = '" + nisSelected + "' ";
            executeQuery(query2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void refreshTable(ActionEvent event) {
        tableSiswa.setItems(getSiswaList());
        tableSiswa.refresh();
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
        alert.setTitle("Konformasi Hapus");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            changePageMouse(event, "login2");
        }

    }

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private void openTambahSiswa() throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("tambahDataSiswa2.fxml"));
        Stage tambahDataSiswa = new Stage();
        tambahDataSiswa.setResizable(false);
        tambahDataSiswa.setScene(new Scene(root));
        tambahDataSiswa.centerOnScreen();
        tambahDataSiswa.initOwner(rootAnchorPane.getScene().getWindow());
        tambahDataSiswa.initModality(Modality.APPLICATION_MODAL);

        tambahDataSiswa.show();
    }


}

