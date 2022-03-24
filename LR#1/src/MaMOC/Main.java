package MaMOC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double green = 0;
        double red = 0;
        double yellow = 0;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Введите время работы красного сигнала: ");
            try {
                red = Integer.parseInt(input.nextLine());
                if(red <= 0)
                    throw new Exception("Время не может быть меньше 0");
                if(red > 1000)
                    throw new Exception("Не больше 1000");
            }
            catch (Exception exception) {
                System.out.println("Неверный формат ввода. Причина: ");
                System.out.println(exception.getMessage());
            }
        }while(red <=0 || red > 1000);

        do {
            System.out.println("Введите время работы жёлтого сигнала: ");
            try {
                yellow = input.nextInt();
                if(yellow <= 0)
                    throw new Exception("Время не может быть меньше 0");
                if(yellow > 1000)
                    throw new Exception("Не больше 1000");
            }
            catch (Exception exception) {
                System.out.println("Неверный формат ввода. Причина: ");
                System.out.println(exception.getMessage());
            }
        }while(yellow <=0 || yellow > 1000);

        do {
            System.out.println("Введите время работы зелёного сигнала: ");
            try {
                green = input.nextInt();
                if(green <= 0)
                    throw new Exception("Время не может быть меньше 0");
                if(green > 1000)
                    throw new Exception("Не больше 1000");
            }
            catch (Exception exception) {
                System.out.println("Неверный формат ввода. Причина: ");
                System.out.println(exception.getMessage());
            }
        }while(green <=0 || green > 1000);

        System.out.println("Количество информации, что загорелся красный или жёлтый сигнал светофора: ");
        System.out.println(Math.ceil(log2((green + yellow + red)/(red + yellow))) + " Бит");
        System.out.println("Среднее количество информации о том, что загорелся любой из трёх сигналов: ");
        System.out.println(Math.ceil(red/(green + yellow + red)*log2((green + yellow + red)/red) +
                (yellow/(green + yellow + red))*log2((green + yellow + red)/yellow) +
                (green/(green + yellow + red))*log2((green + yellow + red)/green)) + " Бит");


        String text = "Hello world";
        String unqieText = createStringWithUniqueSymbols(text);
        String[][] dictionary = createDictionary(unqieText);
        System.out.println("Список закодированнных символов:");
        for (String[] strings : dictionary) {
            for (String string : strings) System.out.print(" " + string);
            System.out.println();
        }
        System.out.println("Закодированная строка " + createCode(dictionary,text));
    }

    public static double log2(double number)
    {
        final double base = Math.log(2);
        return (Math.log(number)/base);
    }

    public static String createStringWithUniqueSymbols(String text)
    {
        StringBuilder unique = new StringBuilder();
        for(int index = 0; index < text.length(); ++index)
            if(unique.toString().indexOf(text.charAt(index)) == -1)
                unique.append(text.charAt(index));
        return unique.toString();
    }


    public static String[][] createDictionary(String stringWithUniqueSymbols)
    {
        int bit = (int)Math.round(log2(stringWithUniqueSymbols.length())+0.5);
        String[][] dictionary = new String[stringWithUniqueSymbols.length()][2];
        for(int index = 0; index < stringWithUniqueSymbols.length(); ++index)
        {
            dictionary[index][0] =  Character.toString(stringWithUniqueSymbols.charAt(index));
            StringBuilder binaryCode = new StringBuilder(Integer.toBinaryString(index + 1));
            if(binaryCode.length() < bit)
                while(binaryCode.length() != bit)
                    binaryCode.insert(0, "0");
            dictionary[index][1] = binaryCode.toString();
        }
        return dictionary;
    }

    public static String createCode(String[][] dictionary, String text)
    {
        StringBuilder code = new StringBuilder();
        for(int index = 0; index < text.length(); ++index)
        {
            String symbol = Character.toString(text.charAt(index));
            int j = 0;
            while(j < dictionary.length)
            {
                if(symbol.equals(dictionary[j][0]))
                    break;
                j++;
            }
            code.append(dictionary[j][1]);
        }
        return code.toString();
    }
}
