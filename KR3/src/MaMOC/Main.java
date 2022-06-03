package MaMOC;
import java.util.Scanner;

public class Main {


    public static boolean isSimple(int number){

        if(number < 2)
            return false;
        double middle = Math.sqrt(number);
        for(int divider = 2; divider <= middle; ++divider)
            if(number % divider == 0)
                return false;
        return true;
    }

    public static int exercise(int number, int p)
    {
        int result = 0;
        while((number * result) % p != 1)
            result += 1;
        return result;
    }

    public static void main(String[] args) {
        int number;
        int p;

        Scanner in = new Scanner(System.in);

        System.out.println("Ввидете данные для программы.");
        System.out.print("Введит элемент = ");
        number = in.nextInt();
        System.out.print("Введите простое число = ");
        p = in.nextInt();
        while(!isSimple(p)) {
            System.out.print("Введено не простое число, попробуйте еще раз. Введите простое число = ");
            p = in.nextInt();
        }

        System.out.print("Результат: ");
        System.out.println(exercise(number,p));

    }
}

