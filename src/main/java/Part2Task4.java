import java.io.*;
import java.util.StringTokenizer;

public final class Part2Task4 {
    private Part2Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = in.nextInt();
        }
        out.println(getCountOfUnique(numbers));
    }

    public static int getCountOfUnique(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int tmp : array) {
            min = Math.min(tmp, min);
            max = Math.max(tmp, max);
        }

        int[] nums = new int[max - min + 1];
        for (int tmp : array) {
            if (nums[tmp - min] == 0) {
                nums[tmp - min]++;
            }
        }

        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
        }
        return count;
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
