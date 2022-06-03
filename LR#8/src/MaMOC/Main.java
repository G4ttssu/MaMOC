package MaMOC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите порядок: ");
        int number = in.nextInt();

        int[] array = new int[number];
        System.out.println("Введите " + number + " элементы перестановки.");
        for(int index = 0; index < number; ++index)
        {
            System.out.print("Введите " + (index + 1) + " элемент: ");
            array[index] = in.nextInt();
        }

        System.out.println("Итоговая подстановка: ");
        for(int index = 0; index < number; ++index)
            System.out.print((index + 1) + " ");
        System.out.println();
        for(int index = 0; index < number; ++index)
            System.out.print(array[index] + " ");

        int count = 0;
        for(int i = 0; i < number - 1; ++i)
            for(int k = i+1; k < number; ++k)
                if(array[k] - i < 0)
                    count++;

        System.out.println();
        if(count % 2 == 0)
            System.out.println("Подстановка четная");
        else
            System.out.println("Подстановка нечетная");
        }
    }
