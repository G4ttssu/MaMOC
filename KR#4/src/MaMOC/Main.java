package MaMOC;

public class Main {

    public static String decToBin(int digit, int size)
    {
        String result = Integer.toBinaryString(digit);
        while(result.length() < size)
            result += '0' + result;
        return result;
    }

    public static int[][] fillMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; ++i)
        {
            String bin = decToBin(i, matrix.length);
            for(int j = 0; j < matrix.length; ++j)
            {
                if(bin.charAt(j) == '1')
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static int[] mult(int[] A, int[] G)
    {
        int length = A.length + G.length - 1;
        int[] result = new int[length];
        for(int i = 0; i < A.length; ++i)
            for(int j = 0; j < G.length; ++j)
                result[i + j] += A[i] * G[j];
        for(int i = 0; i < length; ++i)
        {
            if(result[i] % 2 == 1)
                result[i] = 0;
            else
                result[i] = 1;
        }
        return result;
    }

    public static void outMatrix(int[][] matrix)
    {
        System.out.println("Вывод матрицы:");
        for (int[] ints : matrix) {
            for (int anInt : ints) System.out.print(anInt + " ");
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] words = new int[4][(int)Math.pow(2,4)];
        fillMatrix(words);
        final int[] G = {1,1,0,1};

        System.out.println("Проивзодящий полином: g(x) = x^3 + x + 1");
        System.out.println("Кодиррование несистематическим методом: ");

        for(int i = 0; i < (int)Math.pow(2,4); ++i)
        {
            int[] A =  new int[4];
            for(int j = 0; j < 4; ++j)
                A[j] = words[i][j];
            int length = 4 + 4 - 1;
            int[] result = mult(A,G);
            for(int  k = 0; k < 4; ++k)
                System.out.print(A[k]);
            System.out.print(" : ");
            for(int k = 0; k < length; ++k)
                System.out.print(result[k]);
            System.out.println();
        }
    }
}
