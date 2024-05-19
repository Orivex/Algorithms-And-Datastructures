import java.util.Scanner;

public class BinaryExponentation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        int result = 1;

        while (b > 0) {
            if(b%2==1)
                result *= a;
            a *= a;
            b/=2;
        }

        System.out.println(result);
    }
}
