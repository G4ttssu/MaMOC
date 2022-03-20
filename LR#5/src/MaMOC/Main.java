package MaMOC;

public class Main {

    public static boolean checkEvenCode(String code)
    {
        return code.length() % 2 == 0;
    }

    public static int getSizeMatrix(String code)
    {
        int rows = 1;
        int diff = code.length();
        int drows = 0;
        while(rows <= code.length()/2)
        {
            if(code.length() % rows == 0)
            {
                if(Math.abs(code.length() / rows - rows) < diff)
                {
                    drows = rows;
                    diff = Math.abs(code.length() / rows - rows);
                }

            }
            if(rows == 1)
                rows++;
            else
                rows+=2;
        }
        return drows;
    }

    public static void doColumn(int[][] matrixCode, String code)
    {
        int index = 0;
        for(int i = 0; i < matrixCode.length - 1; ++i)
        {
            int contrBit = 0;
            for(int j = 0; j < matrixCode[i].length - 1; ++j)
            {
                matrixCode[i][j] = Character.getNumericValue(code.charAt(index + j));
                contrBit += matrixCode[i][j];
            }
            matrixCode[i][matrixCode[i].length - 1] = contrBit % 2;
            index += matrixCode[i].length - 1;
        }
    }

    public static void doRow(int[][] matrixCode)
    {
        for(int i = 0; i < matrixCode.length; ++i)
        {
            int contrBit = 0;
            for(int j = 0; j < matrixCode[i].length - 1; ++j)
                contrBit += matrixCode[j][i];
            matrixCode[matrixCode.length - 1][i] = contrBit % 2;
        }

    }

    public static void correctWrongInCode(int[][] matrixCode, int[][] wrongMatrixCode)
    {
        for(int i = 0; i<matrixCode[matrixCode.length - 1].length - 1; ++i)
            if(matrixCode[matrixCode.length-1][i] != wrongMatrixCode[wrongMatrixCode.length-1][i])
                System.out.println("Найдена ошибка в столбце " + (i+1));
        for(int index = 0; index < matrixCode.length - 1; ++index)
        {
            if(matrixCode[index][matrixCode.length-1] != wrongMatrixCode[index][matrixCode.length - 1])
            {
                boolean checkWrong = false;
                for(int j = 0; j < matrixCode.length - 1; ++j)
                {
                    if(matrixCode[matrixCode[index].length-1][j] != wrongMatrixCode[wrongMatrixCode[index].length - 1][j])
                    {
                        checkWrong = true;
                        System.out.println("Найдена исправимая ошибка в коде, неправильный элемент с индексами");
                        System.out.println("Строка " + (index + 1) + "\nСтолбец " + (j+1));
                        System.out.println("Неправильный элемент " + wrongMatrixCode[index][j]);
                        wrongMatrixCode[index][j] = (wrongMatrixCode[index][j] + 1)%2;
                        System.out.println("Исправленный новый элемент " + wrongMatrixCode[index][j]);
                    }
                }
                if(!checkWrong)
                    System.out.println("Найдена неустранимая ошибка в строке " + (index+1));
            }
        }

    }


    public static void main(String[] args) {
        String code = "1010101010111110";
        String wrongCode = "1010101010110110";
        if(checkEvenCode(code))
        {
            int rows = getSizeMatrix(code);
            int [][] matrixCode = new int[code.length()/rows + 1][rows + 1];

            doColumn(matrixCode,code);
            doRow(matrixCode);

            System.out.println("Кодовое слово: " + code);
            System.out.println("Закодированное слово в таблице: ");
            for (int[] ints : matrixCode) {
                for (int anInt : ints) System.out.print(anInt);
                System.out.println();
            }
            System.out.print("Закодированное слово: ");
            for (int[] ints : matrixCode) {
                for (int anInt : ints) System.out.print(anInt);
            }

            System.out.println();

            rows = getSizeMatrix(wrongCode);
            int[][] wrongMatrixCode = new int[wrongCode.length()/rows + 1][rows + 1];
            doColumn(wrongMatrixCode,wrongCode);
            doRow(wrongMatrixCode);

            System.out.println("Неправильное кодовое слово: " + wrongCode);
            System.out.println("Неправильное закодированное слово в таблице: ");
            for (int[] ints : wrongMatrixCode) {
                for (int anInt : ints) System.out.print(anInt);
                System.out.println();
            }
            System.out.print("Неправильное закодированное слово: ");
            for (int[] ints : wrongMatrixCode) {
                for (int anInt : ints) System.out.print(anInt);
            }

            System.out.println();
            correctWrongInCode(matrixCode,wrongMatrixCode);
        }
    }
}
