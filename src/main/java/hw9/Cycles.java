package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.*;

// https://www.eolymp.com/ru/submissions/10067336
public class Cycles {

    private static Map<Integer, List<Integer>> adjacencyList;
    private static byte[] vertexesColors;
    private static int[] vertexesParents;
    private static int curCycleStartVertex = Utils.Vertex.NOT_EXIST;
    private static int curCycleEndVertex = Utils.Vertex.NOT_EXIST;

    private static void solve(final SolveTemplate.FastScanner in, final PrintWriter out) {
        initialize(in);
        int minVertexInCycle = findMinVertexInCycle();
        printResult(minVertexInCycle, out);
    }

    private static void initialize(final SolveTemplate.FastScanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        vertexesColors = new byte[n + 1];
        vertexesParents = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            vertexesColors[i] = Utils.Vertex.WHITE;
            vertexesParents[i] = Utils.Vertex.NOT_EXIST;
        }
        adjacencyList = new HashMap<>(n);
        Utils.readEdges(in, m, adjacencyList);
    }

    private static int findMinVertexInCycle() {
        int minVertexInCycle = Integer.MAX_VALUE;
        for (int vertex: adjacencyList.keySet()) {
            if (dfs(vertex)) {
                int curMinVertexInCycle = curCycleStartVertex;
                int i = curCycleEndVertex;

                while (i != curCycleStartVertex) {
                    curMinVertexInCycle = Math.min(curMinVertexInCycle, i);
                    i = vertexesParents[i];
                }
                minVertexInCycle = Math.min(minVertexInCycle, curMinVertexInCycle);
            }
            Arrays.fill(vertexesColors, Utils.Vertex.WHITE);
        }
        return minVertexInCycle;
    }

    private static void printResult(int minVertexInCycle, final PrintWriter out) {
        if (minVertexInCycle == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.print(minVertexInCycle);
        }
    }

    // Returns does cycle exist for specified vertex
    private static boolean dfs(int v) {
        vertexesColors[v] = Utils.Vertex.GRAY;
        for (int i: adjacencyList.getOrDefault(v, Collections.emptyList())) {
            if (i == vertexesParents[v])
                continue;
            if (vertexesColors[i] == Utils.Vertex.WHITE) {
                vertexesParents[i] = v;
                if (dfs(i))
                    return true;
            } else if (vertexesColors[i] == Utils.Vertex.GRAY) {
                curCycleStartVertex = i;
                curCycleEndVertex = v;
                return true;
            }
        }
        vertexesColors[v] = Utils.Vertex.BLACK;
        return false;
    }

    public static void main(final String[] arg) {
        final SolveTemplate.FastScanner in = new SolveTemplate.FastScanner(System.in);
        try (PrintWriter out = SolveTemplate.createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }

}
