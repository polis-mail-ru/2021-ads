import java.io.*;
import java.util.StringTokenizer;

public final class Part2Task2 {
    private Part2Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = in.nextInt();
        }
        sort(numbers);
        for (int n : numbers) {
            out.println(n);
        }
    }

    public static void sort(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int tmp : array) {
            min = Math.min(tmp, min);
            max = Math.max(tmp, max);
        }

        int[] nums = new int[max - min + 1];
        for (int tmp : array) {
            nums[tmp - min]++;
        }

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            while (nums[j] == 0) {
                j++;
            }
            array[i] = j + min;
            nums[j]--;
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
