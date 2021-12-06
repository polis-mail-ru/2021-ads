import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4 {

    private static Set<Integer>[] graph1;
    private static Set<Integer>[] graph2;
    private static int[] color;
    private static int[] used;
    private static List<Integer> top = new ArrayList<>();
    private static Set<Pair> s = new TreeSet<>();

    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph1 = new Set[n + 1];
        graph2 = new Set[n + 1];
        used = new int[n + 1];
        color = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph1[i] = new HashSet<>();
            graph2[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph1[a].add(b);
            graph2[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            if (used[i] == 0) {
                dfs1(i);
            }
        }
        Arrays.fill(color, -1);
        int k = 0;
        for (int i = 1; i <= n; i++) {
            int v = top.get(n - i);
            if (color[v] == -1) {
                dfs2(v, k++);
            }
        }
        for (int i = 1; i < graph1.length; i++) {
            for (Integer to : graph1[i]) {
                if (color[i] != color[to]) {
                    s.add(new Pair(color[i], color[to]));
                }
            }
        }
        out.println(s.size());
    }

    private static void dfs1(int v) {
        used[v] = 1;
        for (Integer to : graph1[v]) {
            if (used[to] == 0) {
                dfs1(to);
            }
        }
        top.add(v);
    }

    private static void dfs2(int v, int c) {
        color[v] = c;
        for (Integer to : graph2[v]) {
            if (color[to] == -1) {
                dfs2(to, c);
            }
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
}