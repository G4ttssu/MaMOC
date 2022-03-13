package MaMOC;

public class Main {

    //Exercise №1

    final double a_0 = 1;
    final double a_n = 0;

    double calculationB_m(double m)
    {return (Math.pow(-1,m)+1)/(Math.PI + m);}

    double transformFourier(double x, int n)
    {
        double result = a_0;

        for(int i = 0; i < n; ++i)
            result += a_n * Math.cos(i * x) + calculationB_m(i)*Math.sin(i*x);

        return result;
    }

    double calculatePulse(double x)
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
    int calculateTestDischarge(String number)
    {
        int result = 0;
        int index = 0;
        while(index < number.length())
            result += Character.getNumericValue(number.charAt(index));
        return result % 2;
    }

    boolean isFindingError(String numberOne, String numberTwo)
    {
        return calculateTestDischarge(numberOne) == calculateTestDischarge(numberTwo);
    }

    public static void main(String[] args)
    {

    }
}
