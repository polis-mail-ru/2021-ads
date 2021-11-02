package part5.tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public final class FourthTask {

    private static final int MAX = 256;

    private FourthTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        String first = in.next();
        String second = in.next();
        if (first.length() > MAX || second.length() > MAX) {
            throw new IllegalArgumentException();
        }
        if (first.equals(second)) {
            out.println("YES");
            return;
        }
        String string, template;
        if (first.contains("?") || first.contains("*")) {
            string = second;
            template = first;
        } else {
            string = first;
            template = second;
        }
        boolean[][] d = new boolean[template.length() + 1][string.length() + 1];
        d[0][0] = true;
        for (int i = 1; i < template.length() + 1; i++) {
            for (int j = 1; j < string.length() + 1; j++) {
                if (template.charAt(i - 1) == string.charAt(j - 1) || template.charAt(i - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (template.charAt(i - 1) == '*') {
                    d[i][j] = d[i - 1][j - 1] || d[i][j - 1] || d[i - 1][j];
                }
            }
        }
        out.println(d[template.length()][string.length()] ? "YES" : "NO");
    }

    private static int max(int[] array) {
        Arrays.sort(array);
        return array[array.length - 1];
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
