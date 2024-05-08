public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] matrixA = new int[2][2];
        int[][] matrixB = new int[2][2];

        matrixA[0][0] = 100;//
        matrixA[0][1] = 2;//     (100  2)
        matrixA[1][0] = 4;//     (4  2)
        matrixA[1][1] = 2;//

        matrixB[0][0] = 5;//
        matrixB[0][1] = 4;//           (5  4)
        matrixB[1][0] = 6;//           (6  2)
        matrixB[1][1] = 2;//


        int[][] matrix = badApproach(matrixA, matrixB);
        System.out.println(matrix[0][0]);
    }

    public static int[][] badApproach(int[][] matrixA, int[][] matrixB) {
        int allRowsA = matrixA.length;
        int allColumnsB = matrixB[0].length;
        int allColumnsA_or_allRowsB = matrixA[0].length;

        int[][] newMatrix = new int[allRowsA][allColumnsB];

        for (int rowA = 0; rowA < allRowsA; rowA++) {
            for (int columnB = 0; columnB < allColumnsB; columnB++) {
                for (int columnA_and_rowB = 0; columnA_and_rowB < allColumnsA_or_allRowsB; columnA_and_rowB++) {
                    newMatrix[rowA][columnB] += matrixA[rowA][columnA_and_rowB] * matrixB[columnA_and_rowB][columnB];
                }
            }
        }

        return newMatrix;
    }
}
