import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            g.add(in.nextInt() - 1, in.nextInt() - 1);
        }
        g.topologicalSort();
    }

    private static class Graph {
        private static ArrayList<ArrayList<Integer>> adjacencyList;
        private final boolean[] visited;
        private final boolean[] isBlack;
        private static boolean hasCycle = false;

        public Graph(int n) {
            adjacencyList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            visited = new boolean[n];
            isBlack = new boolean[n];
        }

        public void add(int firstEdge, int secondEdge) {
            adjacencyList.get(firstEdge).add(secondEdge);
        }

        private void dfs(List<Integer> result, int u) {
            visited[u] = true;
            for (int adjV : adjacencyList.get(u)) {
                if (!visited[adjV]) {
                    dfs(result, adjV);
                } else if (!isBlack[adjV]) {
                    hasCycle = true;
                }
            }
            result.add(u);
            isBlack[u] = true;
        }

        public void topologicalSort() {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && !hasCycle) {
                    dfs(result, i);
                }
            }
            if (hasCycle) {
                System.out.print(-1);
            } else {
                Collections.reverse(result);
                for (Integer res : result) {
                    System.out.print((res + 1) + " ");
                }
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
