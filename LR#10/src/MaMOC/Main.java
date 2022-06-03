package MaMOC;

import java.util.Scanner;

public class Main {

    private static int intDel(int number)
    {
        for(int index = 2; index < number/2; ++index)
            if(number % index == 0)
                return index;
        return number;
    }

    public static int remainderModulo(int a, int b, int p)
    {
        int result = (int)Math.pow(a,b);
        while((result > p || b != 1) && result != 1)
        {
           int divider = intDel(b);
           b = b/divider;
           result = (int)Math.pow(a,divider) % p;
           a = result;
        }
        return result;
    }

    public static Character[] Search(int n, int k)
    {
        Character[] result = new Character[30];
        int temp = 60 - remainderModulo(10,n-1,k);
        for(int index = 0; index < 28; ++index)
            result[index] = '0';

        String sTemp = Integer.toString(temp);
        result[28] = sTemp.charAt(0);
        result[29] = sTemp.charAt(1);
        if(n < 30)
            result[30 -n] = '1';
        return result;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Введите число a: ");
        int a = in.nextInt();
        System.out.print("Введите число b: ");
        int b = in.nextInt();
        System.out.println("Итоговое число " + a + "^" + b + ": " + Math.pow(a,b));
        System.out.print("Введите число, на которое хотите делить по модулю: ");
        int p = in.nextInt();
        System.out.println("Остаток от деления равен: " + remainderModulo(a,b,p));
        
        //Задача №2

        System.out.print("Введите n: ");
        int n = in.nextInt();
        Character[] result = Search(n,60);
        System.out.println("Последние 30 цифр числа длины n, кратного 3,4,5: ");
        for (Character character : result) System.out.print(character);

    }
}
