package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.*;

// https://www.eolymp.com/ru/submissions/10069083
public class Topsort {

    private static final List<Integer> vertexesQueue = new LinkedList<>();

    private static Map<Integer, List<Integer>> adjacencyList;
    private static boolean[] visitedVertexes;
    private static byte[] vertexesColors;
    private static int n;

    private static void solve(final SolveTemplate.FastScanner in, final PrintWriter out) {
        initialize(in);
        int result = topsort();
        if (result == Utils.FAIL) {
            System.out.println(Utils.FAIL);
        } else {
            Utils.printVertexes(out, vertexesQueue);
        }
    }

    private static void initialize(final SolveTemplate.FastScanner in) {
        n = in.nextInt();
        int m = in.nextInt();
        visitedVertexes = new boolean[n + 1];
        vertexesColors = new byte[n + 1];
        adjacencyList = new HashMap<>(n);
        Arrays.fill(vertexesColors, Utils.Vertex.WHITE);
        for (int i = 0; i < m; i++) {
            Utils.matchAdjacentVertexes(in.nextInt(), in.nextInt(), adjacencyList);
        }
    }

    private static int topsort() {
        for (int i = 1; i < n + 1; i++) {
            if (hasCycle(i))
                return Utils.FAIL;
            if (!visitedVertexes[i])
                Utils.dfs(i, visitedVertexes, adjacencyList, vertexesQueue);
        }
        Collections.reverse(vertexesQueue);
        return 0;
    }

    public static boolean hasCycle(int v) {
        boolean hasCycle = false;
        vertexesColors[v] = Utils.Vertex.GRAY;
        for (int childV: adjacencyList.getOrDefault(v, Collections.emptyList())) {
            if (vertexesColors[childV] == Utils.Vertex.WHITE && hasCycle(childV)
                    || vertexesColors[childV] == Utils.Vertex.GRAY) {
                hasCycle = true;
                break;
            }
        }
        vertexesColors[v] = Utils.Vertex.BLACK;
        return hasCycle;
    }

    public static void main(final String[] arg) {
        final SolveTemplate.FastScanner in = new SolveTemplate.FastScanner(System.in);
        try (PrintWriter out = SolveTemplate.createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }

}
