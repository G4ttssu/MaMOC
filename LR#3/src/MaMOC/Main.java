package MaMOC;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static String coding(String number, String key)
    {
        StringBuilder code = new StringBuilder();
        for(int index = 0; index < number.length(); ++index)
            code.append((Character.getNumericValue(number.charAt(index)) + Character.getNumericValue(key.charAt(index))) % 2);
        return code.toString();
    }

    static String decoding(String code, String key)
    {
        StringBuilder result = new StringBuilder();
        for(int index = 0; index < code.length(); ++index )
            result.append((Character.getNumericValue(code.charAt(index)) + Character.getNumericValue(key.charAt(index))) % 2);
        return result.toString();
    }

    static String getKey(int length)
    {
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < length; ++i)
            key.append(random.nextInt(2));
        return key.toString();
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите двоичный код, который нужно закодировать: ");
        String a = in.next();
        String key = getKey(a.length());
        System.out.println("Ключ:" + key);
        System.out.println("Исходно слово:" + a);
        String b = coding(a,key);
        System.out.println("Закодированное слово:" + b);
        System.out.println("Полученное слово:" + decoding(b,key));
    }
}
