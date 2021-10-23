import java.io.*;
import java.util.StringTokenizer;

public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }

        int[] dinArr = new int[size];
        dinArr[0] = 1;

        int max = 1;
        for (int i = 1; i < size; i++) {
            dinArr[i] = getPastMaxSubsSize(array, dinArr, i, array[i]) + 1;
            max = Math.max(max, dinArr[i]);
        }

        out.println(max);
    }

    private static int getPastMaxSubsSize(int[] array, int[] dinArr, int border, int el) {
        int maxSubsSize = 0;

        for (int i = 0; i < border; i++) {
            if (array[i] != 0 && el % array[i] == 0) {
                if (dinArr[i] > maxSubsSize) {
                    maxSubsSize = dinArr[i];
                }
            }
        }

        return maxSubsSize;
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
