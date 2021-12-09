package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://www.eolymp.com/ru/submissions/10062193
public class ShortestWay {

    private static final List<Integer> shortestWay  = new ArrayList<>();
    private static final Deque<Integer> vertexesQueue = new LinkedList<>();
    private static Map<Integer, List<Integer>> adjacencyList;

    private static int[] vertexesParents;
    private static int[] shortestDistances;
    private static int startV;
    private static int endV;

    private static void solve(final SolveTemplate.FastScanner in, final PrintWriter out) {
        initialize(in);
        bfs(adjacencyList, startV, vertexesParents, shortestDistances);

        if (shortestDistances[endV] == Integer.MAX_VALUE) {
            out.println(Utils.Vertex.NOT_EXIST);
            return;
        }

        long shortestWayDistance = shortestDistances[endV];
        computeShortestWay(endV);
        out.println(shortestWayDistance);
        Utils.printVertexes(out, shortestWay);
    }

    private static void initialize(final SolveTemplate.FastScanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        startV = in.nextInt();
        endV = in.nextInt();
        adjacencyList = new HashMap<>(n);
        shortestDistances = new int[n + 1];
        vertexesParents = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            shortestDistances[i] = Integer.MAX_VALUE;
            vertexesParents[i] = Utils.Vertex.NOT_EXIST;
        }
        shortestDistances[startV] = 0;
        Utils.readEdges(in, m, adjacencyList);
    }

    private static void bfs(Map<Integer, List<Integer>> adjacencyList, int startV,
            int[] vertexesParents, int[] shortestDistances) {
        ShortestWay.vertexesQueue.addLast(startV);
        while (!ShortestWay.vertexesQueue.isEmpty()) {
            int v = ShortestWay.vertexesQueue.poll();
            for (int u: adjacencyList.getOrDefault(v, Collections.emptyList())) {
                if (shortestDistances[u] > shortestDistances[v] + 1) {
                    vertexesParents[u] = v;
                    shortestDistances[u] = shortestDistances[v] + 1;
                    ShortestWay.vertexesQueue.add(u);
                }
            }
        }
    }

    private static void computeShortestWay(int v) {
        while (v != Utils.Vertex.NOT_EXIST) {
            shortestWay.add(v);
            v = vertexesParents[v];
        }
        Collections.reverse(shortestWay);
    }

    public static void main(final String[] arg) {
        final SolveTemplate.FastScanner in = new SolveTemplate.FastScanner(System.in);
        try (PrintWriter out = SolveTemplate.createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }

}
