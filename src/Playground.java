import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Playground {
    public static void main(String[] args) {
        int a = 18;
        int b = 4;
        int rest = 0;
        rest = a % b;
        a = a / b;
        System.out.println(a + " " + rest);
    }

    public static void modulo_fun() {
        int number = 10;
        while (number > 0) {
            System.out.println(number);
            System.out.println( number % 10);
            number = number / 10;
        }

        System.out.println((1234 / 10) / 10);
    }
}
