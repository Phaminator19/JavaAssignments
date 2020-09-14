import java.util.Arrays;

public class Decrypter {

    private static String Decrypt(String number) {
        int[] NumberArray = new int[4];
        int[] temp = new int[4];
        int QuotientNumber;


        for (int i = 0; i < 4; i++) {
            char str = number.charAt(i);
            NumberArray[i] = Character.getNumericValue(str);
            QuotientNumber = SwitchCaseNumbers(NumberArray[i]);
            temp[i] = (QuotientNumber%10);
        }

        int[] swap = new int[2];

        swap[0] = temp[0];
        temp[0] = temp[2];
        temp[2] = swap[0];

        swap[1] = temp[1];
        temp[1] = temp[3];
        temp[3] = swap[1];


        return Arrays.toString(temp);
    }


    private static int SwitchCaseNumbers (int QuotientNumbers) {
        return switch (QuotientNumbers) {
            case 9 -> 2;
            case 8 -> 1;
            case 7 -> 0;
            case 6 -> 9;
            case 5 -> 8;
            case 4 -> 7;
            case 3 -> 6;
            case 2 -> 5;
            case 1 -> 4;
            case 0 -> 3;
            default -> QuotientNumbers;
        };
    }
}


