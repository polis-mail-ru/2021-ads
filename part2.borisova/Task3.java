import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long a = 1;
        long b = 1;
        long aFunc = 1;
        long bFunc = 1;
        for (int i = 0; i < x; i++) {
            aFunc = a * a;
            bFunc = b * b * b;
            if (aFunc == bFunc) {
                a++;
                b++;
            } else if (aFunc < bFunc) {
                a++;
            } else {
                b++;
            }
        }
        out.print(Math.min(aFunc, bFunc));
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
