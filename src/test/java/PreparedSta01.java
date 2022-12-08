import java.sql.*;

public class PreparedSta01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the Driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 1.Example: Update the number of employees to 20000 if the company name is IBM by using prepared statement
        // 1.Step: Creating Prepared Statement Query
        String sql1 = "update companies set number_of_employees = ? where company = ?";

        // 2.Step: Creating Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        // 3.Step: Assign the values by using 'setInt(), setString()...'
        pst1.setInt(1,9000);
        pst1.setString(2,"GOOGLE");

        // 4.Step: Execute the Query
        int numberOfRowsUpdated = pst1.executeUpdate();
        System.out.println("numberOfRowsUpdated = " + numberOfRowsUpdated);

        String sql2 = "select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) + "--" + resultSet1.getInt(3));
        }

        // 2.Example: Update the number of employees 25000 if the company name is IBM
        pst1.setInt(1, 25000);
        pst1.setString(2, "IBM");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated2 = " + numOfRecordsUpdated2);

        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1)+"==>"+resultSet2.getString(2)+"==>"+resultSet2.getInt(3));
        }
    }
}
