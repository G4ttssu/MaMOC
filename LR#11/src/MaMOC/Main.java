package MaMOC;

import java.util.Scanner;

public class Main {

    public static int[] multiplication(int[] a, int[] b, int p)
    {
        int[] res = new int[a.length + b.length + 1];
        for(int index = 0; index < a.length; ++index)
            for(int jndex = 0; jndex < b.length; ++jndex)
                res[index + jndex] += a[index] * b[jndex];

        return res;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Введите p: ");
        int p = in.nextInt();
        System.out.print("Введите порядок первого полинома: ");
        int n_1 = in.nextInt();
        System.out.print("Введите порядок второго полинома: ");
        int n_2 = in.nextInt();

        int[] polynomialOne = new int[n_1 + 1];
        int[] polynomiakTwo = new int[n_2 + 1];
        int[] result = new int[n_1 + n_2 + 1];

        System.out.println("Введите коэффициенты первого полинома: ");
        for(int index = 0; index < polynomialOne.length; ++index)
        {
            System.out.print("Коэффициент " + index + "-го порядка: ");
            polynomialOne[index] = in.nextInt();
        }

        System.out.println("Введите коэффициенты второго полинома: ");
        for(int index = 0; index < polynomiakTwo.length; ++index)
        {
            System.out.print("Коэффициент " + index + "-го порядка: ");
            polynomiakTwo[index] = in.nextInt();
        }

        System.out.println("Результат перемножения над полем Z" + p + ": ");
        for(int index = 0; index <= n_1; ++index)
            for(int jndex = 0; jndex <= n_2; ++jndex)
                result[index + jndex] += polynomialOne[index]* polynomiakTwo[jndex];
        boolean check = false;
        for(int element: result)
            if(element != 0)
                check = true;
        if(check)
            for(int index = 0; index < result.length; ++index)
            {
                if(result[index] % p != 0)
                {
                    System.out.print(result[index] % p + "x^" + index);
                    if(index != result.length - 1)
                        System.out.print("+");
                }
                else
                    System.out.print('0');
            }
    }
}
