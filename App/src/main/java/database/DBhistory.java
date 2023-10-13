package database;

import com.example.app.objects.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBhistory extends DataBase_Connect {

    public String addhistory(String id_sender, String id_reciver, String data, String amount, String cardnumber_sender, String cardnumber_reciver, String iw) throws SQLException {
        PreparedStatement statement= connection.prepareStatement("insert into history (id_sender, id_reciver, history_date, history_amount, history_number_sender, history_number_reciver, history_type) Values (?, ?, ?, ?, ?, ? ,?)");
        statement.setString(1, id_sender);
        statement.setString(2, id_reciver);
        statement.setString(3, data);
        statement.setString(4, amount);
        statement.setString(5, cardnumber_sender);
        statement.setString(6, cardnumber_reciver);
        statement.setString(7, iw);
        statement.executeUpdate();
        return null;
    }

    public List<String> loadhistory(String id , String object) throws SQLException {
        List<String> values = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM history WHERE (id_sender = ? AND history_type = ?) OR (id_reciver = ? AND history_type = ?)");
        statement.setString(1, id);
        statement.setString(2, "Withdraw");
        statement.setString(3, id);
        statement.setString(4, "Income");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String object1 = resultSet.getString(object);
            values.add(object1);
        }
        return values;
    }
}
