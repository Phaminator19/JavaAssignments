/*=============================================================================
| Assignment: HW 03 - Implementing a Hash function
|
| Author: Quang Pham
| Language: Java
|
| To Compile: javac Hw03.java
|
| To Execute: java Hw03 filename
| where filename is in the current directory and contains
| commands to insert, search, delete, print & quit.
|
| Class: COP3503 - CS II Summer 2021
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hw03 {
    public static void complexityIndicator () {
        System.err.println("\nqu511289;1;1");
    }

    public int UCFxram (String d, int len) {
        //arbitrary value
        int randVal1 = 0xbcde98ef;
        int randVal2 = 0x7890face;

        //seed value
        int hashVal = 0xfa01bc96;

        // array d gets 4 byte blocks
        int roundedEnd = (len & 0xfffffffc);
        int i;
        int tempData;

        for (i = 0; i < roundedEnd; i = i + 4) {
            tempData = ((d.charAt(i) & 0xff) | ((d.charAt(i + 1) & 0xff) << 8) | ((d.charAt(i+2) & 0xff) << 16) | (d.charAt(i+3) << 24));
            tempData = tempData * randVal1;

            /* Rotate tempData left 12 bits
            Bit rotation is an operation similar to shift except that the bits that fall off
            at one end are put back to the other end.
            In left rotation, the bits that fall off at left end are put back at right end.
            In right rotation, the bits that fall off at right end are put back at eft end.
             */
            tempData = (tempData << 12) | (tempData >>> (32 - 12));

            tempData = tempData * randVal2;
            hashVal = hashVal ^ tempData;

            /*
            Rotate hashVal left by 13 bits
             */
            hashVal = (hashVal << 13) | (hashVal >>> (32 - 13));
            hashVal = hashVal * 5 + 0x46b6456e;
        }
            //Now collect orphan input characters
             tempData = 0;

            if ((len & 0x03) == 3) {
                tempData = (d.charAt(roundedEnd +2) & 0xff) << 16;
                len = len - 1;
            }

            if (((len & 0x03) == 2)) {
                tempData |= (d.charAt(roundedEnd + 1) & 0xff) << 8;
                len = len - 1;
            }

            if ((len & 0x03) == 1) {
                tempData |= (d.charAt(roundedEnd) & 0xff);
                tempData = tempData * randVal1; // multiply

                //Rotate tempData left 14 bits
                tempData = (tempData << 14) | (tempData >>> (32 - 14));

                tempData = tempData * randVal2;
                hashVal = hashVal ^ tempData;
            }

            hashVal = hashVal ^ len;
            hashVal &= 0xb6acbe58;
            hashVal = hashVal ^ (hashVal >>> 13);

            //another arbitrary value
            hashVal = hashVal * 0x53ea2b2c;
            hashVal = hashVal ^ (hashVal >>> 16);

            return hashVal; // return the 32 bit int hash
    }

    public static void main(String[] args) {
        complexityIndicator();
        String line_string;
        File inputFile = new File(args[0]);
        Hw03 ucf = new Hw03();
        try {
            Scanner scan = new Scanner(inputFile);
            while (scan.hasNext()) {
                line_string = scan.nextLine();
                int Hash_Value = ucf.UCFxram(line_string, line_string.length());
                System.out.format("%10x:%s\n", Hash_Value, line_string);
            }

            scan.close();
            System.out.println("Input file processed");

        } catch (FileNotFoundException err) {
            System.err.println("WARNING: The File is not existed. Please double check and confirmed that the file is correct");
        }

    }
}

/*=============================================================================
| I [Quang Pham] ([4238107]) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/