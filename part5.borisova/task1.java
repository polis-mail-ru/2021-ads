import java.io.*;
import java.util.Scanner;

public class task1 {
    private static void solve(final PrintWriter out) {
        Scanner in = new Scanner(System.in);

        double c = in.nextDouble();
        double l = 0;
        double r = 1e5;
        double mid = (l + r) / 2;
        double rez = mid * mid + Math.sqrt(mid);
        while (l != mid && r != mid) {
            if (rez < c) {
                l = mid;
            }
            else {
                r = mid;
            }
            mid = (l + r) / 2;
            rez = mid*mid + Math.sqrt(mid);
        }
        out.print(mid);
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
