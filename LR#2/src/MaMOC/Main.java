package MaMOC;

import java.util.Scanner;

public class Main {

    //Exercise №1


    static double calculationB_m(double m)
    {return (Math.pow(-1,m+1)+1)/(Math.PI*m);}

    static double transformFourier(double x, int n)
    {
        final double a_0 = 1;
        final double a_n = 0;
        double result = a_0/2;

        for(int i = 0; i < n; ++i)
            result += a_n * Math.cos(i * x) + calculationB_m(i)*Math.sin(i*x);

        return result;
    }

    static double calculatePulse(double x)
    {
        double x_rad = x % (2*Math.PI);
        if(x_rad < 0)
            x_rad += Math.PI;

        if(x_rad < Math.PI)
            return 1;
        else
            return 0;
    }

    //Exercise №2
    static int calculateTestDischarge(String number)
    {
        int result = 0;
        int index = 0;
        while(index < number.length()){
            result += Character.getNumericValue(number.charAt(index));
            index++;
        }
        return result % 2;
    }

    static boolean isFindingError(String numberOne, String numberTwo)
    {
        return calculateTestDischarge(numberOne) == calculateTestDischarge(numberTwo);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите x: ");
        double x = in.nextDouble();
        System.out.print("Введите m: ");
        int m = in.nextInt();
        System.out.println("Значение функции F: " + calculatePulse(x));
        System.out.println("Значение f(x): " + transformFourier(x,m));

        /*
        System.out.print("Введите двочиное значение: ");
        String numberOne = in.next();
        System.out.println("Проверочный бит: " + calculateTestDischarge(numberOne));
        System.out.print("Введите искаженное значение: ");
        String numberTwo = in.next();
        if(!isFindingError(numberOne,numberTwo))
            System.out.println("Удалось найти искажение при передаче");
        else
            System.out.println("Не удалось найти искажение при передаче");

         */
    }
}
