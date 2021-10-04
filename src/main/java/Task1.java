import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    public static class Pair {
        int id;
        int score;

        public Pair(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    private Task1() {
        // Should not be instantiated
    }

    private static void sort(Pair[] array, Comparator<Pair> comparator) {
        for (int i = 1; i < array.length; i++) {
            Pair key = array[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(key, array[j]) >= 0) {
                array[j + 1] = array[j];
                --j;
            }
            array[j + 1] = key;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Pair[] array = new Pair[count];
        for (int i = 0; i < count; i++) {
            array[i] = new Pair(in.nextInt(), in.nextInt());
        }

        sort(array, (o1, o2) -> {
            if (o1.score == o2.score) {
                return o2.id - o1.id;
            }
            return o1.score - o2.score;
        });

        for (Pair pair : array) {
            out.println(pair.id + " " + pair.score);
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
