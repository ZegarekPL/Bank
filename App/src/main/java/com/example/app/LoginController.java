package com.example.app;

import database.*;
import com.example.app.objects.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    public TextField login_input;
    public TextField password_input;
    public Button login_button;

    public static User user;
    public Text msg_output_log;

    public void Login_Button() throws IOException, SQLException {
        user=new User();
        user.setUser_login(login_input.getText());
        user.setUser_password(password_input.getText());
        DBuser connection=new DBuser();
        connection.Connect();
        if(connection.authenticate(user)){
            FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Dashboard.fxml"));
            Scene scene = new Scene(DashboardStart.load(), 1200, 800);
            Stage stage = new Stage();
            stage.setTitle("App!");
            stage.setScene(scene);
            stage.show();
            Stage oldstage = (Stage) login_button.getScene().getWindow();
            oldstage.close();
        }
        if(connection.isloginexist(user)){
            msg_output_log.setText("Dane są niepoprawnie wpisane");

        }
        else{
            msg_output_log.setText("Nie znaleziono użytkownika");
        }
    }
    public void Register_Button() throws IOException {
        FXMLLoader RegisterStart = new FXMLLoader(App.class.getResource("Register.fxml"));
        Scene scene = new Scene(RegisterStart.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) password_input.getScene().getWindow();
        oldstage.close();
    }
}