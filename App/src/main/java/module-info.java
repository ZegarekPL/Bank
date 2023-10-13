module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports database;
    opens database to javafx.fxml;
}