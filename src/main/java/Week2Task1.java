import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.LinkedList;

// A. Результаты олимпиады (1)
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    static class Result implements Comparable<Result> {

        private int id;
        private int points;

        public Result(int points, int id) {
            this.points = points;
            this.id = id;
        }

        public int getPoints() {
            return points;
        }

        public int getId() {
            return id;
        }

        @Override
        public int compareTo(Result o) {
            if (getId() - o.getId() != 0) {
                return o.getId() - getId();
            }
            if (getPoints() - o.getPoints() != 0) {
                return getPoints() - o.getPoints();
            }
            return 0;
        }
    }

    public static void swap(Result[] results, int i, int j) {
        Result temp = results[i];
        results[i] = results[j];
        results[j] = temp;
    }

    public static int partition(Result[] results, int start, int end) {
        int n = (int) (Math.random() * results.length);
        Result key = results[n];

        swap(results, start, n);

        int j = start;
        for (int i = start + 1; i < end; i++) {
            if (results[i].compareTo(key) >= 0) {
                swap(results, i, ++j);
            }
        }

        swap(results, j, start);

        return j;
    }

    public static void sort(Result[] results, int start, int end) {
        if (end - start <= 1) {
            return;
        }

        int i = partition(results, start, end);

        sort(results, start, i);
        sort(results, i + 1, end);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Result[] results = new Result[n];

        for (int i = 0; i < n; i++) {
            results[i] = new Result(in.nextInt(), in.nextInt());
        }

        Arrays.sort(results);

        for (Result result : results) {
            System.out.println(result.points + " " + result.id);
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