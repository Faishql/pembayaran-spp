package com.example.pembayaran_spp;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class loginController extends helpers implements Initializable {

    public TextField username;
    public TextField password;

    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        try {
            connection = MyJDBC.gConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @FXML
    public void loginButton(ActionEvent event) {
            try {
                var result = connection.createStatement().executeQuery("SELECT * FROM users WHERE username = '" + username.getText() + "'");

                if (result.next()) {
                    if (result.getString("password").equals(password.getText())) {
                        session.username = result.getString("username");

                        if (result.getString("level").equals("admin")) {
                            changePage(event, "dashboardAdmin2");
                        } else {
                            var result2 = connection.createStatement().executeQuery("SELECT * FROM users INNER JOIN siswa ON users.username = siswa.nis WHERE username = '" + username.getText() +"'");
                            result2.next();

                            session.kelas = result2.getString("kelas");

                            changePage(event, "dashboardUser2");
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Username / Password is wrong", ButtonType.YES);

                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }





}
