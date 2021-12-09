import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        Graph graph = new Graph(in.nextInt());
        int edgesCounter = in.nextInt();
        for (int i = 0; i < edgesCounter; i++) {
            graph.addNode(in.nextInt() - 1, in.nextInt() - 1);
        }
        graph.topologySort(out);
    }

    private static class Graph {
        private final List<ArrayList<Integer>> adjacencyList;

        public Graph(int vertexCounter) {
            this.adjacencyList = new ArrayList<>(vertexCounter);
            for (int i = 0; i < vertexCounter; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addNode(int firstNode, int secondNode) {
            adjacencyList.get(firstNode).add(secondNode);
        }

        public void topologySort(final PrintWriter out) {
            Map<Integer, Boolean> mapOfVisited = new HashMap<>();
            Map<Integer, Boolean> mapOfBlackVertex = new HashMap<>();
            Stack<Integer> answer = new Stack<>();
            for (int i = 0; i < adjacencyList.size(); i++) {
                if (!mapOfVisited.containsKey(i)) {
                    if (!dfs(mapOfVisited, mapOfBlackVertex, i, answer)) {
                        out.print(-1);
                        return;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < adjacencyList.size(); i++) {
                sb.append(answer.pop() + 1);
                if (i != adjacencyList.size() - 1) {
                    sb.append(" ");
                }
            }
            out.print(sb);
        }

        private boolean dfs(Map<Integer, Boolean> mapOfVisited,
                            Map<Integer, Boolean> mapOfBlackVertex,
                            int vertex,
                            Stack<Integer> answer) {
            mapOfVisited.put(vertex, true);
            for (int edges : adjacencyList.get(vertex)) {
                if (mapOfVisited.containsKey(edges) && !mapOfBlackVertex.containsKey(edges)
                        || !mapOfVisited.containsKey(edges) && !dfs(mapOfVisited, mapOfBlackVertex, edges, answer)) {
                    return false;
                }
            }
            mapOfBlackVertex.put(vertex, true);
            answer.push(vertex);
            return true;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
