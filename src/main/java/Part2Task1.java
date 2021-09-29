import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task1 {
    private Part2Task1() {
        // Should not be instantiated
    }

    /*record Participant(int number, int score) implements Comparable<Participant> {
        @Override
        public int compareTo(Participant other) {
            int cmp0 = score() - other.score();
            if (cmp0 != 0) {
                return cmp0;
            }
            return number() - other.number();
        }
    }*/

    private static class Participant implements Comparable<Participant> {
        private final int number;
        private final int score;

        public Participant(int number, int score) {
            this.number = number;
            this.score = score;
        }

        public int getNumber() {
            return number;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Participant other) {
            int cmp0 = getScore() - other.getScore();
            if (cmp0 != 0) {
                return cmp0;
            }
            return other.getNumber() - getNumber();
        }
    }

    private static void sort(Participant[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Participant x = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(x) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = x;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] participants = new Participant[n];
        for (int i = 0; i < n; i++) {
            int number = in.nextInt();
            int score = in.nextInt();
            participants[i] = new Participant(number, score);
        }

        sort(participants);
        for (int i = 0; i < participants.length; i++) {
            out.println(participants[i].getNumber() + " " + participants[i].getScore());
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
