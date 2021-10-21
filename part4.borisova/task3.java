import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] numbers = new int[m];

        for (int i = 0; i < m; i++) {
            numbers[i] = in.nextInt();
        }
        int[] counts = new int[n];
        Arrays.fill(counts, 0);
        int max;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < sequence.length; j++) {
                max = 0;
                if (numbers[i] == sequence[j]) {
                    for (int k = j; k >=0; k--) {
                         if (counts[k] > max) {
                             max = counts[k];
                         }
                    }
                    if (counts[j] < max + 1) {
                        counts[j] = max + 1;
                    }
                }
            }
        }
        int ans = 0;
        for (int count : counts) {
            if (count > ans) {
                ans = count;
            }
        }
        out.print(ans);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
