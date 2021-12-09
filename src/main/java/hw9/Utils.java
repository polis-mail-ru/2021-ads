package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.*;

public class Utils {

    public static final Byte FAIL = -1;

    public static class Vertex {
        public static final Byte NOT_EXIST = -1;
        public static final Byte WHITE = 0;
        public static final Byte GRAY = 1;
        public static final Byte BLACK = 2;
    }

    public static void readEdges(SolveTemplate.FastScanner in, int m, Map<Integer, List<Integer>> adjacencyList) {
        for (int i = 0; i < m; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();

            if (v1 != v2) {
                Utils.matchAdjacentVertexes(v1, v2, adjacencyList);
                Utils.matchAdjacentVertexes(v2, v1, adjacencyList);
            }
        }
    }

    public static void matchAdjacentVertexes(int v1, int v2, Map<Integer, List<Integer>> adjacencyList) {
        adjacencyList.compute(v1, (v, adjacentVertexes) -> {
            if (adjacentVertexes == null)
                adjacentVertexes = new LinkedList<>();
            adjacentVertexes.add(v2);
            return adjacentVertexes;
        });
    }

    public static void printVertexes(final PrintWriter out, List<Integer> vertexes) {
        vertexes.forEach(v -> out.print(v + " "));
    }

    public static void dfs(int v, boolean[] visitedVertexes,
            Map<Integer, List<Integer>> adjacencyList, List<Integer> vertexesQueue) {
        visitedVertexes[v] = true;
        for (int adjacentVertex: adjacencyList.getOrDefault(v, Collections.emptyList())) {
            if (!visitedVertexes[adjacentVertex])
                dfs(adjacentVertex, visitedVertexes, adjacencyList, vertexesQueue);
        }
        vertexesQueue.add(v);
    }

}
