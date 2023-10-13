package com.example.app;

import database.DBuser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Change_passwordSiteController extends LoginController {
    public Button Sure_changepassword;
    public Button Goto_Dashboard;
    public PasswordField old_password_input;
    public PasswordField new_password_input1;
    public PasswordField new_password_input2;
    public Text msg_output;
    public static String username;
    public static String newpassword;

    public void Sure_changepassword() throws SQLException, IOException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();

        String password = dBuser.userData(user, "user_password");

        if(password.equals(old_password_input.getText())){
            newpassword = new_password_input1.getText();
            if(newpassword.equals(new_password_input2.getText())){
                if(password.equals(newpassword)) {
                    msg_output.setText("Stare hasło i nowe są takie same");
                }
                else{
                    username = dBuser.userData(user, "user_login");
                    if(username.equals(newpassword)){
                        msg_output.setText("Hasło nie może być takie same jak login");
                    }
                    else {
                        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Password_change_confirmation.fxml"));
                        Scene scene = new Scene(DashboardStart.load(), 300, 200);
                        Stage stage = new Stage();
                        stage.setTitle("App!");
                        stage.setScene(scene);
                        stage.show();
                        Stage oldstage = (Stage) Sure_changepassword.getScene().getWindow();
                        oldstage.close();
                    }
                }
            }
            else{
                msg_output.setText("Hasła nie są zgodne");
            }
        }
        else{
            msg_output.setText("Stare hasło jest złe");
        }


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
