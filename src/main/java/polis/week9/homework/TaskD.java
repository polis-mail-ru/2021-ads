package polis.week9.homework;

import java.io.*;
import java.util.*;

public class TaskD {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nodesCount = in.nextInt();
        int edgesCount = in.nextInt();

        // Нумерация вершин начинается с 1. 0й элемент массива смежности использовать не будем
        List<Integer>[] graph = new LinkedList[nodesCount + 1];
        List<Integer>[] revGraph = new LinkedList[nodesCount + 1];

        for (int i = 0; i < edgesCount; i++) {
            int nodeFrom = in.nextInt();
            int nodeTo = in.nextInt();

            if (graph[nodeFrom] == null) {
                graph[nodeFrom] = new LinkedList<>();
            }
            if (revGraph[nodeTo] == null) {
                revGraph[nodeTo] = new LinkedList<>();
            }

            if (nodeFrom == nodeTo || graph[nodeFrom].contains(nodeTo)) {
                continue;
            }

            graph[nodeFrom].add(nodeTo);
            revGraph[nodeTo].add(nodeFrom);
        }

        boolean[] visited = new boolean[graph.length];
        Arrays.fill(visited, false);
        List<Integer> top = new ArrayList<>();
        for (int node = 1; node < graph.length; node++) {
            if (!visited[node]) {
                dfsGraph(node, graph, visited, top);
            }
        }

        int[] grNums = new int[graph.length];
        Arrays.fill(grNums, -1);
        int currGrNum = 0;
        Collections.reverse(top);
        for (int node : top) {
            if (grNums[node] == -1) {
                dfsRevGraph(node, currGrNum++, revGraph, grNums);
            }
        }

        Set<Pair> edgesUniq = new HashSet<>();
        for (int node = 1; node < graph.length; node++) {
            if (graph[node] != null) {
                for (int connNode : graph[node]) {
                    if (grNums[node] != grNums[connNode]) {
                        edgesUniq.add(new Pair(grNums[node], grNums[connNode]));
                    }
                }
            }
        }

        out.println(edgesUniq.size());
    }

    private static void dfsGraph(int node, List<Integer>[] graph, boolean[] visited, List<Integer> top) {
        visited[node] = true;
        if (graph[node] != null) {
            for (int connNode : graph[node]) {
                if (!visited[connNode]) {
                    dfsGraph(connNode, graph, visited, top);
                }
            }
        }
        top.add(node);
    }

    private static void dfsRevGraph(int node, int grNum, List<Integer>[] revGraph, int[] grNums) {
        grNums[node] = grNum;
        if (revGraph[node] != null) {
            for (int connNode : revGraph[node]) {
                if (grNums[connNode] == -1) {
                    dfsRevGraph(connNode, grNum, revGraph, grNums);
                }
            }
        }
    }

    private static class Pair {
        public int first;
        public int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
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
