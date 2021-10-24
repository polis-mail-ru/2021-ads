import java.io.*;
import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/9625529
public class LongestSubsequence {

    private static void solve(final Scanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        int[] d = new int[n];
        d[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if ((a[j] != 0) && (a[i] % a[j] == 0) && (d[j] > max)) {
                    max = d[j];
                }
            }
            d[i] = max + 1;
        }
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }
        out.println(max);
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
