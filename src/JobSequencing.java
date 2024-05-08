public class JobSequencing {

    public static void main(String[] args) {
        int[] profits = {50, 30, 25, 10, 5};
        int[] deadlines = {3, 1, 5, 7, 1};
        int time = 0;
        int profit = 0;

        for (int i = 0; i < profits.length; i++) {
            if(time < deadlines[i]) {
                profit += profits[i];
                time++;
                System.out.println("Current profit: " + profit + " \nJob: " + i);
            }
        }
    }
}
