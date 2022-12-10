package controllers;

import org.testng.annotations.DataProvider;
import utils.DBConnectionBuilder;
import utils.DBUtils;

import java.sql.SQLException;

public class OracleDataProvider {

    @DataProvider(name = "ExtractDataFromOracleDatabase")
    public Object[][] feedDP() throws SQLException, ClassNotFoundException {
        Object data[][] = DBUtils.DataFromDatabase(DBConnectionBuilder.Connections.ORACLE_DRIVER.getValue(), DBConnectionBuilder.Connections.ORACLE_CONNECTION_URL.getValue(), DBConnectionBuilder.Connections.ORACLE_USERNAME.getValue(),
                DBConnectionBuilder.Connections.ORACLE_PASSWORD.getValue(), DBConnectionBuilder.Connections.ORACLE_QUERY.getValue());
        return data;
    }
}