import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] mass = new int[215];
        int number = in.nextInt();
        int firstNum = in.nextInt();
        mass[107]++;
        int temp;
        for (int i = 0; i < number - 1; i++) {
            temp = in.nextInt();
            mass[temp - firstNum + 107]++;
        }
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i]; j++) {
                out.print((firstNum + i - 107) + " ");
            }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
