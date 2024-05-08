public class CoinProblem {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        int n = 99;
        System.out.println(solve(n, coins));
    }

    public static int solve(int n, int[] coins) {
        int currentIdx = coins.length-1;
        int currentCoins = 0;
        int counter = 0;
        while (currentCoins < n && currentIdx > -1) {
            System.out.println((coins[currentIdx] + currentCoins) + " <= " + n + " ?");
            if((coins[currentIdx] + currentCoins) <= n) {
                currentCoins += coins[currentIdx];
                System.out.println("--------Yes, current total coins:" + currentCoins + "--------");
                System.out.println("-------Added this coin:" + coins[currentIdx] + "-------");
                counter++;
            }
            else {
                System.out.println("No");
                currentIdx--;
            }
        }

        return counter;
    }
}
