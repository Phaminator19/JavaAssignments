import java.util.Arrays;

public class Encrypter {

    private static String Encrypt(String number) {
        int[] NumberArrays = new int[4];


        for (int i = 0; i < 4; i++){
            char str = number.charAt(i);
            NumberArrays[i] = Character.getNumericValue(str);
            /* this loop will take the user string and convert into a sequence of integers */
        }

        int[] temp = new int[4];
        int[] swap = new int[2];

        temp[0] = (NumberArrays[0]+7)%10;
        temp[1] = (NumberArrays[1]+7)%10;
        temp[2] = (NumberArrays[2]+7)%10;
        temp[3] = (NumberArrays[3]+7)%10;

        swap[0] = temp[0];
        temp[0] = temp[2];
        temp[2] = swap[0];

        swap[1] = temp[1];
        temp[1] = temp[3];
        temp[3] = swap[1];

        return Arrays.toString(temp);
    }
}
























