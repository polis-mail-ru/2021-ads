package hw9;

import ru.mail.polis.ads.SolveTemplate;

import java.io.PrintWriter;
import java.util.*;

// https://www.eolymp.com/ru/submissions/10069083
public class Topsort {

    private static final List<Integer> vertexesQueue = new LinkedList<>();

    private static int n;
    private static boolean[] visitedVertexes;
    private static byte[] vertexesColors;
    private static Map<Integer, List<Integer>> adjacencyList;

    private static void solve(final SolveTemplate.FastScanner in, final PrintWriter out) {
        initialize(in);

        int result = Utils.topsortWithCycles(n, visitedVertexes, vertexesColors, adjacencyList, vertexesQueue);
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

    public static void main(final String[] arg) {
        final SolveTemplate.FastScanner in = new SolveTemplate.FastScanner(System.in);
        try (PrintWriter out = SolveTemplate.createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }

}
