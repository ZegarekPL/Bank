package com.example.app;

import database.DBuser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Password_change_confirmationController {
    public Text confirmation_msg;
    public Button Goto_Dashboard;
    public String username1;
    public String newpassword1;


    public void initialize() throws SQLException {
        DBuser dBuser= new DBuser();
        dBuser.Connect();

        username1 = Change_passwordSiteController.username;
        newpassword1 = Change_passwordSiteController.newpassword;

        confirmation_msg.setText(dBuser.update_user_password(username1, newpassword1));
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
