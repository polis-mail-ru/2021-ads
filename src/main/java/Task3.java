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
        int k = in.nextInt();
        Graph g = new Graph(n);
        for (int i = 0; i < k; i++) {
            g.add(in.nextInt() - 1, in.nextInt() - 1);
        }
        g.cycleCheck();
    }

    private static class Graph {
        private static ArrayList<ArrayList<Integer>> adjacencyList;
        private static final BitSet cycleNodes = new BitSet();
        private final boolean[] visited;
        private final boolean[] isBlack;
        private static int curMin = Integer.MAX_VALUE;

        public Graph(int n) {
            adjacencyList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>(1));
            }
            visited = new boolean[n];
            isBlack = new boolean[n];
        }

        public void add(int firstEdge, int secondEdge) {
            adjacencyList.get(firstEdge).add(secondEdge);
            adjacencyList.get(secondEdge).add(firstEdge);
        }

        private void dfs(int u, int lastEdge, BitSet currentCycle) {
            visited[u] = true;
            for (int adjV : adjacencyList.get(u)) {
                if (visited[adjV] && !isBlack[adjV] && adjV != lastEdge) {
                    cycleNodes.set(adjV);
                    currentCycle.set(adjV);
                    currentCycle.set(u);
                }
                if (!visited[adjV]) {
                    dfs(adjV, u, currentCycle);
                    if (currentCycle.get(adjV) && !cycleNodes.get(adjV)) {
                        currentCycle.set(u);
                    }
                }
            }
            if (currentCycle.get(u) && u < curMin) {
                curMin = u;
            }
            isBlack[u] = true;
        }

        public void cycleCheck() {
            int visitedLen = visited.length;
            BitSet currentCycle = new BitSet();
            for (int i = 0; i < visitedLen; i++) {
                if (!visited[i]) {
                    dfs(i, -1, currentCycle);
                }
            }
            if (curMin != Integer.MAX_VALUE) {
                System.out.println("Yes");
                System.out.println(curMin + 1);
            } else {
                System.out.println("No");
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