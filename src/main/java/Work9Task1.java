import java.io.*;
import java.util.*;

public class Work9Task1 {
    private static List<List<Integer>> adjacencyLists;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();

        adjacencyLists = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            adjacencyLists.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            adjacencyLists.get(first).add(second);
            adjacencyLists.get(second).add(first);
        }

        Deque<Integer> res = bfs(a, b, n);
        if (res == null) {
            out.println(-1);
            return;
        }

        out.println(res.size());
        out.print(a + " ");
        while (!res.isEmpty()) {
            out.print(res.pollLast() + " ");
        }
    }

    private static Deque<Integer> bfs(int source, int destination, int n) {
        boolean[] visited = new boolean[n + 1];
        int[] parents = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (v == destination) {
                return countResult(parents, source, destination);
            }

            for (int neighbor: neighbors(v)) {
                if (!visited[neighbor]) {
                    parents[neighbor] = v;
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return null;
    }

    private static List<Integer> neighbors(int v) {
        return adjacencyLists.get(v);
    }

    private static Deque<Integer> countResult(int[] parents, int source, int destination) {
        Deque<Integer> stack = new ArrayDeque<>();

        int current = destination;

        while (current != source) {
            stack.add(current);
            current = parents[current];
        }

        return stack;
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
