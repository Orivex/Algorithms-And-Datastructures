import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NQueens {
    public static void main(String[] args) {
        int n = 8;
        solve(n, 0);
        System.out.println(loesungen);
    }

    static int loesungen = 0;
    static Set<Integer> spaltenSet = new HashSet<>();
    static Set<Integer> posDiag = new HashSet<>();
    static Set<Integer> negDiag = new HashSet<>();

    static void solve(int n, int zeile) {
        if(zeile == n) {
            loesungen++;
            return;
        }

        for (int spalte = 0; spalte < n; spalte++) {
            if(spaltenSet.contains(spalte) || posDiag.contains(zeile+spalte) || negDiag.contains(zeile-spalte))
                continue;

            spaltenSet.add(spalte);
            posDiag.add(zeile+spalte);
            negDiag.add(zeile-spalte);

            solve(n, zeile+1);

            spaltenSet.remove(spalte);
            posDiag.remove(zeile+spalte);
            negDiag.remove(zeile-spalte);
        }

    }
}
