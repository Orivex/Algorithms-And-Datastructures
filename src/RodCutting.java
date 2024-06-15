public class RodCutting {
    public static void main(String[] args) {
        int[] price = {10, 2, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        System.out.println(solve(price, 8));
    }

    static int solve(int[] price, int length) {
        if(length == 1) {
            return price[0];
        }

        int max = price[length];

        for (int i = 1; i < length; i++) {
            max = Math.max(max, solve(price, i) + solve(price, length-i));
        }

        return max;
    }

    //static int bottomUpCutRod() {
    //    //Tomorrow..
    //}
}
