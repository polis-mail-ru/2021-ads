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
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int number = in.nextInt();
        Pair[] participants = new Pair[number];
        for (int i = 0; i < number; i++) {
            participants[i] = new Pair(in.nextInt(), in.nextInt());
        }

        bubbleSort(participants);

        for (int i = 0; i < number; i++) {
            out.println(participants[i]);
        }
    }

    private static void bubbleSort(Pair[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    Pair temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
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

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    private static class Pair implements Comparable<Pair> {
        private final int number;
        private final int points;

        Pair(int number, int points) {
            this.number = number;
            this.points = points;
        }

        @Override
        public String toString() {
            return number + " " + points;
        }

        @Override
        public int compareTo(Task1.Pair o) {
            return (points != o.points) ? (o.points - points) : (number - o.number);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
