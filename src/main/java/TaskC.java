import java.io.*;
import java.util.StringTokenizer;

public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeArr1 = in.nextInt();
        int[] arr1 = new int[sizeArr1];
        for (int i = 0; i < sizeArr1; i++) {
            arr1[i] = in.nextInt();
        }

        int sizeArr2 = in.nextInt();
        int[] arr2 = new int[sizeArr2];
        for (int i = 0; i < sizeArr2; i++) {
            arr2[i] = in.nextInt();
        }

        int[][] lcs = new int[sizeArr1 + 1][sizeArr2 + 1];

        for (int i = 0; i <= sizeArr1; i++) {
            lcs[i][0] = 0;
        }

        for (int j = 1; j <= sizeArr2; j++) {
            lcs[0][j] = 0;
        }

        for (int i = 1; i <= sizeArr1; i++) {
            for (int j = 1; j <= sizeArr2; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    if (lcs[i - 1][j] >= lcs[i][j - 1]) {
                        lcs[i][j] = lcs[i - 1][j];
                    } else {
                        lcs[i][j] = lcs[i][j - 1];
                    }
                }
            }
        }

        out.println(lcs[sizeArr1][sizeArr2]);
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
