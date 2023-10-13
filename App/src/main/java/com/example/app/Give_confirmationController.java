package com.example.app;

import database.DBaccount;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Give_confirmationController {
    public Text confirmation_msg;
    public Button Goto_Dashboard;
    public String cardnumber_sender;
    public int Intnewamount_sender;
    public String cardnumber_reciver;
    public int Intnewamount_reciver;


    public void initialize() throws SQLException {
        DBaccount dBaccount= new DBaccount();
        dBaccount.Connect();

        cardnumber_sender = DashboardController.cardnumber_give_sender;
        Intnewamount_sender = DashboardController.Intnewamount_give_sender;
        confirmation_msg.setText(dBaccount.updateCardAmount(String.valueOf(Intnewamount_sender), cardnumber_sender));

        cardnumber_reciver = DashboardController.cardnumber_give_reciver;
        Intnewamount_reciver = DashboardController.Intnewamount_give_reciver;
        dBaccount.updateCardAmount(String.valueOf(Intnewamount_reciver), cardnumber_reciver);
    }

    public void Goto_Dashboard() throws IOException {
        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(DashboardStart.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        confirmation_msg.setText("");
        Stage oldstage = (Stage) Goto_Dashboard.getScene().getWindow();
        oldstage.close();
    }
}
