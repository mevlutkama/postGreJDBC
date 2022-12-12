import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 1.Example: Select the country names whose region id is 1
        String sql1 = "select country_name from countries where region_id = 1";

        // If you use execute() method, you will get just true or false but i want to see the records.
        boolean r1 = st.execute(sql1);
        System.out.println("r1 = " + r1);

        // To see the records we have another method which is executeQuery()
        ResultSet resultSet = st.executeQuery(sql1);
        while (resultSet.next()){
            System.out.println(resultSet.getString("country_name"));
        }

        // 2.Example: Select the country ids and country names whose region id's are greater than 2
        String sql2 = "select country_id, country_name from countries where region_id > 2";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("country_id") + "==>" + resultSet2.getString("country_name"));
        }

        // 3.Example: Select the worker whose salary is the lowest from workers table
        String sql3 = "select * from workers where salary = (select min(salary) from workers)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("id") + "--" + resultSet3.getString("name") + "--" +  resultSet3.getInt("salary") );
        }

        con.close();
        st.close();
        resultSet.close();

    }
}
