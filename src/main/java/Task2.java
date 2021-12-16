import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    static int[][] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] parent;
    static final int MAX_DISTANCE = 1000000001;

    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        visited = new boolean[n + 1];
        graph = new int[n + 1][n + 1];
        for (int[] row : graph) {
            Arrays.fill(row, MAX_DISTANCE);
        }
        for (int i = 1; i <= m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            int distance = in.nextInt();
            graph[first][second] = distance;
            graph[second][first] = distance;
        }
        dist = new int[n + 1];
        Arrays.fill(dist, MAX_DISTANCE);
        dist[s] = 0;
        parent = new int[n + 1];
        Arrays.fill(parent, -1);
        for (int i = 1; i < n; i++) {
            int min = MAX_DISTANCE;
            int closestVertex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    closestVertex = j;
                }
            }
            if (closestVertex == -1) {
                break;
            }
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && graph[closestVertex][j] != MAX_DISTANCE) {
                    Relax(closestVertex, j);
                }
            }
            visited[closestVertex] = true;
        }
        if (dist[f] == MAX_DISTANCE) {
            out.println(-1);
        } else {
            out.println(dist[f]);
            PrintPath(f, out);
        }
    }

    static void Relax(int i, int j) {
        if (dist[i] + graph[i][j] < dist[j]) {
            dist[j] = dist[i] + graph[i][j];
            parent[j] = i;
        }
    }

    static void PrintPath(int vertex, PrintWriter out) {
        if (vertex == -1) {
            return;
        }
        PrintPath(parent[vertex], out);
        out.print(vertex + " ");
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
