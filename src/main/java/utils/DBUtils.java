package utils;

import java.sql.*;

public class DBUtils {
    /* Fetching the data from different databases*/
    public static Object[][] DataFromDatabase(String DriverName,String url,String username,String password,String query) throws SQLException, ClassNotFoundException{
        Connection connection=null;
        Statement statement= null;
        ResultSet resultSet= null;
        ResultSetMetaData resultSetMetaData;
        int rows=0;
        int cols=0;
        Object[][] data = new Object[0][0];
        try{
            //Loading the required JDBC Driver class
            Class.forName(DriverName);
            //Creating a connection to the database
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                //creating statement
                statement  = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            }
            if(statement != null){
                //storing the result into result_set
                resultSet= statement.executeQuery(query);
            }
            if(resultSet != null){
                resultSet.last();
                //Getting row count
                rows= resultSet.getRow();
                System.out.println("Number of rows. "+ rows);
                resultSetMetaData = resultSet.getMetaData();
                if(resultSetMetaData != null){
                    //Getting column count
                    cols= resultSetMetaData.getColumnCount();
                    System.out.println("Number of cols: "+cols);
                    data = new String[rows][cols];
                    //iterating over the result set
                    int i=0;
                    resultSet.beforeFirst();
                    int count=1;
                    while(resultSet.next() && count<=5)
                    {
                        for (int j = 0; j<cols; j++)
                        {
                            data[i][j]=resultSet.getString(j+1);
                            System.out.println(data[i][j]);
                        }
                        i++;
                        count++;
                    }
                }
            }
        } catch (Exception se){
            se.printStackTrace();
        }
        finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
        return data;
    }
}
