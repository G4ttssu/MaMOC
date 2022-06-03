package MaMOC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Введите кол-во строк: ");
        int rows = in.nextInt();
        System.out.print("Введите кол-во столбцов: ");
        int columns = in.nextInt();

        System.out.println("Введите элементы матрицы кодовых слов: ");

        int[][] adjacentClass = new int[rows][columns];
        for(int index  = 0; index < rows; ++index)
            for(int jndex = 0; jndex < rows; ++jndex)
            {
                System.out.print("[" + index + "][" + jndex + "]: ");
                adjacentClass[index][jndex] = in.nextInt();
            }
        System.out.println("Введите некодовое слово: ");
        int[] vector = new int[rows];
        for(int index = 0; index < rows; ++index)
        {
            System.out.print("[" + index + "]: ");
            vector[index] = in.nextInt();
        }
        System.out.println("Смежный класс: ");
        for(int index = 0; index < rows; ++index)
        {
            for(int jndex = 0; jndex < columns; ++jndex)
                System.out.print(adjacentClass[index][jndex]);
            System.out.println();
        }

        int minimum = rows;

        for(int index = 0; index < rows; ++index)
        {
            int summary = 0;
            for(int jndex = 0; jndex < columns; ++jndex)
                summary += adjacentClass[index][jndex];
            if(minimum > summary)
                minimum = summary;
        }

        System.out.println("Лидеры класса: ");
        for(int index = 0; index < rows; ++index)
        {
            int summary = 0;
            for(int jndex = 0; jndex < columns; ++jndex)
                summary += adjacentClass[index][jndex];
            if(summary == minimum)
                for(int jndex = 0; jndex < columns; ++jndex)
                    System.out.print(adjacentClass[index][jndex]);
            System.out.println();
        }

    }
}
