package utils;

import java.sql.Connection;
import java.util.Properties;

public class DBConnectionBuilder {
    /**Group of pre-defined constants.*/
    public enum Connections{
        SQL_DRIVER("sql_driver","com.mysql.cj.jdbc.Driver"),
        SQL_CONNECTION_URL("mySqlUrl","jdbc:mysql://localhost:3306/orangehrm"),
        SQL_USERNAME("sqlUsername","root"),
        SQL_PASSWORD("sqlPassword","Sriman@7849"),
        SQL_QUERY1("sqlQuery1","select * from credentials;"),
        SQL_QUERY2("sqlQuery2","select * from add_employee"),
        ORACLE_DRIVER("oracle_driver","oracle.jdbc.driver.OracleDriver"),
        ORACLE_CONNECTION_URL(" "," "),
        ORACLE_USERNAME("oracleUsername "," "),
        ORACLE_PASSWORD("oraclePassword"," "),
        MONGO_HOSTNAME("mongoHost","localhost"),
        MONGO_PORT_NUMBER("mongoPort",2707),
        ORACLE_QUERY("oracleQuery","select * from credentials");
        private String property;
        private String value;
        private int port;
        /*Constructor for setting the properties*/
        Connections(final String aProperty, final String aValue)
        {
            property = aProperty;
            value = aValue;
        }
        Connections(final String aProperty, final int aPort)
        {
            property = aProperty;
            port = aPort;
        }
        public String getProperty()
        {
            return property;
        }
        public String getValue()
        {
            return value;
        }
        public int getPort()
        {
            return port;
        }
        private void setValue(final String aValue)
        {
            value = aValue;
        }
        public void setPort(final int aPort)
        {
            port= aPort;
        }
        public static void initialize(final Properties aPropertyTable)
        {
            for(final Connections connections : values())
            {
                final String key = connections.getProperty();
                final String defaultValue = connections.getValue();
                final int defaultPort = connections.getPort();
                connections.setValue(aPropertyTable.getProperty(key, defaultValue));
                connections.setPort(Integer.parseInt(aPropertyTable.getProperty(key, String.valueOf(defaultPort))));
            }
        }
    }
}
