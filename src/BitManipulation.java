public class BitManipulation {
    public static void main(String[] args) {
        System.out.println(countOfsetBits(7 ));
    }

    static int countOfsetBits(int number) {
        int count = 0;

       // while(number > 0) {
       //     if((number & 1) == 1) {
       //         count++;
       //     }
       //     number = number >> 1;
       // }

        while(number > 0) {
             count++;
             number = (number & number-1);
        }

        return count;
    }

    static int rightMostSetBit(int number) {
        int mask = number & (number - 1);

        int newNum = number ^ mask;
        int result = (int)(Math.log(newNum) / Math.log(2));
        return result;
    }

    static int binaryToDecimal(String binary) {
        int x = binary.length();
        int number = 0;
        for (char ch : binary.toCharArray()) {
            x--;
            if(ch == '0') {
                continue;
            }
            number += Math.pow(2, x);
        }

        return number;
    }

    static int hammingDistance(int a, int b, int k) {
        //...
        return 0;
    }

    //Bit mask
    //from LSB to MSB, 0 to n
    static int turnOnBit(int number, int i) {
        int mask = 1 << i;
        return number | mask;
    }

    static int turnOffBit(int number, int i) {
        int mask = ~(1 << i);
        return number & mask;
    }

    static int toggleBit(int number, int i) {
        int mask = 1 << i;
        return number ^ mask;
    }
}
