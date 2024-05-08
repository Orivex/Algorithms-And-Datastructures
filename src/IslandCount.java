import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IslandCount {
    public static void main(String[] args) {
        int[][] grid = {new int[]{1, 1, 1, 0}, new int[]{0, 0, 1, 1}, new int[]{1, 0, 0, 1}, new int[]{1, 1, 1, 1}};

        System.out.println(smallestIsland(grid));
    }

    private static boolean explore(int[][] grid, int[] pos) {

        int row = pos[0];
        int column = pos[1];

        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean columnInbounds = 0 <= column && column < grid[0].length;

        if(!rowInbounds || !columnInbounds)  {
            return false;
        }

        if(grid[row][column] == 0) {
            return false;
        }

        grid[row][column] = 0;

        explore(grid, new int[]{row-1, column});
        explore(grid, new int[]{row+1, column});
        explore(grid, new int[]{row, column-1});
        explore(grid, new int[]{row, column+1});

        return true;


    }

    public static int countIslands(int[][] grid) {

        int count = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                if(explore(grid, new int[]{i, j}) == true) {
                    count++;
                }

            }
        }

        return count;
    }

    public static int getIslandSize(int[][] grid, int row, int column) {

        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean columnInbounds = 0 <= column && column < grid[0].length;

        if(!rowInbounds || !columnInbounds) {
            return 0;
        }

        if(grid[row][column] == 0) {
            return 0;
        }

        grid[row][column] = 0;

        int size = 1;

        size += getIslandSize(grid, row+1, column);
        size += getIslandSize(grid, row-1, column);
        size += getIslandSize(grid, row, column+1);
        size += getIslandSize(grid, row, column-1);

        return size;
    }

    public static int smallestIsland(int[][] grid) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                int size = getIslandSize(grid, i, j);

                if(size > 0) {
                    smallest = Math.min(smallest, size);
                }

            }

        }

        return smallest;
    }
}
