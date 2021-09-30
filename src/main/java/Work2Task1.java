import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work2Task1 {
    private Work2Task1() {
        // Should not be instantiated
    }

    private static class Participant implements Comparable<Participant> {
        Integer id;
        Integer score;

        @Override
        public int compareTo(Participant p) {
            int result = this.score.compareTo(p.score);

            if (result == 0) {
                result = p.id.compareTo(this.id);
            }

            return result;
        }

        @Override
        public String toString() {
            return "" + id + " " + score;
        }

        public Participant(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] array = new Participant[n];

        for (int i = 0; i < n; i++) {
            array[i] = new Participant(in.nextInt(), in.nextInt());
        }

        sort(array, 0, n);

        for (int i = 0; i < n; i++) {
            out.println(array[i]);
        }
    }

    private static void sort(Participant[] array, int from, int to) {
        if (from >= to - 1) {
            return;
        }

        int n = partition(array, from, to);
        sort(array, from, n);
        sort(array, n + 1, to);
    }

    private static int partition(Participant[] array, int from, int to) {
        Random random = new Random();
        int randomIndex = random.nextInt(to - from) + from;
        swap(array, from, randomIndex);

        Participant pivotal = array[from];
        int i = from;

        for (int j = from + 1; j < to; ++j) {
            if (array[j].compareTo(pivotal) >= 0) {
                swap(array, ++i, j);
            }
        }

        swap(array, i, from);
        return i;
    }

    private static void swap(Participant[] array, int first, int second) {
        Participant elementToSwap = array[first];
        array[first] = array[second];
        array[second] = elementToSwap;
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
