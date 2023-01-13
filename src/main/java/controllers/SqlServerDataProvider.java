package controllers;

import org.testng.annotations.DataProvider;

import java.sql.SQLException;

public class SqlServerDataProvider {
    @DataProvider(name = "DataFromMySqlDatabase")
    public static Object[][] getData() throws SQLException {

        return new Object[0][];
    }
}
