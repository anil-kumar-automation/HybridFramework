package controllers;

import org.testng.annotations.DataProvider;
import utils.DBConnectionBuilder;
import utils.DBUtils;

import java.sql.SQLException;

public class MySqlDataProvider {

    @DataProvider(name = "ExtractDataFromMySqlDatabase")
    public Object[][] feedDP() throws SQLException, ClassNotFoundException {
        Object data[][] = DBUtils.DataFromDatabase(DBConnectionBuilder.Connections.SQL_DRIVER.getValue(), DBConnectionBuilder.Connections.SQL_CONNECTION_URL.getValue(), DBConnectionBuilder.Connections.SQL_USERNAME.getValue(),
                DBConnectionBuilder.Connections.SQL_PASSWORD.getValue(), DBConnectionBuilder.Connections.SQL_QUERY.getValue());
        return data;
    }
}


