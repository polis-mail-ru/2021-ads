package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static class Edge {
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    private static int sizeV;

    private static void solve(final FastScanner in) {
        // Size
        sizeV = in.nextInt();
        int sizeE = in.nextInt();
        // List of all graph's edges (E) of input graph G=(V,E)
        List<Edge> listOfEdges = new LinkedList<>();
        for (int i = 0; i < sizeE; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            listOfEdges.add(new Edge(u, v, weight));
        }
        // Root node
        int s = 0;

        Integer[] d = new Integer[sizeV];
        int[] p = new int[sizeV];

        if (bellmanFordShortestPaths(listOfEdges, s, d, p)) {
            System.out.println("A negative-weight cycle is reachable from the source vertex");
            return;
        }

        printDists(d);
    }

    /* Input:
     *  listOfEdges - list of all graph's edges (E) of input graph G=(V,E)
     *  s - start node s ∈ V
     * Output:
     *  bool - if there are any negative cycles
     *  d[v] = δ(s, v)  v ∈ V
     *  p[v] - tree of the shortest paths v ∈ V
     */
    private static boolean bellmanFordShortestPaths(List<Edge> listOfEdges, int s, Integer[] d, int[] p) {
        for (int i = 0; i < sizeV; i++) {
            p[i] = -1;
        }
        d[s] = 0;
        // x - contains last edited node v, (u,v)∈E, if no edited => x=-1
        boolean wasEdited = false;
        for (int i = 0; i < sizeV; i++) {
            wasEdited = false;
            for (Edge e : listOfEdges) {
                if (d[e.u] != null) {
                    if (d[e.v] == null || d[e.v] > d[e.u] + e.weight) {
                        d[e.v] = d[e.u] + e.weight;
                        p[e.v] = e.u;
                        wasEdited = true;
                    }
                }
            }
            if (!wasEdited) {
                break;
            }
        }
        return wasEdited;
    }

    private static void printDists(Integer[] d) {
        for (int i = 0; i < sizeV; i++) {
            System.out.print((d[i] == null ? 30000 : d[i]) + " ");
        }
    }

    // Optimized Scanner
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
        try (PrintWriter ignored = new PrintWriter(System.out)) {
            solve(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
