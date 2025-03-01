package coursecheckprogress;

import java.sql.*;
import java.util.*;

public class progress {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) throws SQLException {

        int[] StudentID = returnStudentID() ;
        String[] StudentName = returnName();
        double[] Score =  returnStudentScore();
        double[] attendance = returnAttendance();


        clear();
         for(int i = 0; i< StudentID.length ; i ++ ){
             String student_name = StudentName[i];
             int student_id = StudentID[i];
             double student_score = Score[i];
             double student_atten = attendance[i];
             sendDataToProgress(student_id, student_name, student_score, student_atten);
         }

    }

    static void clear() throws SQLException{
        String sql = " truncate progress";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.executeUpdate();
    }

    static void sendDataToProgress(int studentId, String studentName, double studentScore, double studentAtten) throws SQLException {

        String sql = " insert into progress(student_id, student_name,student_score,student_atten) values(?,?,?,?)";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, studentId);
        stmt.setString(2, studentName);
        stmt.setDouble(3, studentScore);
        stmt.setDouble(4, studentAtten);

        stmt.executeUpdate();
    }

    static double[] returnStudentScore() throws SQLException {
        double[] Array = new double[1000000];

        String sqlScore = "select homework, midterm, final, project from scores";


        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sqlScore);

        ResultSet rs = stmt.executeQuery();
        int i = 0;
        while (rs.next()) {

            double homework = rs.getDouble("homework");
            double midterm = rs.getDouble("midterm");
            double finalScpre = rs.getDouble("final");
            double project = rs.getDouble("project");

            double totalScore = homework * 20 / 100 + midterm * 20 / 100 + finalScpre * 30 / 100 + project * 30 / 100;

            Array[i] = totalScore;

            i++;

        }

        return Array;
    }

    static double[] returnAttendance() throws SQLException {
        double[] array = new double[100];

        String sqlAttendance = " select attendance from attendance";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sqlAttendance);

        ResultSet rs = stmt.executeQuery();
        int o = 0;
        while (rs.next()) {

            double attendance = rs.getDouble("attendance");
            array[o] = attendance;
            o++;

        }

        double maxNum = 0;
        double percentageArray[] = new double[10000];
        double attendancePer;

        for(int i = 0; i < array.length; i++){
            if(array[i] > maxNum){
                maxNum = array[i];
            }
        }

        for(int i =0; i<array.length; i++){
            attendancePer = array[i]/maxNum*100;
            percentageArray[i] = attendancePer;
        }
        return percentageArray;
    }

    static int[] returnStudentID () throws SQLException{

        int[] Array = new int[1000];

        String sql = "select (student_id) from students";


        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        int i = 0;

        while (rs.next()) {


            int student = rs.getInt("student_id");
            Array[i] = student;
            i++;

        }

        return Array;
    }

    static String[] returnName () throws SQLException{
       String[] Array = new String[1000000];

       String sql = " select (full_name) from students";
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        int i = 0;

        while (rs.next()) {

            String name = rs.getString("full_name");
            Array[i] = name;
            i++;

        }

        return Array;
    }

}
