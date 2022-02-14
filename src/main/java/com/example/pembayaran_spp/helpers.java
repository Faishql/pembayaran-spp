package com.example.pembayaran_spp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class helpers {

    public static void changePage(ActionEvent Event, String page) {

        Node node = (Node)Event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource( page + ".fxml")));
            stage.setScene(new Scene(root, 1366, 768));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changePageMouse(MouseEvent Event, String page)  throws IOException {

        Node node = (Node)Event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource( page + ".fxml")));
            stage.setScene(new Scene(root, 1366, 768));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public abstract void initialize(URL location, ResourceBundle resources);
}
