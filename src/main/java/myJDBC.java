import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class myJDBC {

    public static void main(String[] args) {

        String password = System.getenv("DB_PASSWORD");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from firstone");
            System.out.println("id  " +"names" );

            while (resultSet.next()) {
                System.out.print(resultSet.getString("id"));
                System.out.print("   ");
                System.out.println(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
