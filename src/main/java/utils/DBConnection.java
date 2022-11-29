package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration/config.properties");
    Properties properties = new Properties();

    public DBConnection() throws FileNotFoundException {
    }

    public Connection creatingConnection() throws SQLException, IOException {
        properties.load(fis);
        String connectionChoice = properties.getProperty("connectionChoice");

        System.out.println("Creating connection...");
        switch (connectionChoice) {
            case "Sql":
                return connectToMySQL();
            case "oracle":
                return connectToOracle();
            default:
                System.out.println("Provide a valid Connection choice");
                break;
        }

        return null;
    }

    public Connection connectToOracle() throws SQLException, IOException {
        properties.load(fis);
        //Registering the Driver
        // DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //Getting the connection
        String oracleUrl = properties.getProperty("oracleUrl");
        Connection oracleCon = DriverManager.getConnection(oracleUrl, properties.getProperty("oracleUserName"), properties.getProperty("oraclePassword"));
        System.out.println("Connected to Oracle database.....");
        return oracleCon;
    }

    public Connection connectToMySQL() throws SQLException, IOException {
        properties.load(fis);
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = properties.getProperty("mySqlUrl");
        Connection mySqlCon = DriverManager.getConnection(mysqlUrl, properties.getProperty("sqlUsername"), properties.getProperty("sqlPassword"));
        System.out.println("Connected to MySQL database......");
        return mySqlCon;
    }

    public void ExtractDataFromMySQL(Connection con) throws SQLException {
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Executing the query
        /*ResultSet rs = stmt.executeQuery("select * from credentials");*/
        ResultSet rs = stmt.executeQuery("SELECT * FROM sql12569537.BfsiCredential");
        System.out.println("Contents of credential table in MySQL database: ");
        while (rs.next()) {
            String userName = rs.getString("Username");
            String password = rs.getString("Password");
            System.out.println("userName:" + userName);
            System.out.println("password:" + password);
            System.out.println("*****************************");
            System.out.println();
        }
        System.out.println();
    }

    public void ExtractDataFromOracle(Connection con) throws SQLException {
        //Creating the Statement
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from credentials");
        System.out.println("Contents of Login table in Oracle database: ");
        while (rs.next()) {
            String userName = rs.getString("Username");
            String password = rs.getString("Password");
            System.out.println("userName:" + userName);
            System.out.println("password:" + password);
            System.out.println("*********************************");
            System.out.println();
        }
        System.out.println();

    }
}
