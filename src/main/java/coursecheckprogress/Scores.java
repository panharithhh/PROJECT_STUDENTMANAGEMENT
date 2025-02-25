package coursecheckprogress;
import java.util.*;
import java.sql.*;

class Students {
    private int student_id;
    private String full_name;

    public Students(int student_id, String full_name) {
        this.student_id = student_id;
        this.full_name = full_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getFull_name() {
        return full_name;
    }


}

public class Scores {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args)  throws SQLException {

        Scanner sc = new Scanner(System.in);

        double homework, midterm, final_score, project;

        System.out.println("Enter the score in order \n " +
                            "   1. homework \n  " +
                            "   2. midterms \n  " +
                            "   3. final    \n  " +
                            "   4. project  \n  ");

        double []  scores = new double[4];
        for(int i =0; i<4;  i++){
            scores[i] = sc.nextDouble();
        }

        homework = scores[0];
        midterm = scores[1];
        final_score = scores[2];
        project = scores[3];

        System.out.println("what is the student you want to add the score to");
        int student = sc.nextInt();
        Students studentObj = returnStudent(student);

        if(studentObj == null){
            System.out.println("invalid student_id");
        }else {
            String full_name = studentObj.getFull_name();
            int student_id = studentObj.getStudent_id();

            sendScores(full_name, student_id, homework, midterm, final_score, project);
        }
    }

    public static Students returnStudent(int student) throws SQLException{

        String sql = "select student_id, full_name from students where student_id = ? ";

        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,student);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){

            int student_id = rs.getInt("student_id");
            String name = rs.getString("full_name" );
            Students stud = new Students(student_id, name);
            return stud;

        }

        return null;
    }

    public static void sendScores (String full_name, int student_id, double homework, double midterm, double final_score, double project) throws SQLException{

        String sql = " insert into scores (student_id, full_name, homework, midterm, final,project) values(?,?,?,?,?,?)";


        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,student_id );
        stmt.setString(2,full_name );
        stmt.setDouble(3, homework);
        stmt.setDouble(4, midterm);
        stmt.setDouble(5, final_score);
        stmt.setDouble(6, project);

        stmt.executeUpdate();
    }
}