import java.io.*;
import java.util.StringTokenizer;


public final class HomeWork2Task1 {

    private static Participant[] tempArray;

    private HomeWork2Task1() {
        // Should not be instantiated
    }

    static class Participant implements Comparable<Participant> {
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

        @Override
        public int compareTo(HomeWork2Task1.Participant o) {
            if (score == o.score) {
                return -(id - o.id);
            }
            return score - o.score;
        }

        @Override
        public String toString() {
            return id + " " + score;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] participants = new Participant[n];
        for (int i = 0; i < n; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        tempArray = new Participant[n];
        sort(participants, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.println(participants[i]);
        }
    }

    static void sort(Participant[] participants, int fromInclusive, int toInclusive) {
        if (fromInclusive < toInclusive) {
            int mid = (fromInclusive + toInclusive) / 2;
            sort(participants, fromInclusive, mid);
            sort(participants, mid + 1, toInclusive);
            merge(participants, fromInclusive, mid, toInclusive);
        }
    }

    static void merge(Participant[] participants, int fromInclusive, int mid, int toInclusive) {
        int l = mid - fromInclusive + 1;
        int r = toInclusive - mid;
        for (int i = 0; i < l; ++i) {
            tempArray[i] = participants[fromInclusive + i];
        }
        int i = 0;
        int j = 0;
        int k = fromInclusive;
        while (i < l && j < r) {
            if (tempArray[i].compareTo(participants[mid + 1 + j]) > 0) {
                participants[k] = tempArray[i];
                i++;
            } else {
                participants[k] = participants[mid + 1 + j];
                j++;
            }
            k++;
        }
        while (i < l) {
            participants[k] = tempArray[i];
            i++;
            k++;
        }
        while (j < r) {
            participants[k] = participants[mid + 1 + j];
            j++;
            k++;
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