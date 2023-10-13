package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBaccount extends DataBase_Connect {
    public String createAccount(String id, String card_type) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into account (id_user, card_type, card_number, card_amound) Values (?, ?, ?, 0)");
        statement.setInt(1, Integer.parseInt(id));
        statement.setString(2, card_type);
        String number = "0";
        if (card_type.equals("credit")) {
            number = "1";
        } else if (card_type.equals("debit")) {
            number = "2";
        } else if (card_type.equals("saving")) {
            number = "3";
        } else {
            System.out.println("nie znaleziono karty");
        }
        statement.setString(3, id + "/" + number);
        statement.executeUpdate();
        return null;
    }

    public String getCardNumber(String id, String cardtype) throws SQLException {
        String cardnumber = null;
        PreparedStatement statement = connection.prepareStatement("SELECT card_number FROM account WHERE id_user = ? AND card_type = ?");
        statement.setString(1, id);
        statement.setString(2, cardtype);
        ResultSet results = statement.executeQuery();
        if (results.next()) {
            cardnumber = results.getString("card_number");
        }
        return cardnumber;
    }
    public String getIdFormCardNumber(String cardnumber) throws SQLException {
        String id = null;
        PreparedStatement statement = connection.prepareStatement("SELECT id_user FROM account WHERE card_number = ?");
        statement.setString(1, cardnumber);
        ResultSet results = statement.executeQuery();
        if (results.next()) {
            id = results.getString("id_user");
        }
        return id;
    }

    public String getCardAmount(String cardnumber) throws SQLException {
        String cardamound = null;
        PreparedStatement statement = connection.prepareStatement("SELECT card_amound FROM account WHERE card_number = ?");
        statement.setString(1, cardnumber);
        ResultSet results = statement.executeQuery();
        if (results.next()) {
            cardamound = results.getString("card_amound");
        }
        return cardamound;
    }
    public String updateCardAmount(String amount, String cardnumber) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE account SET card_amound = ? WHERE card_number = ?");
        statement.setString(1, amount);
        statement.setString(2, cardnumber);
        statement.executeUpdate();

        String newcardamount = getCardAmount(cardnumber);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            String newamounttext = "Masz na koncie nową kwotę: " + newcardamount + " zł";
            return newamounttext;
        } else {
            String newamounttext = "Ilość pieniędzy na koncie pozostała taka sama i wynosi: " + newcardamount + " zł";
            return newamounttext;
        }
    }
    public static void deactiveaccount(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE account SET card_status = ? WHERE id_user = ?");
        statement.setString(1, "Deactive");
        statement.setString(2, id);
        statement.executeUpdate();
    }
    public String cardstatus(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT card_status FROM account WHERE id_user = ?");
        statement.setString(1, id);
        ResultSet results = statement.executeQuery();
        if(results.next()) {
            String status = results.getString("card_status");
            String number = "0";
            if (status.equals("Active")) {
                number = "1";
                return number;
            } else if (status.equals("Deactive")) {
                number = "2";
                return number;
            }
            return "0";
        }
        return "0";
    }
}