package coursecheckprogress;

public class scoreCalculation {
    public static void main(String[] args) {

        double[] array= {80,70,100,90};

        double totalScore = calculateTotalScore(array);

        System.out.println(totalScore);
    }

    static double calculateTotalScore(double[] array){
        double totalScore = array[0]*20/100 + array[1]*20/100 + array[2]*30/100 + array[3]*30/100;
        return totalScore;
    }


}
