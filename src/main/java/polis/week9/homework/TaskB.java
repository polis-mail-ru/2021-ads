package polis.week9.homework;

import java.io.*;
import java.util.*;

public class TaskB {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nodesCount = in.nextInt();
        int edgesCount = in.nextInt();

        // Нумерация вершин начинается с 1. 0й элемент массива смежности использовать не будем
        List<Integer>[] adjacencyList = new LinkedList[nodesCount + 1];
        for (int i = 0; i < edgesCount; i++) {
            int nodeFrom = in.nextInt();
            int nodeTo = in.nextInt();

            if (adjacencyList[nodeFrom] == null) {
                adjacencyList[nodeFrom] = new LinkedList<>();
            }

            adjacencyList[nodeFrom].add(nodeTo);
        }

        Stack<Integer> topSorted = topologicalSort(adjacencyList);

        while (!topSorted.isEmpty()) {
            out.print(topSorted.pop() + " ");
        }
    }

    private static Stack<Integer> topologicalSort(List<Integer>[] adjacencyList) {
        Color[] colors = new Color[adjacencyList.length];
        Arrays.fill(colors, Color.WHITE);
        Stack<Integer> answer = new Stack<>();

        for (int node = 1; node < adjacencyList.length; node++) {
            if (colors[node].equals(Color.WHITE)) {
                dfsTopological(node, adjacencyList, colors, answer);
            }
        }

        return answer;
    }

    private static void dfsTopological(int node, List<Integer>[] adjacencyList, Color[] colors, Stack<Integer> answer) {
        colors[node] = Color.GRAY;

        if (adjacencyList[node] != null) {
            for (int connNode : adjacencyList[node]) {
                if (colors[connNode].equals(Color.WHITE)) {
                    dfsTopological(connNode, adjacencyList, colors, answer);
                } else if (colors[connNode].equals(Color.GRAY)) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }

        answer.push(node);
        colors[node] = Color.BLACK;
    }

    private enum Color {
        GRAY,
        BLACK,
        WHITE
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

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}