package MaMOC;

public class Main {

    public static int[] multiplication(int[] a, int[] b)
    {
        int[] res = new int[a.length + b.length + 1];
        for(int index = 0; index < a.length; ++index)
            for(int jndex = 0; jndex < b.length; ++jndex)
                res[index + jndex] += a[index] + b[jndex];
        for(int index = 0; index < res.length; ++index)
        {
            if(res[index] % 2 == 0)
                res[index] = 0;
            else
                res[index] = 1;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] generatePplynome = {1,0,1,1};
        String[] array = new String[16];

        for(int index = 0; index < 16; ++index)
        {
            array[index] = Integer.toBinaryString(index);
            while(array[index].length() < 4)
                array[index] = '0' + array[index];
        }

        int[] word = new int[4];
        int[] temp = new int[7];

        System.out.println("Проивзодящий полином: g(x) = x^3 + x + 1 ");
        System.out.println("Кодирование несистематическим методом: ");
        for(int index = 0; index < array.length; ++index)
        {
            for(int jndex = 0; jndex < word.length; ++jndex)
                word[jndex] = array[index].charAt(jndex);
            temp = multiplication(word,generatePplynome);

            System.out.print(index + ": ");
            for(int jndex = 0; jndex < temp.length; ++jndex)
                System.out.print(temp[jndex]);
            System.out.println();
        }
    }
}
