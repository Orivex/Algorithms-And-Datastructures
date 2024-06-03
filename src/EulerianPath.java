import java.util.ArrayList;
import java.util.List;

public class EulerianPath {
    public static void main(String[] args) {

        int V = 7; //Number of vertices

        int[][] adjMatrix = new int[V][V];

        connect(adjMatrix, 1, 2);
        connect(adjMatrix, 1, 3);
        connect(adjMatrix, 2, 2);
        connect(adjMatrix, 2, 4);
        connect(adjMatrix, 2, 4);
        connect(adjMatrix, 3, 1);
        connect(adjMatrix, 3, 2);
        connect(adjMatrix, 3, 5);
        connect(adjMatrix, 4, 3);
        connect(adjMatrix, 4, 6);
        connect(adjMatrix, 5, 6);
        connect(adjMatrix, 6, 3);

        int[] inDegrees = new int[V];
        int[] outDegrees = new int[V];
        boolean atMostForOneVertex_1 = false; // Look in your notes
        boolean atMostForOneVertex_2 = false; // Look in your notes

        for (int i = 0; i < V; i++) {
            int outConnections = 0;
            int inConnections = 0;
            for (int j = 0; j < V; j++) {
                outConnections += adjMatrix[i][j];
                inConnections += adjMatrix[j][i];
            }
            outDegrees[i] = outConnections;
            inDegrees[i] = inConnections;

            int difference1 = outDegrees[i] - inDegrees[i];

            if(difference1 == 1 && !atMostForOneVertex_1)
                atMostForOneVertex_1 = true;
            else if(difference1 == 1)
                System.exit(0);

            int difference2 = inDegrees[i] - outDegrees[i];

            if(difference2 == 1 && !atMostForOneVertex_2)
                atMostForOneVertex_2 = true;
            else if(difference2 == 1)
                System.exit(0);

        }

        System.out.println("Eulerian path exists (～￣▽￣)～");


    }

    //static int[] solution = new int[V];
    static void dfs(int[][] adjMatrix, int[] outDegrees, int[][] visited, int currentV) {
        if(outDegrees[currentV] == 0) {
            //Backtrack
        }

        for (int i = 0; i < adjMatrix[currentV].length; i++) {
            if(adjMatrix[currentV][i] >= 1 && visited[currentV][i] == 0) {
                visited[currentV][i] = 1;
                dfs(adjMatrix, outDegrees, visited, i);
                return;
            }
        }

    }

    //Implement tomorrow
    //int findStartVertex() {
    //
    //}

    static void connect(int[][] adjMatrix, int v1, int v2) {
        adjMatrix[v1][v2] += 1;
    }

    static void printAdjMatrix(int[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.println("Vertex " + i + " is connected to:");

            for (int j = 0; j < adjMatrix[i].length; j++) {
                if(adjMatrix[i][j] >= 1) {
                    System.out.println(j + ": " + adjMatrix[i][j] + " time(s)");
                }
            }

            System.out.println("-------------");
        }
    }
}
