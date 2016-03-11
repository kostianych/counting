package by.tut.kostianych;

import java.io.*;

public class Main {

    static BufferedReader in = null;

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        double maxNumber = main.calculateMax(); //максимальное число в файле 2GB
        //maxNumber = 900000000
        // Каждое число отмечаем в соотвествующем бите единицей, например 5 - 00010000
        // Пока используем один integer 32 бита для простоты, для больших массивов нужно будет использовать массив integer
        // Для файла 2GB это займёт в памяти 900000000/8 = 107 MB
        int count1 = main.fillCountBits("array1.txt");
        int count2 = main.fillCountBits("array2.txt");
        int minLength = 0;
//        if (count1.length > count2.length) {
//            minLength = count2.length;
//        } else {
//            minLength = count1.length;
//        }

        PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
        for (int i = 0; i < minLength; i++) {
            // write number if exists in both files

            int result = count1 & count2;// result будет иметь бит 1 для чисел котрые есть в обоих файлах

//            if (result) {
//                writer.println(i + " ");
//
//            }
        }
        writer.close();


    }


    private int fillCountBits(String fileName) throws IOException {
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

        // count variable
        int one = 1, count = 0;
        in = null;

        while ( (num = readNext(fileName)) >= 0) {

            count = count & one << num; //сдвиг влево
        }

        //System.out.println();
        System.out.println("Count array: ");

//        for (int i = 0; i < count.length; i++) {
//            System.out.print(count[i] + " ");
//        }
//        System.out.println();
        return count;

    }

    public double calculateMax() {
        double size = 2 * Math.pow(1024, 3) ; //размер файла 2GB в байтах
        double sum = 10, i = 1, num = 0;
        // Вычисляем сколько цифр в файле 2GB
        // Для этого формируем ряд:
        // 10 одноразрядных чисел + 90 двуразрядных + 900 3-х разрядных ... n * 9 * 10^(n-1) - количество n разрядных
        // 1 символ в файле в кодировке ANSI занимает 1 байт
        // Цифры разделены между собой одним символом (например пробел)
        while (sum < size) {
            num = 9 * Math.pow(10, i);// - количество чисел разрядности i + 1
            sum += (i + 1) * num +  // - сколько байт занимают числа разрядности i + 1
              num;// учитываем пробелы
            i++;
        };
        return num;
    }

    private static int readNext(String fileName) throws IOException {
        if (in == null) {
            in = new BufferedReader(new FileReader(fileName), 1); // считываем по одному символу
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
