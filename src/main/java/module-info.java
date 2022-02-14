module com.example.pembayaran_spp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pembayaran_spp to javafx.fxml;
    exports com.example.pembayaran_spp;
}