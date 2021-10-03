

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Test3 {
    private Week2Test3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int order = in.nextInt();
        int i = 1;
        int j = 1;
        long a = 1;
        long b = 1;
        long answer = 0;
        // using formulas for (x+1)^3 = x^3 + 3x^2 + 3x + 1 and (x+1)^2 = x^2 + 2x + 1
        while (order > 0) {
            if (b < a) {
                answer = b;
                b += j * ((j << 2) - j + 3L) + 1L; // b = i^3
                ++j;
            } else {
                answer = a;
                if (a == b) {
                    b += j * ((j << 2) - j + 3L) + 1L; // b = i^3
                    ++j;
                }
                a += (i << 1) + 1L; // a = i^2
                ++i;
            }
            --order;
        }
        
        out.println(answer);
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
