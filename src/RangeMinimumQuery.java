import java.util.Scanner;

public class RangeMinimumQuery {
    public static void main(String[] args) {
        RangeMinimumQuery rmq = new RangeMinimumQuery();

        rmq.execute();
    }

    void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Size of array");
        int n = scanner.nextInt();
        int[][] m = new int[n][log2(n)];

        for (int i = 0; i < n; i++) {
            System.out.println("Element " + i);
            m[i][0] = scanner.nextInt();
        }

        for (int j = 1; j < log2(n); j++) {
            for (int i = 0; i + Math.pow(2, j) - 1 < n; i++) {
                m[i][j] = Math.min(m[i][j-1], m[i+(int)Math.pow(2, j-1)][j-1]);
            }
        }

        System.out.println("Range from ...");
        int L = scanner.nextInt();
        System.out.println("... to ...");
        int R = scanner.nextInt();
        int length = R - L + 1;
        int j = log2(length);

        System.out.println(Math.min(m[L][j-1], m[R-(int)Math.pow(2, j-1)+1][j-1]));
    }


    int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
