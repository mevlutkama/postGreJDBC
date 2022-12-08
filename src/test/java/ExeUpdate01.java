import java.sql.*;

public class ExeUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the Driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees
        String sql1 = "update companies\n" +
                "set number_of_employees = 16000\n" +
                "where number_of_employees < (select avg(number_of_employees) from companies)";

        int numOfUpdatedRec1 = st.executeUpdate(sql1);
        System.out.println("numOfUpdatedRec1 = " + numOfUpdatedRec1);

        String sql2 = "select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+"--"+resultSet1.getInt(3));
        }

    }
}
