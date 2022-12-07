import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exe01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the Driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 4.Step: Execute query
        // 1.Example: Create a workers table with the columns worker_id, worker_name, worker_salary
        String sql = "create table workers(worker_id varchar(10), worker_name varchar(50), worker_salary int)";
        boolean sqlResult = st.execute(sql);
        System.out.println("sqlResult = " + sqlResult);

        /*
        execute() can be used in DDL(Table creation, dropping table, altering the table) and DQL(Select)
        1) If you use execute() in DDL you will get "false" everytime
        2) If you use execute() in DQL you will get false or true
            When you use execute() in DQL, if you get ResultSet Object in return you will get true otherwise you will get false
         */
        // 2.Example: Alter the table by adding worker_address column into the workers table
        String sql1 = "alter table workers add worker_address varchar(80)";
        st.execute(sql1);

        // 3.Example: Drop the table
        String sql2 = "drop table workers";
        st.execute(sql2);

        // 5.Step: Close the connection and statement
        con.close();
        st.close();

    }
}
