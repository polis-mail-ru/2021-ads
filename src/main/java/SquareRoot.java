import java.io.PrintWriter;
import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/9620838
public class SquareRoot {
    private static final double EPSILON = 0.000001;

    private static void solve(final Scanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double left = 0;
        double right = c;
        double value;
        double mid;
        do {
            mid = (right + left) / 2;
            value = Math.pow(mid, 2.0) + Math.sqrt(mid);
            if (value > c) {
                right = mid;
            } else {
                left = mid;
            }
        } while (Math.abs(value - c) > EPSILON);
        out.println(mid);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
