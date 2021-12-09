import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4 {
    private static List<Integer>[] g;
    private static List<Integer>[] revG;

    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        g = new ArrayList[n + 1];
        revG = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
            revG[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            g[first].add(second);
            revG[second].add(first);
        }
        out.println(graphCondensation(n));
    }

    private static int graphCondensation(int n) {
        boolean[] visited = new boolean[n + 1];
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, path, visited);
            }
        }
        int[] color = new int[n + 1];
        int c = 1;
        for (int i = 1; i <= n; i++) {
            int u = path.get(n - i);
            if (color[u] == 0) {
                dfsReverse(u, c++, color);
            }
        }
        TreeSet<Pair> s = new TreeSet<>();
        for (int i = 1; i < g.length; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int to = g[i].get(j);
                if (color[i] != color[to]) {
                    s.add(new Pair(color[i], color[to]));
                }
            }
        }
        return s.size();
    }

    private static void dfs(int u, List<Integer> path, boolean[] visited) {
        visited[u] = true;
        for (int el : g[u]) {
            if (!visited[el]) {
                dfs(el, path, visited);
            }
        }
        path.add(u);
    }

    private static void dfsReverse(int u, int c, int[] color) {
        color[u] = c;
        for (int el : revG[u]) {
            if (color[el] == 0) {
                dfsReverse(el, c, color);
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair a) {
            if (x == a.x) return y - a.y;
            return x - a.x;
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
