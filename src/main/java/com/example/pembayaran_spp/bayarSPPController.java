package com.example.pembayaran_spp;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class bayarSPPController extends helpers implements Initializable{

    @FXML
    private TableView<Bayar> tableBayarSPP;

    @FXML
    private TableColumn<SPP, String> no;

    @FXML
    private TableColumn<Bayar, String> kelas;

    @FXML
    private TableColumn<Bayar, String> nominal;

    @FXML
    private TableColumn<Bayar, String> status;

    @FXML
    private TableColumn<Bayar, String> tanggal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showBayar();
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

    public ObservableList<Bayar> getBayarList(){
        ObservableList<Bayar> bayarList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM bayar WHERE bayar.nis = '" + session.username + "'";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Bayar bayar;

            while (rs.next()) {
                bayar = new Bayar( rs.getString("kelas"), rs.getString("nominal"),  rs.getString("tanggal"), rs.getString("status"));
                bayarList.add(bayar);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  bayarList;
    }

    public void showBayar() {
        ObservableList<Bayar> list = getBayarList();

        no.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SPP, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SPP, String> p) {
                return new ReadOnlyObjectWrapper(tableBayarSPP.getItems().indexOf(p.getValue()) + 1 +  "");
            }
        });
        kelas.setCellValueFactory(new PropertyValueFactory<Bayar, String>("kelas"));
        nominal.setCellValueFactory(new PropertyValueFactory<Bayar, String>("nominal"));
        tanggal.setCellValueFactory(new PropertyValueFactory<Bayar, String>("tanggal"));
        status.setCellValueFactory(new PropertyValueFactory<Bayar, String>("status"));


//        addButtonToTable();

        tableBayarSPP.setItems(list);
    }

    private void addButtonToTable() {
        TableColumn aksi = new TableColumn("aksi");
        aksi.setStyle("-fx-alignment: CENTER;");

        Callback<TableColumn<Bayar, Void>, TableCell<Bayar, Void>> cellFactory = new Callback<TableColumn<Bayar, Void>, TableCell<Bayar, Void>>() {
            @Override
            public TableCell call(final TableColumn param) {
                final TableCell cell = new TableCell() {

                    private final Button btnBayar = new Button("Bayar");

                    {
                        btnBayar.setStyle("-fx-background-color: #01937C; -fx-text-fill: #fff;");
                        btnBayar.setOnMouseEntered(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                btnBayar.setCursor(Cursor.HAND); //Change cursor to hand
                            }
                        });
                        btnBayar.setOnAction((ActionEvent event) -> {

                            Bayar selectedRow = tableBayarSPP.getSelectionModel().getSelectedItem();
                            if (!(selectedRow == null)) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bayar ?", ButtonType.YES, ButtonType.CANCEL);
                                alert.setTitle("Konformasi Bayar");
                                alert.showAndWait();

                                if (alert.getResult() == ButtonType.YES) {
                                    String query = "UPDATE bayar SET status = '" + "Lunas" + "' WHERE nis = '" + session.username +"' AND tanggal = '" + selectedRow.getTanggal() + "'";
                                    executeQuery(query);
                                }
                            } else {
                                Alert warning = new Alert(Alert.AlertType.WARNING, "Pilih baris data siswa terlebih dahulu", ButtonType.YES, ButtonType.CANCEL);
                                warning.showAndWait();
                            }
                        });
                    }
//                    private final Button btnCetak = new Button("Bayar");
//
//                    {
//
//                    }

                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hbox = new HBox(btnBayar);
                            setGraphic(hbox);
                        }
                    }

                };
                return cell;
            }
        };

        aksi.setCellFactory(cellFactory);

        tableBayarSPP.getColumns().add(aksi);

    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement st;


        try {
            st = connection.createStatement();

            int i = st.executeUpdate(query);
            if (i > 0) {

//                Stage stage = (Stage) insertBtn.getScene().getWindow();
//
//                stage.close();
                System.out.println("success");

            } else {
                System.out.println("stuck somewhere");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshTable(ActionEvent event) {
        tableBayarSPP.setItems(getBayarList());
        tableBayarSPP.refresh();
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




