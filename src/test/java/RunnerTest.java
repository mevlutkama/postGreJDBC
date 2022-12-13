import jdk.jshell.execution.Util;

public class RunnerTest {

    public static void main(String[] args) {
        // 1.Step: Registration to the Driver
        // 2.Step: Create connection with database
        Utils.connectToDatabase("localhost", "Techpro", "postgres", "13265915248");

        // 3.Step: Create statement
        Utils.createStatement();

        // 4.Step: Execute the query
        Utils.createTable("school", "teachers varchar(50)", "name varchar(10)", "schoolId numeric(3)");

        Utils.insertIntoTable("school", "name 'John'", "teachers 'Mary Cury'");

        Utils.dropTable("school");

        // 5.Step: Close the connection and statement
        Utils.closeConnectionAndStatement();

    }
}
