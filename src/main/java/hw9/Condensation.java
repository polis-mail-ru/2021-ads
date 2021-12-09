package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// https://www.eolymp.com/ru/submissions/10083789
public class Condensation {

    private static final List<Integer> vertexesQueue = new LinkedList<>();
    private static final Map<Integer, List<Integer>> connections = new HashMap<>();

    private static Map<Integer, List<Integer>> adjacencyList;
    private static Map<Integer, List<Integer>> transAdjacencyList;
    private static boolean[] visitedVertexes;
    private static int n;
    private static int m;
    private static int curConnectivityComponentOrdinal = 1;
    private static int[] connectivityComponents;

    private static void solve(final SolveTemplate.FastScanner in, final PrintWriter out) {
        initialize(in);
        topsort();
        refreshVisitedVertexes();
        numberConnectivityComponents();
        formCondensationGraph();
        countAndPrintCondensedEdgesNumber(out);
    }

    private static void initialize(final SolveTemplate.FastScanner in) {
        n = in.nextInt();
        m = in.nextInt();
        visitedVertexes = new boolean[n + 1];
        connectivityComponents = new int[n + 1];
        adjacencyList = new HashMap<>(n);
        transAdjacencyList = new HashMap<>(n);
        formOrgraphAndTransorgraph(in);
    }

    private static void topsort() {
        for (int i = 1; i < n + 1; i++) {
            if (!visitedVertexes[i])
                Utils.dfs(i, visitedVertexes, adjacencyList, vertexesQueue);
        }
        Collections.reverse(vertexesQueue);
    }

    private static void refreshVisitedVertexes() {
        Arrays.fill(visitedVertexes, false);
    }

    private static void numberConnectivityComponents() {
        for (int v: vertexesQueue) {
            if (!visitedVertexes[v]) {
                condensate(v);
                ++curConnectivityComponentOrdinal;
            }
        }
    }

    private static void formCondensationGraph() {
        for (Map.Entry<Integer, List<Integer>> vertex: adjacencyList.entrySet()) {
            for (int adjacentVertex: vertex.getValue()) {
                if (connectivityComponents[adjacentVertex] != connectivityComponents[vertex.getKey()]) {
                    Utils.matchAdjacentVertexes(connectivityComponents[adjacentVertex],
                        connectivityComponents[vertex.getKey()], connections);
                }
            }
        }
    }

    private static void countAndPrintCondensedEdgesNumber(final PrintWriter out) {
        AtomicInteger nEdgesInGraphCondensation = new AtomicInteger();
        connections.forEach((k, v) -> nEdgesInGraphCondensation.addAndGet(v.size()));
        out.print(nEdgesInGraphCondensation.get());
    }

    private static void formOrgraphAndTransorgraph(final SolveTemplate.FastScanner in) {
        for (int i = 0; i < m; ++i) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();

            Utils.matchAdjacentVertexes(v1, v2, adjacencyList);
            Utils.matchAdjacentVertexes(v2, v1, transAdjacencyList);
        }
    }

    private static void condensate(int v) {
        visitedVertexes[v] = true;
        connectivityComponents[v] = curConnectivityComponentOrdinal;
        for (int curV: transAdjacencyList.getOrDefault(v, Collections.emptyList())) {
            if (!visitedVertexes[curV])
                condensate(curV);
        }
    }

    public static void main(final String[] arg) {
        final SolveTemplate.FastScanner in = new SolveTemplate.FastScanner(System.in);
        try (PrintWriter out = SolveTemplate.createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }

}
