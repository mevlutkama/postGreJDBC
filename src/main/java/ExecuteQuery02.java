import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
        // 1.Way: By using OFFSET and FETCH NEXT
        String sql1 = "select company, number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 row only";
        ResultSet resultSet1 = st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company") + "--" + resultSet1.getInt("number_of_employees"));
        }

        // 2.Way: By using subquery
        String sql2 = "select company, number_of_employees\n" +
                "from companies\n" +
                "where number_of_employees = (select max(number_of_employees) \n" +
                "from companies \n" +
                "where number_of_employees < \n" +
                "(select max(number_of_employees) from companies))";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("company") + "--" + resultSet2.getInt("number_of_employees"));
        }

        // 2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees
        String sql3 = "select company, number_of_employees\n" +
                "from companies \n" +
                "where number_of_employees < (select avg(number_of_employees) from companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString(1) + "--" + resultSet3.getInt(2));
        }
        con.close();
        st.close();
    }
}
