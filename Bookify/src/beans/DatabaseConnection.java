package beans;
import java.sql.*;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public String DB_DRIVER;
    public String DB_URL;
    public String DB_USERNAME;
    public String DB_PASSWORD;
    public String DB_NAME;

    public DatabaseConnection(String DB_DRIVER, String DB_URL, String DB_USERNAME, String DB_PASSWORD, String DB_NAME) {
        this.DB_DRIVER = DB_DRIVER;
        this.DB_URL = DB_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
        this.DB_NAME = DB_NAME;
    }

    public DatabaseConnection() {
    }

    public Connection initilizeConnection() throws ClassNotFoundException, SQLException {

//       get JDBC class by External Driver
        Class.forName(this.DB_DRIVER);

//        create Connection
//      Connection connection = (Connection) DriverManager.getConnection(db_url + db_name,db_user_name,db_password);
        Connection connection = (Connection) DriverManager.getConnection(this.DB_URL + this.DB_NAME, this.DB_USERNAME, this.DB_PASSWORD);

        return connection;
    }
}
