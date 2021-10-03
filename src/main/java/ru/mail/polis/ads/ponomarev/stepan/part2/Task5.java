package ru.mail.polis.ads.ponomarev.stepan.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//TODO: Пока не работает

public class Task5 {
    private static final Map<Integer, Boolean> VALUE_TO_REPEAT_COUNT = new HashMap<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        int firstArraySize = in.nextInt();
        int[] firstArray = new int[firstArraySize];
        for (int i = 0; i < firstArraySize; i++) {
            firstArray[i] = in.nextInt();

            VALUE_TO_REPEAT_COUNT.put(firstArray[i], true);
        }

        int secondArraySize = in.nextInt();
        int targetRepeatCount = Math.min(firstArraySize, secondArraySize);
        int repeatCount = 0;
        int[] secondArray = new int[secondArraySize];
        for (int i = 0; i < secondArraySize; i++) {
            secondArray[i] = in.nextInt();

            if (VALUE_TO_REPEAT_COUNT.get(secondArray[i]) != null) {
                repeatCount++;
            }
        }

        out.println(targetRepeatCount <= repeatCount ? "YES" : "NO");
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