import java.io.*;
import java.util.StringTokenizer;

public class TaskD {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] stairCost = new int[n + 2];
        stairCost[0] = 0;
        stairCost[n + 1] = 0;
        for (int i = 1; i < n + 1; i++) {
            stairCost[i] = in.nextInt();
        }

        int maxStepSize = in.nextInt();
        int[] maxWayCostToStair = new int[n + 2];
        maxWayCostToStair[0] = 0;
        for (int i = 1; i < n + 2; i++) {
            int maxCostPrevStair = maxWayCostToStair[i - 1];
            for (int stepC = 2; stepC <= maxStepSize && stepC <= i; stepC++) {
                if (maxWayCostToStair[i - stepC] > maxCostPrevStair) {
                    maxCostPrevStair = maxWayCostToStair[i - stepC];
                }
            }
            maxWayCostToStair[i] = maxCostPrevStair + stairCost[i];
        }

        out.println(maxWayCostToStair[n + 1]);
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
