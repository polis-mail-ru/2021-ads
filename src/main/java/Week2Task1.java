import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task1 {
    private Week2Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfParticipants = in.nextInt();
        OlympiadParticipant[] participantsList = new OlympiadParticipant[numberOfParticipants];

        for (int i = 0; i < numberOfParticipants; ++i) {
            participantsList[i] = new OlympiadParticipant(in.nextInt(), in.nextInt());
        }

        sort(participantsList);

        for (int i = 0; i < numberOfParticipants; ++i) {
            out.println(participantsList[i].id + " " + participantsList[i].score);
        }
    }

    private static void sort(OlympiadParticipant[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            int temp = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[temp].compareTo(arr[j]) > 0) {
                    temp = j;
                }
            }

            OlympiadParticipant tempParticipant = arr[i];
            arr[i] = arr[temp];
            arr[temp] = tempParticipant;
        }
    }

    private static class OlympiadParticipant implements Comparable<OlympiadParticipant> {
        private int id;
        private int score;

        OlympiadParticipant(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Week2Task1.OlympiadParticipant o) {
            if (o.score - this.score == 0) {
                return this.id - o.id;
            }

            return o.score - this.score;
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
