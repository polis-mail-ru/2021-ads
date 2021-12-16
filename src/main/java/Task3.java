import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            int distance = in.nextInt();
            edges.add(new Edge(first, second, distance));
        }
        BellmanFord(edges, n);
    }

    private static class Edge {
        private final int from;
        private final int to;
        private final int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int From() {
            return from;
        }

        public int To() {
            return to;
        }

        public int Dist() {
            return dist;
        }
    }

    private static void BellmanFord(List<Edge> edges, int n) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;
        for (int i = 1; i < n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.From()] != Integer.MAX_VALUE && dist[edge.From()] + edge.Dist() < dist[edge.To()]) {
                    dist[edge.To()] = dist[edge.From()] + edge.Dist();
                }
            }
        }
        PrintDistances(dist, n);
    }

    private static void PrintDistances(int[] dist, int n) {
        for (int i = 0; i < n; ++i) {
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.print(dist[i] + " ");
            } else {
                System.out.print("30000 ");
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
