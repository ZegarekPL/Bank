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

public class Ante_confirmationController {
    public Text confirmation_msg;
    public Button Goto_Dashboard;
    public String cardnumber;
    public int Intnewamount;


    public void initialize() throws SQLException {
        DBaccount dBaccount= new DBaccount();
        dBaccount.Connect();

        cardnumber = DashboardController.cardnumber_ante_reciver;
        Intnewamount = DashboardController.Intnewamount;
        confirmation_msg.setText(dBaccount.updateCardAmount(String.valueOf(Intnewamount), cardnumber));
    }

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
}
