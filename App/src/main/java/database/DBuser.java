package database;

import com.example.app.objects.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBuser extends DataBase_Connect {

    //----------------------------LOGIN-------------------------------
    public boolean authenticate(User user) throws SQLException { //LOGIN
        PreparedStatement statement= connection.prepareStatement("select * from users where user_password=? AND user_login=? AND user_status = ?");
        statement.setString(1, user.getUser_password());
        statement.setString(2, user.getUser_login());
        statement.setString(3, "Active");
        ResultSet results=statement.executeQuery();
        return results.next();
    }
    //----------------------------REGISTER-------------------------------
    public void register(User user) throws SQLException { //REGISTER
        PreparedStatement statement= connection.prepareStatement("insert into users (user_name, user_surname, user_birth, user_phone, user_login, user_email, user_password) Values (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, user.getUser_name());
        statement.setString(2, user.getUser_surname());
        statement.setString(3, user.getUser_birth());
        statement.setString(4, user.getUser_phone());
        statement.setString(5, user.getUser_login());
        statement.setString(6, user.getUser_email());
        statement.setString(7, user.getUser_password());
        statement.executeUpdate();
    }
    public boolean isloginexist(User user) throws SQLException {
        PreparedStatement statement= connection.prepareStatement("SELECT user_login FROM users WHERE user_login=?");
        statement.setString(1, user.getUser_login());
        ResultSet results=statement.executeQuery();
        return results.next();
    }
    public boolean isemailexist(User user) throws SQLException {
        PreparedStatement statement= connection.prepareStatement("SELECT user_email FROM users WHERE user_email=?");
        statement.setString(1, user.getUser_email());
        ResultSet results=statement.executeQuery();
        return results.next();
    }
    public boolean isphoneexist(User user) throws SQLException {
        PreparedStatement statement= connection.prepareStatement("SELECT user_phone FROM users WHERE user_phone=?");
        statement.setString(1, user.getUser_phone());
        ResultSet results=statement.executeQuery();
        return results.next();
    }

    //----------------------------DASHBOARD + CHANGE PASSWORD----------------------------

    public String userData(User user, String object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_login = ?");
        statement.setString(1, user.getUser_login());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            String object1 = resultSet.getString(object);
            return object1;
        }
        return null;
    }
    public String update_user_password(String username, String newPassword) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET user_password = ? WHERE user_login = ?");
        statement.setString(1, newPassword);
        statement.setString(2, username);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            String updateuserpasswordtext = "Hasło użytkownika " + username + " zostało zaktualizowane.";
            return updateuserpasswordtext;
        }
        else {
            String updateuserpasswordtext = "Hasło użytkownika " + username + " zostało zaktualizowane.";
            return updateuserpasswordtext;
        }
    }
    public String deactiveuser(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET user_status = ? WHERE id = ?");
        statement.setString(1, "Deactive");
        statement.setString(2, id);
        statement.executeUpdate();
        String msg = "Konto zostało zdeaktywowane.";
        return msg;
    }
}