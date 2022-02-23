package com.example.pembayaran_spp;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class dataSPPController extends helpers implements Initializable {

        @FXML
        private Label h1;

        @FXML
        private TableView<SPP> tableSPP;

        @FXML
        private TableColumn<SPP, String> no;

        @FXML
        private TableColumn<SPP, String> kelas;

        @FXML
        private TableColumn<SPP, Integer> nominal;

        @FXML
        private TableColumn<SPP, String> tanggal;

        @FXML
        private Button openTambahSPP;

        @FXML
        private ComboBox dwSelectKelas;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == openTambahSPP ) {
            session.getModal = "tambah";
            openTambahSPP();
        } else {
            session.getModal = "update";


            openTambahSPP();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dwSelectKelas.setItems(getKelasList());
        showSPP();
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

            sppList.add("Show All");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sppList;
    }



    public ObservableList<SPP> getSPPList(){
        ObservableList<SPP> sppList = FXCollections.observableArrayList();
        Connection connection = getConnection();

        String query;
        String getStringDW = (String) dwSelectKelas.getValue();

            if ( getStringDW == null || getStringDW.trim().isEmpty() || getStringDW == "Show All" ) {
                query = "SELECT * FROM spp";
            } else {
                query = "SELECT * FROM spp WHERE kelas = '"+ getStringDW + "' ";
            }

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            SPP spp;

            while (rs.next()) {
                spp = new SPP(rs.getInt("id_kelas"), rs.getString("kelas"), rs.getInt("nominal_spp"),rs.getString("tanggal"));
                sppList.add(spp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  sppList;
    }

    public void showSPP() {

        ObservableList<SPP> list = getSPPList();

        no.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SPP, String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<SPP, String> p) {
                return new ReadOnlyObjectWrapper(tableSPP.getItems().indexOf(p.getValue()) + 1 +  "");
            }
        });
        kelas.setCellValueFactory(new PropertyValueFactory<SPP, String>("kelas"));
        nominal.setCellValueFactory(new PropertyValueFactory<SPP, Integer>("nominal"));
        tanggal.setCellValueFactory(new PropertyValueFactory<SPP, String>("tanggal"));

        tableSPP.setItems(list);

    }

    private void addButtonToTable() {
        TableColumn aksi = new TableColumn("Aksi");
        aksi.setPrefWidth(226);
        aksi.setStyle("-fx-selection-bar: #c4c4c4; -fx-alignment: CENTER;");

        Callback<TableColumn<SPP, Void>, TableCell<SPP, Void>> cellFactory = new Callback<TableColumn<SPP, Void>, TableCell<SPP, Void>>() {
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
                            try {
                                session.selectedKelas = tableSPP.getSelectionModel().getSelectedItem().getKelas();
                                session.selectedNominal = tableSPP.getSelectionModel().getSelectedItem().getNominal();
                                session.selectedTanggal = tableSPP.getSelectionModel().getSelectedItem().getTanggal();


                                handleButtonAction(e);
                            } catch (IOException ex) {
                                ex.printStackTrace();
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

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete?", ButtonType.YES, ButtonType.CANCEL);
                            alert.setTitle("Konformasi Hapus");
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                Integer idDelete = 21;
                                String query = " DELETE FROM SPP WHERE id_kelas = " + idDelete + "";
                                executeQuery(query);
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

        tableSPP.getColumns().add(aksi);

    }

    public void refreshTable(ActionEvent event) {
        tableSPP.setItems(getSPPList());
        tableSPP.refresh();

        dwSelectKelas.setItems(getKelasList());
    }

    public void deleteSPP(ActionEvent event){

        Integer idDelete = 9;
        String query = " DELETE FROM SPP WHERE id_SPP = " + idDelete + "";
        executeQuery(query);

    }

    private void executeQuery(String query) {
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

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private void openTambahSPP() throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("tambahDataSPP2.fxml"));
        Stage tambahDataSPP = new Stage();
        tambahDataSPP.setResizable(false);
        tambahDataSPP.setScene(new Scene(root));
        tambahDataSPP.centerOnScreen();
        tambahDataSPP.initOwner(rootAnchorPane.getScene().getWindow());
        tambahDataSPP.initModality(Modality.APPLICATION_MODAL);

        tambahDataSPP.show();
    }
}
