import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDatabase(String hostName, String dataBaseName, String userName, String password) {

        // 1.Step: Registration to the Driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 2.Step: Create connection with database
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dataBaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is successful!");
        return connection;
    }

    public static Statement createStatement() {

        // 3.Step: Create statement
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Statement created!");

        return statement;
    }

    // 4.Step: Execute the query
    public static boolean execute(String query) {

        boolean isExecuted = false;

        try {
            isExecuted = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Command executed successfully");
        return isExecuted;
    }

    // 5.Step: Close the connection and statement
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed");
            } else {
                System.out.println("Connection and statement are not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dropTable(String tableName){
        try {
            statement.execute("drop table " + tableName);
            System.out.println("Table " + tableName + " dropped!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable(String tableName, String... columnName_DataType ){

        StringBuilder columnName_DataTypeString  = new StringBuilder("");
        for (String w: columnName_DataType){
            columnName_DataTypeString.append(w).append(",");
        }

        columnName_DataTypeString.deleteCharAt(columnName_DataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+tableName+"("+columnName_DataTypeString+" )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Table created successfully!");
    }

    // Insert into tablename (columnName1, columnName2 ...) values (value1, value2, ...)
    public static void insertIntoTable(String tablename, String... columnName_Values){

        StringBuilder columnName = new StringBuilder("");
        StringBuilder value = new StringBuilder("");


        for (String w : columnName_Values){

            int firstSpace = w.indexOf(" ");
                columnName.append(w.substring(0,firstSpace)).append(",");
                value.append(w.substring(firstSpace)).append(",");
        }

        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));

        String command = "Insert into " + tablename +  "(" + columnName + ")" + "values (" + value + ")";

        try {
            statement.execute(command);
            System.out.println("Data inserted successfully into " + tablename);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
