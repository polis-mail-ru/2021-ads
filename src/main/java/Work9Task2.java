import java.io.*;
import java.util.*;

public class Work9Task2 {
    private static List<List<Integer>> adjacencyLists;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        adjacencyLists = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            adjacencyLists.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            adjacencyLists.get(first).add(second);
        }

        Deque<Integer> res = topologicalSort(n);

        while (!res.isEmpty()) {
            out.print(res.pollFirst() + " ");
        }
    }

    private static Deque<Integer> topologicalSort(int n) {
        boolean[] visited = new boolean[n + 1];
        boolean[] currentVisit = new boolean[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i] && !dfs(i, stack, visited, currentVisit)) {
                stack.clear();
                stack.push(-1);
                return stack;
            }
        }

        return stack;
    }

    private static boolean dfs(int v, Deque<Integer> stack, boolean[] visited, boolean[] currentVisit) {
        visited[v] = true;
        currentVisit[v] = true;
        for (int neighbor: neighbors(v)) {
            if (currentVisit[neighbor]) {
                return false;
            }
            if (!visited[neighbor] && !dfs(neighbor, stack, visited, currentVisit)) {
                return false;
            }
        }
        currentVisit[v] = false;
        stack.push(v);
        return true;
    }

    private static List<Integer> neighbors(int v) {
        return adjacencyLists.get(v);
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
