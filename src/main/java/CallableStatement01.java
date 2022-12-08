import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2.Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "13265915248");

        // 3.Step: Create statement
        Statement st = con.createStatement();

        // 1.Example: Create a function which uses 2 parameters and return the sum of the parameters
        // 1st. Step: Type code to create function
        String sql1 = "create or replace function additionF(x numeric, y numeric) returns numeric language plpgsql as $$ begin return x+y; end $$";

        // 2nd Step: Execute the function
        st.execute(sql1);

        // 3rd Step: Prepare CallableStatement
        CallableStatement cst1 = con.prepareCall("{? = call additionF(?, ?)}");

        // 4th Step: Use registerOutParameter() method for result container, use set() method for parameters
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        // 5th Step: Use execute() method to get result for the specific values.
        cst1.execute();

        // 6th Step: To see the results on console use "sout"
        System.out.println(cst1.getObject(1));

        // 2.Example: Create a function which calculates the volume of cone
        // 1st. Step: Type code to create function
        String sql2 = "create or replace function volumeOfConeF(r numeric, h numeric) returns numeric language plpgsql as $$ begin return 3.14*r*r*h/3; end $$";

        // 2nd Step: Execute the function
        st.execute(sql2);

        // 3rd Step: Prepare CallableStatement
        CallableStatement cst2 = con.prepareCall("{? = call volumeOfConeF(?, ?)}");

        // 4th Step: Use registerOutParameter() method for result container, use set() method for parameters
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,2);
        cst2.setInt(3,5);

        // 5th Step: Use execute() method to get result for the specific values.
        cst2.execute();

        // 6th Step: To see the results on console use "sout"
        System.out.printf("%.2f", cst2.getObject(1));

        con.close();
        st.close();
        cst1.close();
        cst2.close();
    }
}
