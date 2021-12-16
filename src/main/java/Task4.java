import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4 {
    static int[] mas;
    static int[] size;

    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        mas = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            mas[i] = i;
            size[i] = 1;
        }
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            int danger = in.nextInt();
            edges.add(new Edge(first, second, danger));
        }
        edges.sort(new comparator());
        int index = 0;
        for (int i = 0; i < m; i++) {
            Union(edges.get(i).From(), edges.get(i).To());
            if (Repr(1) == Repr(n)) {
                index = i;
                break;
            }
        }
        out.println(edges.get(index).Danger());
    }

    private static class Edge {
        private final int from;
        private final int to;
        private final int danger;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.danger = dist;
        }

        public int From() {
            return from;
        }

        public int To() {
            return to;
        }

        public int Danger() {
            return danger;
        }
    }

    static int Repr(int n) {
        if (n == mas[n]) {
            return n;
        }
        mas[n] = Repr(mas[n]);
        return mas[n];
    }

    static int Union(int x, int y) {
        x = Repr(x);
        y = Repr(y);
        if (x == y) {
            return 0;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        mas[y] = x;
        size[x] += size[y];
        return 1;
    }

    public static class comparator implements Comparator<Edge> {
        public int compare(Edge a, Edge b) {
            return a.Danger()- b.Danger();
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
