package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class SubSequenceTask {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }

        int[] dynamic = new int[n];

        for (int i = 0; i < n; i++) {
            int max = -1;
            int indexOfMax = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (data[j] != 0 && data[i] % data[j] == 0 && dynamic[j] > max) {
                    max = dynamic[j];
                    indexOfMax = j;
                }
            }
            if (indexOfMax >= 0) {
                dynamic[i] = dynamic[indexOfMax] + 1;
                continue;
            }
            dynamic[i] = 1;
        }
        int max = 1;
        for (int i = 0; i < n; i++) {
            if (dynamic[i] > max) {
                max = dynamic[i];
            }
        }
        out.println(max);
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
