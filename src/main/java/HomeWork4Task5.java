import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public final class HomeWork4Task5 {

    private HomeWork4Task5() {
        // Should not be instantiated
    }

    static char[] s;
    static int[][] d;
    static int[][] kMinSum;
    static Map<Character, Character> brackets = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    static {
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put(')', '(');
        brackets.put(']', '[');
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String sequence;
        try {
            sequence = in.readLine();
        } catch (IOException e) {
            return;
        }
        if (sequence == null || sequence.equals("")) {
            out.println("");
            return;
        }
        stringBuilder = new StringBuilder(sequence);
        s = sequence.toCharArray();
        d = new int[s.length][s.length];
        kMinSum = new int[s.length][s.length];
        for (int i = 0; i < s.length; i++) {
            d[i][i] = 1;
        }
        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < s.length - i; j++) {
                if (isOpenBracket(s[j]) && s[j + i] == brackets.get(s[j])) {
                    int minSum = countMinSum(i, j);
                    if (minSum < d[j + 1][j + i - 1]) {
                        d[j][j + i] = minSum;
                    } else {
                        d[j][j + i] = d[j + 1][j + i - 1];
                        kMinSum[j][j + i] = -1;
                    }

                } else {
                    d[j][j + i] = countMinSum(i, j);
                }
            }
        }
        if (d[0][s.length - 1] > 0) {
            stringBuilder = new StringBuilder();
            appendCorrectSequence(0, s.length - 1);
        }
        out.println(stringBuilder);
    }

    private static int countMinSum(int i, int j) {
        int minSum = Integer.MAX_VALUE;
        int tempSum;
        for (int k = 1; k <= i; k++) {
            tempSum = d[j][k + j - 1] + d[j + k][j + i];
            if (tempSum < minSum) {
                minSum = tempSum;
                kMinSum[j][j + i] = k;
            }
        }
        return minSum;
    }

    static void appendCorrectSequence(int from, int to) {
        if (from > to) return;
        if (from == to) {
            if (isOpenBracket(s[from])) {
                stringBuilder.append(s[from]).append(brackets.get(s[from]));
            } else {
                stringBuilder.append(brackets.get(s[from])).append(s[from]);
            }
        } else {
            if (d[from][to] == 0) {
                for (int i = from; i <= to; i++) {
                    stringBuilder.append(s[i]);
                }
                return;
            }
            if (kMinSum[from][to] == -1) {
                stringBuilder.append(s[from]);
                appendCorrectSequence(from + 1, to - 1);
                stringBuilder.append(s[to]);
            } else {
                appendCorrectSequence(from, kMinSum[from][to] + from - 1);
                appendCorrectSequence(from + kMinSum[from][to], to);
            }
        }
    }

    static boolean isOpenBracket(char bracket) {
        return bracket == '(' || bracket == '[';
    }

    static void printMatrix(int[][] d, final PrintWriter out) {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                out.print(d[i][j] + " ");
            }
            out.println();
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String readLine() throws IOException {
            return reader.readLine();
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