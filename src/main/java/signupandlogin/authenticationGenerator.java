package signupandlogin;

import java.util.Random;

public class authenticationGenerator {

    public static String generateCode() {
         int passwordArray[] = new int[6];

        Random r = new Random();
        for(int i =0; i<6 ; i++) {
            passwordArray[i]= r.nextInt(0,9);
        }

        StringBuilder sb = new StringBuilder();
        for(int k : passwordArray) {
            sb.append(k); // special array when u use sb.append
        }

        return sb.toString();

    }

}

