import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        Participant[] participants = new Participant[N];
        for (int i = 0; i < N; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        Arrays.sort(participants);
        for (Participant participant : participants) {
            out.println(participant.ID + " " + participant.points);
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

    private static class Participant implements Comparable<Participant> {
        private final int ID;
        private final int points;

        Participant(int ID, int points) {
            this.ID = ID;
            this.points = points;
        }

        @Override
        public int compareTo(TaskA.Participant o) {
            if (this.points == o.points) {
                return this.ID - o.ID;
            }
            return o.points - this.points;
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
