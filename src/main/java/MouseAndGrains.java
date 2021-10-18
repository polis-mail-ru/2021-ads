import java.io.*;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9560240
public class MouseAndGrains {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] floor = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floor[i][j] = in.nextInt();
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int left = j - 1 >= 0 ? floor[i][j - 1] : 0;
                int back = i + 1 < m ? floor[i + 1][j] : 0;
                floor[i][j] = Integer.max(left, back) + floor[i][j];
            }
        }
        StringBuilder path = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while (i <= m && j >= 0) {
            int left = j - 1 >= 0 ? floor[i][j - 1] : Integer.MIN_VALUE;
            int back = i + 1 < m ? floor[i + 1][j] : Integer.MIN_VALUE;
            if (left >= back) {
                j -= 1;
                path.insert(0, 'R');
            } else {
                i += 1;
                path.insert(0, 'F');
            }
        }
        out.println(path.deleteCharAt(0));
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
