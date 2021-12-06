import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {

    private static final byte WHITE = 0;
    private static final byte GREY = 1;
    private static final byte BLACK = 2;
    private static byte[] color;
    private static int[] prev;
    private static int cycleStart;
    private static int cycleEnd;
    private static Set<Integer> cycleVertices = new HashSet<>();
    private static Set<Integer>[] graph;

    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        color = new byte[n + 1];
        prev = new int[n + 1];
        graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            int v = in.nextInt();
            int e = in.nextInt();
            graph[v].add(e);
            graph[e].add(v);
        }
        doDfs(n);
        if (cycleVertices.isEmpty()) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(cycleVertices.stream()
                    .mapToInt(value -> value)
                    .min().getAsInt()
            );
        }
    }

    private static void doDfs(int n) {
        for (int i = 1; i <= n; i++) {
            if (color[i] == WHITE) {
                dfs(i);
            }
        }
    }

    private static void dfs(int v) {
        color[v] = GREY;
        for (Integer to : graph[v]) {
            if (color[to] == WHITE) {
                prev[to] = v;
                dfs(to);
            }
            if (color[to] == GREY && to != prev[v]) {
                cycleStart = to;
                cycleEnd = v;
                while (cycleEnd != cycleStart) {
                    cycleVertices.add(cycleEnd);
                    cycleEnd = prev[cycleEnd];
                }
                cycleVertices.add(cycleStart);
            }
        }
        color[v] = BLACK;
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
