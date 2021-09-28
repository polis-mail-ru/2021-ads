import java.io.*;
import java.util.StringTokenizer;

public final class Part2Task1 {
    private Part2Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        Participant[] participants = new Participant[N];
        for (int i = 0; i < N; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        sort(participants, 0, N);
        for (Participant participant : participants) {
            out.println(participant.getId() + " " + participant.getScore());
        }
    }

    public static void sort(Comparable[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            Comparable key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static class Participant implements Comparable<Participant> {
        private int id;
        private int score;

        public Participant(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Participant participant) {
            int condition1 = participant.score - this.score;
            if (condition1 != 0) {
                return condition1;
            }
            return this.id - participant.id;
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
