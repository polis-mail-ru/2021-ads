import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork4Task2 {

    private HomeWork4Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        int[] maxCost = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int k = in.nextInt();
        maxCost[0] = array[0];
        int currentMax = maxCost[0];
        int currentMaxIndex = 0;
        for (int i = 1; i < k; i++) {
            for (int j = currentMaxIndex + 1; j < i; j++) {
                if (maxCost[j] > currentMax) {
                    currentMax = maxCost[j];
                    currentMaxIndex = j;
                }
            }
            maxCost[i] = array[i];
            if (currentMax > 0) {
                maxCost[i] += currentMax;
            }
        }
        for (int i = k; i < n; i++) {
            currentMax = maxCost[i - k];
            for (int j = i - k + 1; j < i; j++) {
                if (maxCost[j] > currentMax) {
                    currentMax = maxCost[j];
                }
            }
            maxCost[i] = currentMax + array[i];
        }
        int totalMaxCost = maxCost[n - k];
        for (int i = n - k + 1; i < n; i++) {
            if (maxCost[i] > totalMaxCost) {
                totalMaxCost = maxCost[i];
            }
        }
        out.println(totalMaxCost);
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