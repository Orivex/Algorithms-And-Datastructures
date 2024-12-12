import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] graph = new int[n+1][n+1];

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int a, b;
            a = in.nextInt();
            b = in.nextInt();

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println(" ");
        }

    }

    static void dijkstra(int startV, int[][] graph) {

    }
}
