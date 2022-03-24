package MaMOC;

import java.util.Scanner;

public class Main {

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

    public static double defineShortDistance(String[][] dictionary)
    {
        double shortDistance = Double.POSITIVE_INFINITY;
        for(int index = 0; index < dictionary.length; ++index)
            for(int j = index+1; j < dictionary.length; ++j)
            {
                double tempDistance = 0;
                for(int k = 0; k < dictionary[index][1].length(); ++k)
                {
                    if(dictionary[index][1].charAt(k) != dictionary[j][1].charAt(k))
                        tempDistance++;
                }
                if(tempDistance < shortDistance)
                    shortDistance = tempDistance;

            }
        return shortDistance;
    }

    public static void addBits(String[][] dictionary)
    {
        int maxBits = 0;
        for (String[] strings : dictionary)
            if (strings[1].length() > maxBits)
                maxBits = strings[1].length();

        for(int index = 0; index < dictionary.length; ++index)
             if(dictionary[index][1].length() != maxBits)
                 while (dictionary[index][1].length() < maxBits)
                     dictionary[index][1] = "0" + dictionary[index][1];
    }

    public static void main(String[] args) {

        //Ввод своеей таблицы
        Scanner input = new Scanner(System.in);

        System.out.print("Введите количество символов, которые хотитет внести в таблицу кодов: ");
        int number = input.nextInt();

        String[][] dictionary = new String[number][2];

        for(int index = 0; index < number; ++index)
        {
            System.out.print("Введите " + (index + 1) + " символ: ");
            dictionary[index][0] = input.next();
            System.out.print("Введите код данного символа: ");
            dictionary[index][1] = input.next();
        }

        addBits(dictionary);

        System.out.println("Кодовый словарь: ");
        for (String[] strings : dictionary) {
            for (String string : strings) System.out.print(" " + string);
            System.out.println();
        }

        System.out.println("Минимально кодовое расстояние: " + defineShortDistance(dictionary));

        //Ввод своего текста и кодирование равномерным кодом


        System.out.print("Введите текст: ");
        String text = input.next();

        String a = createStringWithUniqueSymbols(text);
        dictionary = createDictionary(a);
        for (String[] strings : dictionary) {
            for (String string : strings) System.out.print(" " + string);
            System.out.println();
        }
        System.out.println("Минимально кодовое расстояние: " + defineShortDistance(dictionary));

    }
}
