

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskA {
    private TaskA() {
        // Should not be instantiated
    }

    private static void ContestantBubbleSort(Contestant[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1].compareTo(array[j]) > 0) {
                    Contestant temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Contestant[] contestants = new Contestant[n];
        for (int i = 0; i < contestants.length; i++) {
            contestants[i] = new Contestant(in.nextInt(), in.nextInt());
        }

        ContestantBubbleSort(contestants);

        for (Contestant contestant : contestants) {
            out.println(contestant.getIdentityNumber() + " " + contestant.getScore());
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

    private static final class Contestant implements Comparable<Contestant> {
        private final int identityNumber;
        private final int score;

        Contestant(int identityNumber, int score) {
            this.identityNumber = identityNumber;
            this.score = score;
        }

        public int getIdentityNumber() {
            return identityNumber;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(TaskA.Contestant contestant) {
            if (this.score > contestant.score) {
                return 1;
            } else if (this.score < contestant.score) {
                return -1;
            }
            if (this.identityNumber < contestant.identityNumber) {
                return 1;
            } else if (this.identityNumber > contestant.identityNumber) {
                return -1;
            }
            return 0;
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
}
