
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void swap(Human[] arr, int left, int right) {
        Human tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();

        Human[] people = new Human[n];

        for (int i = 0; i < n; i++) {
            people[i] = new Human(in.nextInt(), in.nextInt());
        }


        for (int i = 0; i < n; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - 1; j++) {
                if (people[j].compareTo(people[j + 1]) > 0) {
                    isSorted = false;
                    swap(people, j, j + 1);
                }
            }
            if (isSorted)
                break;
        }

        for (int i = 0; i < n; i++) {
            out.println(people[i].m_ID + " " + people[i].m_score);
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

    private static class Human implements Comparable<Human> {
        final int m_score;
        final int m_ID;

        @Override
        public int compareTo(Human human) {
            if (this.m_score == human.m_score)
                return this.m_ID - human.m_ID;
            return human.m_score - this.m_score;
        }

        public Human(int ID, int score) {
            this.m_ID = ID;
            this.m_score = score;
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
