package com.example.app;

import database.DBaccount;
import database.DBhistory;
import database.DBuser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardController extends LoginController{

    //---------------------------------HOME------------------------------------
    public Button Logout;
    public Text date_home;
    public Text hour_home;
    public Text credit_amount;
    public Text credit_number;
    public Text debit_amount;
    public Text debit_number;
    public Text saving_amount;
    public Text saving_number;

    //---------------------------------PRZEŚLIJ--------------------------------
    public Text date_przeslij;
    public Text hour_przeslij;
    public TextField amount_input_give;
    public TextField card_number_input_give;
    public Text give_msg;
    public Button Deleteaccount_konto;
    private String[] card = {"Karta Kredytowa","Karta Debetowa", "Karta Oszczędnościowa"};
    public ChoiceBox <String> give_choose_card = new ChoiceBox<>(FXCollections.observableArrayList(card));
    public Button give;
    public static Integer Intnewamount_give_sender;
    public static String cardnumber_give_sender;
    public static String cardnumber_give_reciver;
    public static Integer Intnewamount_give_reciver;

    //---------------------------------WPŁAĆ-----------------------------------
    public Text date_wplac;
    public Text hour_wplac;
    public ChoiceBox <String> ante_choose_card = new ChoiceBox<>(FXCollections.observableArrayList(card));
    public Button ante;
    public Text ante_msg;
    public TextField amount_input;
    public static Integer Intnewamount;
    public static String cardnumber_ante_reciver;

    //---------------------------------WYPŁAĆ----------------------------------
    public Text date_wyplac;
    public Text hour_wyplac;
    public ChoiceBox <String> withdraw_choose_card = new ChoiceBox<>(FXCollections.observableArrayList(card));
    public Button withdraw;
    public Text withdraw_msg;
    public TextField amount_input_withdraw;
    public static Integer Intnewamount_withdraw;
    public static String cardnumber_withdraw_sender;

    //---------------------------------HISTORIA--------------------------------
    public Text date_historia;
    public Text hour_historia;
    public TextField history_amount_minus;
    public TextField history_amount_plus;
    public TextField date_amount_minus;
    public TextField date_amount_plus;
    private String[] history_iw = {"Income","Withdraw"};
    public ChoiceBox <String> history_IW = new ChoiceBox<>(FXCollections.observableArrayList(history_iw));
    ObservableList<History> list= FXCollections.observableArrayList();
    public TableView <History>table_history;
    public TableColumn <History, String> table_history_sender;
    public TableColumn <History, String> table_history_reciver;
    public TableColumn <History, String> table_history_date;
    public TableColumn <History, String> table_history_amount;
    public TableColumn <History, String> table_history_cardnumber_sender;
    public TableColumn <History, String> table_history_cardnumber_reciver;
    public TableColumn <History, String> table_history_iw;


    //---------------------------------KONTO-----------------------------------
    public Text date_konto;
    public Text hour_konto;
    public Text user_name_textfield;
    public Text user_surname_textfield;
    public Text user_birth_textfield;
    public Text user_phone_textfield;
    public Text user_email_textfield;
    public Text user_login_textfield;
    public Text user_password_textfield;
    public Button Changepassword_konto;




    public void Logout() throws IOException {
        FXMLLoader LoginStart = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(LoginStart.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) Logout.getScene().getWindow();
        oldstage.close();
    }

    public void initialize() throws SQLException {

        TimeLocal(date_home, hour_home);
        TimeLocal(date_przeslij, hour_przeslij);
        TimeLocal(date_wplac, hour_wplac);
        TimeLocal(date_wyplac, hour_wyplac);
        TimeLocal(date_historia, hour_historia);
        TimeLocal(date_konto, hour_konto);

        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBaccount dBaccount = new DBaccount();
        dBaccount.Connect();
        user_name_textfield.setText(dBuser.userData(user, "user_name"));
        user_surname_textfield.setText(dBuser.userData(user, "user_surname"));
        user_birth_textfield.setText(dBuser.userData(user, "user_birth"));
        user_phone_textfield.setText(dBuser.userData(user, "user_phone"));
        user_email_textfield.setText(dBuser.userData(user, "user_email"));
        user_login_textfield.setText(dBuser.userData(user, "user_login"));
        user_password_textfield.setText(dBuser.userData(user, "user_password"));

        give_choose_card.getItems().addAll(card);
        ante_choose_card.getItems().addAll(card);
        withdraw_choose_card.getItems().addAll(card);
        history_IW.getItems().addAll(history_iw);

        String user_id = dBuser.userData(user, "id");
        String cardnumberr1 = dBaccount.getCardNumber(user_id, "credit");
        String cardnumberr2 = dBaccount.getCardNumber(user_id, "debit");
        String cardnumberr3 = dBaccount.getCardNumber(user_id, "saving");
        String cardamound1 = dBaccount.getCardAmount(cardnumberr1);
        String cardamound2 = dBaccount.getCardAmount(cardnumberr2);
        String cardamound3 = dBaccount.getCardAmount(cardnumberr3);

        credit_number.setText("Numer konta: " + cardnumberr1);
        credit_amount.setText(cardamound1 + " zł");
        debit_number.setText("Numer konta: " + cardnumberr2);
        debit_amount.setText(cardamound2 + " zł");
        saving_number.setText("Numer konta: " + cardnumberr3);
        saving_amount.setText(cardamound3 + " zł");

        initializeColums();

        try {
            loadData();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void initializeColums(){
        table_history_sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        table_history_reciver.setCellValueFactory(new PropertyValueFactory<>("reciver"));
        table_history_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_history_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table_history_cardnumber_sender.setCellValueFactory(new PropertyValueFactory<>("cardnumber_sender"));
        table_history_cardnumber_reciver.setCellValueFactory(new PropertyValueFactory<>("cardnumber_reciver"));
        table_history_iw.setCellValueFactory(new PropertyValueFactory<>("iw"));

    }

    public void TimeLocal(Text date, Text hour){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            String localHour = String.valueOf(now.getHour());
            String localMinutes = String.valueOf(now.getMinute());
            String localSeconds = String.valueOf(now.getSecond());
            String localDay = String.valueOf(now.getDayOfMonth());
            String localMonth = String.valueOf(now.getMonthValue());
            String localYear = String.valueOf(now.getYear());
            if(now.getMonthValue()<10){
                localMonth = "0" + now.getMonthValue();
            }
            if(now.getDayOfMonth()<10){
                localDay = "0" + now.getDayOfMonth();
            }
            String localYearMonthDay = localDay +":"+ localMonth +":"+localYear ;
            date.setText(localYearMonthDay);
            if (now.getSecond() < 10) {
                localSeconds = "0" + now.getSecond();
            }
            if (now.getMinute() < 10) {
                localMinutes = "0" + now.getMinute();
            }
            if(now.getHour() < 10 ){
                localHour = "0" + now.getHour();
            }
            String localHMS = localHour + ":" + localMinutes + ":" + localSeconds;
            hour.setText(localHMS);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public String Date(){
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        String formattedDate = String.format("%02d.%02d.%04d", dayOfMonth, month, year);
        return formattedDate;
    }

    public void Changepassword_konto() throws IOException {
        FXMLLoader LoginStart = new FXMLLoader(App.class.getResource("Change_passwordSite.fxml"));
        Scene scene = new Scene(LoginStart.load(), 450, 300);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) Changepassword_konto.getScene().getWindow();
        oldstage.close();
    }
    public String selectCard(String selectedCard) {
        if (selectedCard.equals("Karta Kredytowa")) {
            return "credit";
        } else if (selectedCard.equals("Karta Debetowa")) {
            return "debit";
        } else if (selectedCard.equals("Karta Oszczędnościowa")) {
            return "saving";
        } else {
            return "";
        }
    }

    public void Give() throws IOException, SQLException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBhistory dBhistory = new DBhistory();
        dBhistory.Connect();
        DBaccount dBaccount = new DBaccount();
        dBaccount.Connect();

        String id = dBuser.userData(user, "id");
        String date = Date();

        cardnumber_give_reciver = card_number_input_give.getText();

        String id_reciver = dBaccount.getIdFormCardNumber(cardnumber_give_reciver);

        String cardstatus = dBaccount.cardstatus(id_reciver);

        if(cardstatus.equals("1")){
            String amountG = amount_input_give.getText();
            String selectedCardG = give_choose_card.getValue();

            if((amountG.equals("0"))||(amountG.equals(""))){
                give_msg.setText("Wpisałeś niepoprawną wartość");
            }
            else {
                if (selectedCardG == null) {
                    give_msg.setText("Wybierz konto");
                } else {
                    String cardtype = selectCard(selectedCardG);

                    cardnumber_give_sender = dBaccount.getCardNumber(id, cardtype);

                    if(cardnumber_give_sender.equals(cardnumber_give_reciver)){
                        give_msg.setText("Nie można przelać na to samo konto");
                    }
                    else{

                        dBhistory.addhistory(id, id_reciver, date, amountG, cardnumber_give_sender, cardnumber_give_reciver, "Income");
                        dBhistory.addhistory(id, id_reciver, date, amountG, cardnumber_give_sender, cardnumber_give_reciver, "Withdraw");

                        String cardamound_sender = dBaccount.getCardAmount(cardnumber_give_sender);
                        Integer Intcardamound_sender = Integer.valueOf(cardamound_sender);
                        Integer Intamount_sender = Integer.valueOf(amountG);Intnewamount_give_sender = Intcardamound_sender - Intamount_sender;

                        String cardamound_reciver = dBaccount.getCardAmount(cardnumber_give_reciver);
                        Integer Intcardamound_reciver = Integer.valueOf(cardamound_reciver);
                        Integer Intamount_reciver = Integer.valueOf(amountG);
                        Intnewamount_give_reciver = Intcardamound_reciver + Intamount_reciver;

                        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Give_confirmation.fxml"));
                        Scene scene = new Scene(DashboardStart.load(), 300, 200);
                        Stage stage = new Stage();
                        stage.setTitle("App!");
                        stage.setScene(scene);
                        stage.show();
                        Stage oldstage = (Stage) ante.getScene().getWindow();
                        oldstage.close();
                    }
                }
            }

        } else{
            give_msg.setText("Numer rachunku nie istnieje");
        }
    }

    public void Ante() throws SQLException, IOException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBhistory dBhistory = new DBhistory();
        dBhistory.Connect();
        DBaccount dBaccount = new DBaccount();
        dBaccount.Connect();

        String id = dBuser.userData(user, "id");
        String date = Date();

        String amountA = amount_input.getText();
        String selectedCard = ante_choose_card.getValue();

        if((amountA.equals("0"))||(amountA.equals(""))){
            ante_msg.setText("Wpisałeś niepoprawną wartość");
        }
        else{
            if(selectedCard == null){
                ante_msg.setText("Wybierz konto");
            }
            else{
                String cardtype = selectCard(selectedCard);

                cardnumber_ante_reciver = dBaccount.getCardNumber(id, cardtype);


                String id_sender = "Bankomat";
                String cardnumber_ante_sender = null;
                dBhistory.addhistory(id_sender, id, date, amountA, cardnumber_ante_sender, cardnumber_ante_reciver, "Income");

                String cardamound = dBaccount.getCardAmount(cardnumber_ante_reciver);
                Integer Intcardamound = Integer.valueOf(cardamound);
                Integer Intamount = Integer.valueOf(amountA);
                Intnewamount = Intcardamound + Intamount;

                FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Ante_confirmation.fxml"));
                Scene scene = new Scene(DashboardStart.load(), 300, 200);
                Stage stage = new Stage();
                stage.setTitle("App!");
                stage.setScene(scene);
                stage.show();
                Stage oldstage = (Stage) ante.getScene().getWindow();
                oldstage.close();
            }
        }
    }
    public void Withdraw() throws SQLException, IOException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBhistory dBhistory = new DBhistory();
        dBhistory.Connect();
        DBaccount dBaccount = new DBaccount();
        dBaccount.Connect();

        String id = dBuser.userData(user, "id");
        String date = Date();

        String amountW = amount_input_withdraw.getText();
        String selectedCard = withdraw_choose_card.getValue();

        if((amountW.equals("0"))||(amountW.equals(""))){
            withdraw_msg.setText("Wpisałeś niepoprawną wartość");
        }
        else{
            if(selectedCard == null){
                withdraw_msg.setText("Wybierz konto");
            }
            else{
                String cardtype = selectCard(selectedCard);
                cardnumber_withdraw_sender = dBaccount.getCardNumber(id, cardtype);
                String id_reciver = "Bankomat";
                String cardnumber_withdraw_reciver = null;
                dBhistory.addhistory(id, id_reciver, date, amountW, cardnumber_withdraw_sender, cardnumber_withdraw_reciver, "Withdraw");

                String cardamound = dBaccount.getCardAmount(cardnumber_withdraw_sender);
                Integer Intcardamound = Integer.valueOf(cardamound);
                Integer Intamount = Integer.valueOf(amountW);
                Intnewamount_withdraw = Intcardamound - Intamount;

                FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Withdraw_confirmation.fxml"));
                Scene scene = new Scene(DashboardStart.load(), 300, 200);
                Stage stage = new Stage();
                stage.setTitle("App!");
                stage.setScene(scene);
                stage.show();
                Stage oldstage = (Stage) ante.getScene().getWindow();
                oldstage.close();
            }
        }
    }
    public static class History{
        private final SimpleStringProperty sender;
        private final SimpleStringProperty reciver;
        private final SimpleStringProperty date;
        private final SimpleStringProperty amount;
        private final SimpleStringProperty cardnumber_sender;
        private final SimpleStringProperty cardnumber_reciver;
        private final SimpleStringProperty iw;
        public History(String sender, String reciver, String date, String amount, String cardnumber_sender, String cardnumber_reciver, String iw){
            this.sender=new SimpleStringProperty(sender);
            this.reciver=new SimpleStringProperty(reciver);
            this.date=new SimpleStringProperty(date);
            this.amount=new SimpleStringProperty(amount);
            this.cardnumber_sender=new SimpleStringProperty(cardnumber_sender);
            this.cardnumber_reciver=new SimpleStringProperty(cardnumber_reciver);
            this.iw=new SimpleStringProperty(iw);
        }
        public String getSender() { return sender.get();}
        public String getReciver() { return reciver.get();}
        public String getDate() { return date.get();}
        public String getAmount() { return amount.get();}
        public String getCardnumber_sender() { return cardnumber_sender.get();}
        public String getCardnumber_reciver() { return cardnumber_reciver.get();}
        public String getIw() { return iw.get();}
    }
    private void loadData() throws SQLException, IOException, ClassNotFoundException {
        DBuser dBuser = new DBuser();
        dBuser.Connect();
        DBhistory dBhistory = new DBhistory();
        dBhistory.Connect();

        String id = dBuser.userData(user, "id");

        List<String> sender1 = dBhistory.loadhistory(id,"id_sender");
        List<String> reciver1 = dBhistory.loadhistory(id,"id_reciver");
        List<String> date1 = dBhistory.loadhistory(id, "history_date");
        List<String> amount1 = dBhistory.loadhistory(id, "history_amount");
        List<String> cardnumber_sender1 = dBhistory.loadhistory(id, "history_number_sender");
        List<String> cardnumber_reciver1 = dBhistory.loadhistory(id, "history_number_reciver");
        List<String> iw1 = dBhistory.loadhistory(id, "history_type");

        for (int i = sender1.size() - 1; i >=0 ; i--) {
            String sender = sender1.get(i);
            String reciver = reciver1.get(i);
            String date = date1.get(i);
            String amount = amount1.get(i);
            String cardnumber_sender = cardnumber_sender1.get(i);
            String cardnumber_reciver = cardnumber_reciver1.get(i);
            String iw = iw1.get(i);

            History history = new History(sender, reciver, date, amount, cardnumber_sender, cardnumber_reciver, iw);
            list.add(history);
        }

        table_history.setItems(list);
    }


    public void Deleteaccount_konto() throws IOException {

        FXMLLoader DashboardStart = new FXMLLoader(App.class.getResource("Deleteaccount_Site.fxml"));
        Scene scene = new Scene(DashboardStart.load(), 300, 200);
        Stage stage = new Stage();
        stage.setTitle("App!");
        stage.setScene(scene);
        stage.show();
        Stage oldstage = (Stage) ante.getScene().getWindow();
        oldstage.close();
    }
}
