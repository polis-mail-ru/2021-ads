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

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeV = in.nextInt();
        int sizeE = in.nextInt();
        List<List<Integer>> listOfAdjacency = new ArrayList<>(sizeV);
        for (int i = 0; i < sizeV; i++) {
            listOfAdjacency.add(new ArrayList<>());
        }
        int u, v;
        for (int i = 0; i < sizeE; i++) {
            u = in.nextInt() - 1;
            v = in.nextInt() - 1;
            listOfAdjacency.get(u).add(v);
            listOfAdjacency.get(v).add(u);
        }
        Deque<Integer> list = topologicalSort(listOfAdjacency);
        if (list.isEmpty()) {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
        System.out.println(Collections.min(list) + 1);
    }

    private static Deque<Integer> topologicalSort(List<List<Integer>> listOfAdjacency) {
        List<Color> colors = new ArrayList<>(listOfAdjacency.size());
        for (int i = 0; i < listOfAdjacency.size(); i++) {
            colors.add(Color.WHITE);
        }

        Deque<Integer> ans = new LinkedList<>();
        for (int v = 0; v < listOfAdjacency.size(); v++) {
            if (colors.get(v) == Color.WHITE) {
                isNodeVInCycle(listOfAdjacency, colors, ans, v, v);
            }
        }
        return ans;
    }

    private static boolean isNodeVInCycle(List<List<Integer>> listOfAdjacency, List<Color> colors, Deque<Integer> ans, int u, int uParent) {
        boolean isUInCycle = false;
        colors.set(u, Color.GRAY);
        for (Integer v : listOfAdjacency.get(u)) {
            if (v != uParent) {
                switch (colors.get(v)) {
                    case WHITE: {
                        // Если у вершины v дети в цикле
                        if (isNodeVInCycle(listOfAdjacency, colors, ans, v, u)) {
                            // Тогда саму v добавляем в список в список зацикленных вершин
                            ans.add(v);

                            // фраза: если вершина не черная - эквивалентна фразе: если мы не достигли начала цикла,
                            // т.к. обычные(не в цикле) вершины закрашиваются в конце этого цикла, значит мы закрасили их раньше,
                            // то есть своего образа раскручивание стека
                            if (colors.get(u) != Color.BLACK) {
                                isUInCycle = true;
                            }
                        }
                        break;
                    }
                    // Смысл в том что если вершина в цикле, то мы ее отмечаем BLACK раньше времени.
                    // Причем v - это именно ТУДА КУДА мы идем, а u - откуда идем. Своеобразное ориентированное ребро (u,v)
                    case GRAY: {
                        ans.add(v);
                        colors.set(v, Color.BLACK);
                        isUInCycle = true;
                        break;
                    }
                }
            }
        }
        colors.set(u, Color.BLACK);
        return isUInCycle;
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