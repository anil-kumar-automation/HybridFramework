package utils;

import java.sql.Connection;
import java.util.Properties;

public class DBConnectionBuilder {
    /*Group of pre-defined constants.*/
    public enum Connections{
        CONNECTION_CHOICE("connectionChoice","mongoDB"),//can change iff requires another connection.
        SQL_CONNECTION_URL("mySqlUrl","jdbc:mysql://localhost:3306/E_formDetails"),
        SQL_USERNAME("sqlUsername","root"),
        SQL_PASSWORD("sqlPassword","Sriman@7849"),
        ORACLE_CONNECTION_URL(" "," "),
        ORACLE_USERNAME("oracleUsername "," "),
        ORACLE_PASSWORD("oraclePassword"," "),
        MONGO_HOSTNAME("mongoHost","localhost"),
        MONGO_PORT_NUMBER("mongoPort",2707);

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
    public static void creatingMongoConnection() throws Exception {
        DBConnection.connectToMongoDB();
    }
    /*Connection switch yto select the particular database*/
    public static Connection creatingConnection() throws Exception {
        System.out.println("Creating connection...");
        if(Connections.CONNECTION_CHOICE.getValue().equals("Sql") || Connections.CONNECTION_CHOICE.getValue().equals("Oracle")){
            switch (Connections.CONNECTION_CHOICE.getValue()) {
                case "Sql":
                    return DBConnection.connectToMySQL();
                case "Oracle":
                    return DBConnection.connectToOracle();
                default:
                    System.out.println("Provide a valid Connection choice");
                    break;
            }
        }else{
            creatingMongoConnection();
        }
        return null;
    }
}
