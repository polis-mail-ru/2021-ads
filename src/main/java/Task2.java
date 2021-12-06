import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {

    private static final byte WHITE = 0;
    private static final byte GREY = 1;
    private static final byte BLACK = 2;
    private static List<Integer> result = new ArrayList<>();
    private static boolean hasCycle;
    private static byte[] color;
    private static Set<Integer>[] graph;

    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        color = new byte[n + 1];
        graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            int v = in.nextInt();
            int e = in.nextInt();
            graph[v].add(e);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == WHITE) {
                dfs(i);
            }
        }
        if (hasCycle) {
            out.println("-1");
        } else {
            for (int i = result.size() - 1; i >= 0; i--) {
                out.print(result.get(i) + " ");
            }
        }
    }

    static void dfs(int v) {
        color[v] = GREY;
        for (Integer to : graph[v]) {
            if (color[to] == GREY) {
                hasCycle = true;
                return;
            }
            if (color[to] == WHITE) {
                dfs(to);
            }
        }
        color[v] = BLACK;
        result.add(v);
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
