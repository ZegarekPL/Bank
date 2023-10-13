package com.example.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Deleteaccount_SiteController {

    public Button Goto_Dashboard;
    public Button Goto_Dashboard_delete;


    public void Goto_Dashboard() throws IOException {
        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(DashboardStart.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) Goto_Dashboard.getScene().getWindow();
        oldstage.close();
    }

    public void Goto_Dashboard_delete( ) throws IOException {
        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Deleteaccount_confirmation.fxml"));
        Scene scene = new Scene(DashboardStart.load(), 300, 200);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) Goto_Dashboard_delete.getScene().getWindow();
        oldstage.close();
    }
}
