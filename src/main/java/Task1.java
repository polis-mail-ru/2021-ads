import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task1 {
    private static int start;
    private static int end;

    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        start = in.nextInt() - 1;
        end = in.nextInt() - 1;
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            g.add(in.nextInt() - 1, in.nextInt() - 1);
        }
        g.findShortestPath();
    }

    private static class Graph {
        private final ArrayList<ArrayList<Integer>> adjacencyList;
        private final boolean[] visited;
        private final HashMap<Integer, Integer> path = new HashMap<>();

        public Graph(int n) {
            adjacencyList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            visited = new boolean[n];
        }

        public void add(int firstEdge, int secondEdge) {
            adjacencyList.get(firstEdge).add(secondEdge);
            adjacencyList.get(secondEdge).add(firstEdge);
        }

        public void findShortestPath() {
            if (!bfs()) {
                System.out.println(-1);
            } else {
                StringBuilder result = new StringBuilder();
                int vertex = end;
                int pathLength = 0;
                while (path.get(vertex) != null) {
                    result.append(" ").append(vertex + 1);
                    pathLength++;
                    vertex = path.get(vertex);
                }
                result.append(" ").append(start + 1);
                System.out.println(pathLength);
                System.out.println(result.reverse().deleteCharAt(result.length() - 1));
            }
        }

        private boolean bfs() {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;
            while (!queue.isEmpty()) {
                int current = queue.pop();
                if (current == end) {
                    return true;
                }
                for (int adjV : adjacencyList.get(current)) {
                    if (!visited[adjV]) {
                        queue.add(adjV);
                        visited[adjV] = true;
                        path.put(adjV, current);
                    }
                }
            }
            return false;
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
