import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task1 {

    private static final Set<Integer> shortestPath = new HashSet<>();

    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            int v = in.nextInt();
            int e = in.nextInt();
            graph[v].add(e);
            graph[e].add(v);
        }
        int distance = bfs(graph, a, b, n);
        if (distance == Integer.MAX_VALUE) {
            out.print("-1");
        } else {
            out.println(distance);
            out.print(a + " ");
            for (Integer v : shortestPath) {
                out.print(v + " ");
            }
            out.print(b);
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

    private static int bfs(Set<Integer>[] graph, int source, int destination, int n) {
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[source] = 0;
        List<Integer> q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.remove(0);
            for (Integer v : graph[u]) {
                if (d[v] == Integer.MAX_VALUE) {
                    d[v] = d[u] + 1;
                    q.add(v);
                    if (v == destination) {
                        shortestPath.add(u);
                    }
                }
            }
        }
        return d[destination];
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
