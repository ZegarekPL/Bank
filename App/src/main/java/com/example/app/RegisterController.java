package com.example.app;

import com.example.app.objects.Validator;
import com.example.app.objects.User;
import database.DBaccount;
import database.DBuser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterController extends LoginController{

    public TextField name_input_reg;
    public TextField surname_input_reg;
    public TextField phone_input_reg;
    public TextField email_input_reg;
    public TextField login_input_reg;
    public TextField password_input1_reg;
    public TextField password_input2_reg;
    public DatePicker birth_input_reg;
    public Text msg_output_reg;

    public void RegisterFinal_Button() throws IOException, SQLException {
        User user = new User();
        user.setUser_name(name_input_reg.getText());
        user.setUser_surname(surname_input_reg.getText());

        user.setUser_phone(phone_input_reg.getText());
        user.setUser_login(login_input_reg.getText());
        user.setUser_email(email_input_reg.getText());
        user.setUser_password(password_input1_reg.getText());
        user.setUser_password(password_input2_reg.getText());

        DBuser dBuser=new DBuser();
        dBuser.Connect();
        DBaccount dBaccount= new DBaccount();
        dBaccount.Connect();




        if(login_input_reg.getText() == ""){
            msg_output_reg.setText("Wpisz login");
        }
        else {
            if (password_input1_reg.getText().equals(password_input2_reg.getText())) {
                if((password_input1_reg.getText() == "")||(password_input2_reg.getText() == "")){
                    msg_output_reg.setText("Wpisz oba hasła");
                }
                else{
                    if (Validator.isValidPassword(password_input1_reg.getText())) {
                        if (dBuser.isloginexist(user)) {
                            msg_output_reg.setText("Login jest zajęty");
                        } else {
                            if(birth_input_reg.getValue() == null) {
                                msg_output_reg.setText("Pole urodzin jest puste");
                            }
                            else{
                                LocalDate myDate = birth_input_reg.getValue();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                String formattedDateString = myDate.format(formatter);
                                user.setUser_birth(formattedDateString);

                                String email = email_input_reg.getText();
                                if (Validator.isValidEmail(email)) {
                                    if (dBuser.isemailexist(user)) { // popraw validacje
                                        msg_output_reg.setText("Adres e-mail jest już zajęty");
                                    } else {
                                        String phone = phone_input_reg.getText();
                                        if (Validator.isValidPhone(phone)) {
                                            if (dBuser.isphoneexist(user)) {
                                                msg_output_reg.setText("Ten numer telefonu jest już zajęty");
                                            } else {
                                                String name = name_input_reg.getText();
                                                if (Validator.isValidSurName(name)) {
                                                    String surname = surname_input_reg.getText();
                                                    if (Validator.isValidSurName(surname)) {

                                                        dBuser.register(user);

                                                        String id = dBuser.userData(user, "id");
                                                        dBaccount.createAccount(id, "credit");
                                                        dBaccount.createAccount(id, "debit");
                                                        dBaccount.createAccount(id, "saving");

                                                        FXMLLoader LoginStart = new FXMLLoader(App.class.getResource("Login.fxml"));
                                                        Scene scene = new Scene(LoginStart.load(), 600, 400);
                                                        Stage stage = new Stage();
                                                        stage.setTitle("App!");
                                                        stage.setScene(scene);
                                                        stage.show();
                                                        Stage oldstage = (Stage) login_input_reg.getScene().getWindow();
                                                        oldstage.close();
                                                    } else {
                                                        msg_output_reg.setText("Nazwisko jest niepoprawnie wpisane");
                                                    }
                                                } else {
                                                    msg_output_reg.setText("Imię jest niepoprawnie wpisane");
                                                }
                                            }
                                        } else {
                                            msg_output_reg.setText("Wprowadzono niepoprawny numer telefonu");
                                        }
                                    }
                                } else {
                                    msg_output_reg.setText("Wprowadzono niepoprawny adres e-mail");
                                }
                            }
                        }
                    } else {
                        msg_output_reg.setText("Hasło musi zawierać: min 8 znaków. W tym 1 duża litere, 1 mała litere oraz 1 znak");
                    }
                }
            } else {
                msg_output_reg.setText("Hasła nie są zgodne");
            }
        }
    }

    public void Go_to_Login_Button() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) login_input_reg.getScene().getWindow();
        oldstage.close();
    }
}