import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 */
public final class Part2Task3 {
    private Part2Task3() {
        // Should not be instantiated
    }

    private static long getA(int x) {
        return (long) x * x;
    }

    private static long getB(int x) {
        return (long) x * x * x;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long value = 1;
        int count = 0;
        int a = 1;
        int b = 1;
        while (true) {
            if (getA(a) < getB(b)) {
                value = getA(a);
                a++;
                count++;
                if (count == x) {
                    break;
                }
            }
            if (getA(a) == getB(b)) {
                value = getA(a);
                a++;
                b++;
                count++;
                if (count == x) {
                    break;
                }
            }
            if (getA(a) > getB(b)) {
                value = getB(b);
                b++;
                count++;
                if (count == x) {
                    break;
                }
            }
        }
        System.out.println(value);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
