package controllers;

import org.testng.annotations.DataProvider;
import utils.selenium.DBConnectionBuilder;
import utils.selenium.DBUtils;

import java.sql.SQLException;

public class MySqlDataProvider {

    @DataProvider(name = "ExtractDataFromMySqlDatabase1")
    public Object[][] feedDPQuery1() throws SQLException, ClassNotFoundException {
        Object data[][] = DBUtils.DataFromDatabase(DBConnectionBuilder.Connections.SQL_DRIVER.getValue(), DBConnectionBuilder.Connections.SQL_CONNECTION_URL.getValue(), DBConnectionBuilder.Connections.SQL_USERNAME.getValue(),
                DBConnectionBuilder.Connections.SQL_PASSWORD.getValue(), DBConnectionBuilder.Connections.SQL_QUERY1.getValue());
        return data;
    }
    @DataProvider(name = "ExtractDataFromMySqlDatabase2")
    public Object[][] feedDPQuery2() throws SQLException, ClassNotFoundException {
        Object data[][] = DBUtils.DataFromDatabase(DBConnectionBuilder.Connections.SQL_DRIVER.getValue(), DBConnectionBuilder.Connections.SQL_CONNECTION_URL.getValue(), DBConnectionBuilder.Connections.SQL_USERNAME.getValue(),
                DBConnectionBuilder.Connections.SQL_PASSWORD.getValue(), DBConnectionBuilder.Connections.SQL_QUERY2.getValue());
        return data;
    }
}


