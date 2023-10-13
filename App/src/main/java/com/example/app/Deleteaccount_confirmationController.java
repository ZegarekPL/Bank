package com.example.app;

import database.DBaccount;
import database.DBuser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Deleteaccount_confirmationController extends LoginController {
    public Text confirmation_msg;
    public Button Goto_Dashboard;

    public void initialize() throws SQLException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBaccount dBaccount = new DBaccount();
        dBaccount.Connect();

        String id = dBuser.userData(user, "id");

        dBaccount.deactiveaccount(id);
        confirmation_msg.setText(dBuser.deactiveuser(id));
    }

    public void Goto_Dashboard() throws IOException, SQLException {
        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(DashboardStart.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) Goto_Dashboard.getScene().getWindow();
        oldstage.close();
    }
}
