package by.tut.kostianych;

import java.io.*;

public class Main {

    static BufferedReader in = null;

    public static void main(String[] args) throws IOException {

        int[] count1 = makeCountArray("array1.txt");
        int[] count2 = makeCountArray("array2.txt");
        int minLength = 0;
        if (count1.length > count2.length) {
            minLength = count2.length;
        } else {
            minLength = count1.length;
        }

        PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
        for (int i = 0; i < minLength; i++) {
            // write number if exists in both files
            if (count1[i] > 0 && count2[i] > 0) {
                writer.println(i + " ");

            }
        }
        writer.close();


    }


    private static int[] makeCountArray(String fileName) throws IOException {
        System.out.println(fileName+ ":") ;
        //Determine maximum number
        in = null;
        int num, max = 0;
        while ( (num = readNext(fileName)) >= 0) {
            if (num > max) {
                max = num;
            }
            System.out.print(num + " ") ;


        }
        System.out.println();
        System.out.println("Maximum: " + max) ;

        // count array
        int[] count = new int[max + 1];
        in = null;

        while ( (num = readNext(fileName)) >= 0) {
            count[num] = count[num] + 1;
        }

        //System.out.println();
        System.out.println("Count array: ");

        for (int i = 0; i < count.length; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();
        return count;

    }

    private static int readNext(String fileName) throws IOException {
        if (in == null) {
            in = new BufferedReader(new FileReader(fileName), 1);
        }

        char c = (char) in.read();

        // end of line
        if (c == 65535) {
            return -1;
        }

        in.read(); // skip space

        return Integer.valueOf(new Character(c).toString());

    }

}
