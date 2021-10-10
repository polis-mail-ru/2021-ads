package ru.mail.polis.ads.part3.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week3Test1 {
    private Week3Test1() {
        // Should not be instantiated
    }
    
    // https://www.e-olymp.com/ru/submissions/9482190
    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];

        array[0] = in.nextInt();
        for (int i = 1; i < array.length; ++i) {
            array[i] = in.nextInt();
            if (array[i] < array[(i - 1) >> 1]) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");
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
