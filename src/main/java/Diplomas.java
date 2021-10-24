import java.io.*;
import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/9620688
public class Diplomas {
    private static void solve(final Scanner in, final PrintWriter out) {
        long w = in.nextLong();
        long h = in.nextLong();
        long n = in.nextLong();
        long sureWontFit = 0;
        long sureWillFit = Math.max(w, h) * n;
        while (sureWillFit - sureWontFit > 1) {
            long mid = (sureWillFit + sureWontFit) / 2;
            long diplomasFitting = (long) (Math.floor((double) mid / w) * Math.floor((double) mid / h));
            if (diplomasFitting >= n) {
                sureWillFit = mid;
            } else {
                sureWontFit = mid;
            }
        }
        out.println(sureWillFit);
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
