package ru.mail.polis.ads.part3.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9543405
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] maybeHeap = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            maybeHeap[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            final int j = 2 * i;
            if (j <= n && maybeHeap[i] > maybeHeap[j]) {
                out.println("NO");
                return;
            }
            if (j + 1 <= n && maybeHeap[i] > maybeHeap[j + 1]) {
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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