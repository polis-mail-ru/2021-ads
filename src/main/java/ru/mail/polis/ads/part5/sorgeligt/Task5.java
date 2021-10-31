package ru.mail.polis.ads.part5.sorgeligt;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line.add(i + 1);
        }
        line.forEach(x -> out.print(x + " "));
        out.println();
        while (nextPermutation(line)) {
            line.forEach(x -> out.print(x + " "));
            out.println();
        }
    }

    private static boolean nextPermutation(List<Integer> line) {
        for (int i = line.size() - 2; i >= 0; i--) {
            if (line.get(i) < line.get(i + 1)) {
                int minElem = i + 1;
                for (int j = i + 1; j < line.size(); j++) {
                    if ((line.get(j) < line.get(minElem)) && (line.get(j) > line.get(i))) {
                        minElem = j;
                    }
                }
                Collections.swap(line, i, minElem);
                reverse(line, i + 1, line.size());
                return true;
            }
        }
        Collections.reverse(line);
        return false;
    }

    private static void reverse(List<Integer> list, int begin, int end) {
        end -= 1;
        for (int i = 0; i < (end - begin + 1) / 2; i++) {
            Collections.swap(list, begin + i, end - i);
        }
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

        double nextDouble() {
            return Double.parseDouble(next());
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