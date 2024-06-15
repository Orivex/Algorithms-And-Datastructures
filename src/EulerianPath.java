import java.util.ArrayList;
import java.util.List;

public class EulerianPath {
    public static void main(String[] args) {

        int V = 7; //Number of vertices

        int[][] adjMatrix = new int[V][V];

        connect(adjMatrix, 0, 0);
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
        int startV = 0;
        int endV = 0;

        for (int i = 0; i < V; i++) {
            int outConnections = 0;
            int inConnections = 0;
            for (int j = 0; j < V; j++) {
                outConnections += adjMatrix[i][j];
                inConnections += adjMatrix[j][i];
            }
            outDegrees[i] = outConnections;
            inDegrees[i] = inConnections;

            if(outConnections-inConnections > 1 || inConnections-outConnections > 1) {
                System.exit(0);
            }
            else if(outConnections-inConnections == 1) {
                startV++;
            } else if (inConnections-outConnections == 1) {
                endV++;
            }
        }

        if(startV == 0 && endV == 0 || startV == 1 && endV == 1) {
            System.out.println("Eulerian path exists (～￣▽￣)～");
        }
        else {
            System.exit(0);
        }

        System.out.println("First: "+findStartVertex(outDegrees, inDegrees));

        int[][] visited = new int[V][V];
        dfs(adjMatrix, outDegrees, visited, findStartVertex(outDegrees, inDegrees));

        if(getEdgesCount(adjMatrix) == path.size()-1) {
            System.out.println("Path:");
            for(int i : path) {
                System.out.print(i + " ");
            }
        }
        else {
            System.out.println("Disconnected graph!");
        }
    }

    static List<Integer> path = new ArrayList<>();

    static void dfs(int[][] adjMatrix, int[] outDegrees, int[][] visited, int currentV) {

        while (outDegrees[currentV] != 0) {

            int next = getNextNeighbor(adjMatrix, visited, currentV);

            if(next == -1) {
                return;
            }

            visited[currentV][next]++;
            outDegrees[currentV]--;

            dfs(adjMatrix, outDegrees, visited, next);
        }

        path.add(0, currentV);

    }

    static int getNextNeighbor(int[][] adjMatrix, int[][] visited, int currentV) {
        for (int i = 0; i < adjMatrix.length; i++) {
            if(adjMatrix[currentV][i] > 0 && visited[currentV][i] < adjMatrix[currentV][i]) {
                return i;
            }
        }

        return -1;
    }

    static int findStartVertex(int[] out, int[] in) {
        int start = 0;

        for (int i = 0; i < out.length; i++) { //out.length = in.length = V
            if(out[i] - in[i] == 1) {
                return i;
            }

            if(out[i] > 0) { //Prevents using single vertices (Vertex with no edges at all) to be used as the startV
                start = i;
            }
        }

        return start;
    }

    static int getEdgesCount(int[][] adjMatrix) {
        int edges = 0;

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if(adjMatrix[i][j] > 0)
                    edges++;
            }
        }

        return edges;
    }

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
