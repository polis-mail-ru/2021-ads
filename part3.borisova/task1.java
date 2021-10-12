import java.io.*;
import java.util.StringTokenizer;

public class task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int number = in.nextInt();
        int[] arr = new int[number + 1];
        arr[0] = 0;
        for (int i = 1; i <= number; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 1; i <= number / 2 - 1; i++) {
            if (arr[i] > arr[2 * i] || arr[i] > arr[2 * i + 1]) {
                System.out.println("NO");
                return;
            }
        }
        int i = number / 2;
        if (arr[i] > arr[2 * i]) {
            System.out.println("NO");
            return;
        }
        if (number % 2 == 1 && arr[i] > arr[2 * i + 1]) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
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
