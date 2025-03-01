package coursecheckprogress;

import org.apache.tomcat.util.net.jsse.JSSEUtil;

public class test {

    public static void main(String[] args) {

        double[] array = {10,4,3,2};

        double[] x = findMaxNum(array);

        for(double j : x){
            System.out.println(j);
        }
    }

    public static double[] findMaxNum(double[] array){

        double maxNum = 0;
        double percentageArray[] = new double[4];
        double attendancePer;

        for(int i =0; i <array.length; i++){
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

}
