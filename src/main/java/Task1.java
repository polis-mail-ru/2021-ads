import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        Graph graph = new Graph(in.nextInt());
        int edgeCounter = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        for (int i = 0; i < edgeCounter; i++) {
            graph.addNode(in.nextInt() - 1, in.nextInt() - 1);
        }
        graph.findShortPath(start, finish, out);
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
            adjacencyList.get(secondNode).add(firstNode);
        }

        public void findShortPath(int source, int destination, final PrintWriter out) {
            Map<Integer, Integer> mapOfEdges = new HashMap<>();
            Map<Integer, Boolean> mapOfVisited = new HashMap<>();
            bfs(mapOfEdges, mapOfVisited, source, destination);
            mapOfEdges.put(source, -1);
            if (!mapOfVisited.containsKey(destination)) {
                out.println(-1);
                return;
            }
            int currentNode = destination;
            int pathLength = 0;
            StringBuilder sb = new StringBuilder();
            while (currentNode != -1) {
                sb.append(currentNode + 1);
                pathLength++;
                currentNode = mapOfEdges.get(currentNode);
                if (currentNode != -1) {
                    sb.append(" ");
                }
            }
            out.println(pathLength - 1);
            out.println(sb.reverse());
        }
        private void bfs(Map<Integer, Integer> mapOfEdges, Map<Integer, Boolean> mapOfVisited, int source, int destination) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(source);
            mapOfVisited.put(source, true);
            while (!queue.isEmpty()) {
                int node = queue.remove();
                if (node == destination) {
                    return;
                }
                for (int edge: adjacencyList.get(node)) {
                    if (!mapOfVisited.containsKey(edge)) {
                        mapOfVisited.put(edge, true);
                        mapOfEdges.put(edge, node);
                        queue.add(edge);
                    }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
