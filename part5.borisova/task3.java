import java.io.*;
import java.util.StringTokenizer;

public class task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        long[] array = new long[size];
        int[] div = new int[size];
        int maxDiv;
        int maxAnswer = 1;
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
            div[i] = 1;
            maxDiv = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] != 0 && array[i] % array[j] == 0 && div[j] > maxDiv) {
                    maxDiv = div[j];
                }
            }
            div[i] += maxDiv;
            if (maxAnswer < div[i]) {
                maxAnswer = div[i];
            }
        }
        out.print(maxAnswer);
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
