import java.io.*;
import java.util.StringTokenizer;

public class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int participantsC = in.nextInt();

        Participant participants[] = new Participant[participantsC];

        for (int i = 0; i < participantsC; i++) {
            int id = in.nextInt();
            int score = in.nextInt();
            participants[i] = new Participant(id, score);
        }

        qsort(participants, 0, participants.length);
        for (Participant participant : participants) {
            out.println(participant.getId() + " " + participant.getScore());
        }
    }

    public static void qsort(Comparable[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        qsort(array, fromInclusive, i);
        qsort(array, i + 1, toExclusive);
    }

    private static int partition(Comparable[] array, int fromInclusive, int toExclusive) {
        Comparable pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j].compareTo(pivotal) <= 0) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static void swap(Comparable[] array, int i, int j) {
        Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static class Participant implements Comparable<Participant> {
        private final int id;
        private final int score;

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

        public int compareTo(Participant anotherP) {
            if (score < anotherP.score) {
                return 1;
            } else if (score > anotherP.score) {
                return -1;
            } else if (id > anotherP.id) {
                return 1;
            } else if (id < anotherP.id) {
                return -1;
            } else {
                return 0;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
