package coursecheckprogress;
import java.util.*;
import java.sql.*;

public class Scores {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args)  throws SQLException {

        Scanner sc = new Scanner(System.in);

        double homework, midterm, final_score, project;

        System.out.println("Enter the score in order \n " +
                            "1. homework \n" +
                            "2. midterms \n" +
                            "3. final \n" +
                            "4. project \n");

        double []  scores = new double[4];
        for(int i =0; i<4;  i++){
            scores[i] = sc.nextDouble();
        }

        homework = scores[0];
        midterm = scores[1];
        final_score = scores[2];
        project = scores[3];

        sendScores(homework, midterm, final_score, project);

    }

//    public static String[] retreiveData (){
//
//    }

    public static void sendScores (double homework, double midterm, double final_score, double project) throws SQLException{

        String sql = " insert into scores (homework, midterm, final,project) values(?,?,?,?)";

        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setDouble(1, homework);
        stmt.setDouble(2, midterm);
        stmt.setDouble(3, final_score);
        stmt.setDouble(4,project);

        stmt.executeUpdate();
    }
}
