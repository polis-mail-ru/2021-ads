
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

        boolean lessThan(Pair rhs) {
            if (score == rhs.score) {
                return id > rhs.id;
            }
            return score < rhs.score;
        }
    }

    private Task1() {
        // Should not be instantiated
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        ArrayList<Pair> array = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            array.add(new Pair(in.nextInt(), in.nextInt()));
        }

        for (int i = 1; i < count; i++) {
            Pair key = array.get(i);
            int j = i - 1;
            while (j >= 0 && !key.lessThan(array.get(j))) {
                array.set(j + 1, array.get(j));
                --j;
            }
            array.set(j + 1, key);
        }

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
