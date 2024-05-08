public class MultistageGraph {
    public static void main(String[] args) {
        int[][] adjMatrix = new int[7][7];

        //stage 1
        adjMatrix[1][2] = 3;
        adjMatrix[1][3] = 4;
        //stage 2
        adjMatrix[2][4] = 8;
        adjMatrix[2][5] = 4;
        adjMatrix[3][5] = 1;
        //stage 3
        adjMatrix[4][6] = 1;
        adjMatrix[5][6] = 2;
        //stage 4 is only 6 (so he connects to no vertex)

        int stages = 4;

        int[] cost = new int[adjMatrix.length];
        int[] pointers = new int[adjMatrix.length];

        cost[adjMatrix.length - 1] = 0;

        for (int i = adjMatrix.length-2; i >= 1; i--) {
            System.out.println("Checking the min cost for " + i);
            int min = Integer.MAX_VALUE;

            for (int j = i+1; j < adjMatrix.length; j++) {
                //connected?                //smaller?
                if(adjMatrix[i][j] != 0 && adjMatrix[i][j] + cost[j] < min) {
                    min = adjMatrix[i][j] + cost[j];
                    //save the min path
                    pointers[i] = j;

                    System.out.println("New min cost for " + i + ". It is " + min + ". \n Now " + i + " points to " + j);
                }
            }

            System.out.println("------");
            System.out.println("End min cost for " + i + ". It is " + min + ". \n Now " + i + " points to " + pointers[i]);
            System.out.println("------");
            cost[i] = min;
        }

        System.out.println("-------------------------------------");
        System.out.println("Finished");

        int[] path = new int[stages+1];
        path[stages] = adjMatrix.length-1;
        path[1] = 1;

        for (int i = 2; i < stages; i++) {
            path[i] = pointers[path[i-1]];
        }

        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
    }

}
