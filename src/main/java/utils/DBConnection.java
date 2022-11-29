package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import page.LoginForEformWithDB;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBConnection {
    /*Connecting to oracle database*/
    public static Connection connectToOracle() throws SQLException, IOException {

        //Registering the Driver
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //Getting the connection
        Connection oracleCon = DriverManager.getConnection(DBConnectionBuilder.Connections.ORACLE_CONNECTION_URL.getValue(),DBConnectionBuilder.Connections.ORACLE_USERNAME.getValue(), DBConnectionBuilder.Connections.ORACLE_PASSWORD.getValue());
        System.out.println("Connected to Oracle database.....");
        return oracleCon;
    }
    /*Connecting to mySql database*/
    public static Connection connectToMySQL() throws SQLException, IOException {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //Getting the connection
        Connection mySqlCon = DriverManager.getConnection(DBConnectionBuilder.Connections.SQL_CONNECTION_URL.getValue(), DBConnectionBuilder.Connections.SQL_USERNAME.getValue(), DBConnectionBuilder.Connections.SQL_PASSWORD.getValue());
        System.out.println("Connected to MySQL database......");
        return mySqlCon;
    }
    /*Connecting to mongoDB database*/
    public static void connectToMongoDB() throws Exception {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient(DBConnectionBuilder.Connections.MONGO_HOSTNAME.getValue(), DBConnectionBuilder.Connections.MONGO_PORT_NUMBER.getPort());
        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
        System.out.println("Connected to the MongoDB database successfully");
        //Accessing the data from MongoDB
        extractDataFromMongoDB(mongo);
    }
    /*Fetching data from the mongoDB database*/
    public static void extractDataFromMongoDB(MongoClient mongo) throws Exception{
        // Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");
        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("loginCredentials");
        System.out.println("Collection loginCredentials selected successfully");
        Document document1 = new Document("Username", "Sk66921")
                .append("Password","12345674");
        Document document2 = new Document("Username", "as61837")
                .append("Password","12347894");
        Document document3 = new Document("Username", "ac67129")
                .append("Password","12345622");
        Document document4 = new Document("Username", "sk57670")
                .append("Password","12345654");
        Document document5 = new Document("Username", "dn67143")
                .append("Password","12345634");

        List<Document> list = new ArrayList<>();
        list.add(document1);
        list.add(document2);
        list.add(document3);
        list.add(document4);
        list.add(document5);
        //inserting data to collection
        collection.insertMany(list);
        //fetching the data from documents
        for(Document d:list){
            String userName = d.getString("Username");
            String password = d.getString("Password");
            LoginForEformWithDB loginForEformWithDB = new LoginForEformWithDB(userName,password);
        }
        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;
        // Getting the iterator, to show all the documents
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }
    /*Fetching data from the sql database*/
    public void ExtractDataFromMySQL(Connection con) throws SQLException {
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Executing the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM credentials");
        System.out.println("Contents of credential table in MySQL database: ");
        while (rs.next()) {
            String userName = rs.getString("username");
            String password = rs.getString("password");
            System.out.println("userName:" + userName);
            System.out.println("password:" + password);
            System.out.println("*****************************");
            System.out.println();
            LoginForEformWithDB loginForEformWithDB = new LoginForEformWithDB(userName, password);
        }
        System.out.println();
    }
    /*Fetching data from the oracle database*/
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
            LoginForEformWithDB loginForEformWithDB = new LoginForEformWithDB(userName, password);
        }
        System.out.println();
    }
}
