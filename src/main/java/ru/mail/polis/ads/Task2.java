package ru.mail.polis.ads;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
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
        }
        Deque<Integer> list = topologicalSort(listOfAdjacency);
        if (list == null) {
            System.out.println(-1);
            return;
        }
        for (Integer integer : list) {
            System.out.print(integer + 1 + " ");
        }

    }

    private static @Nullable Deque<Integer> topologicalSort(List<List<Integer>> listOfAdjacency) {
        List<Color> colors = new ArrayList<>(listOfAdjacency.size());
        for (int i = 0; i < listOfAdjacency.size(); i++) {
            colors.add(Color.WHITE);
        }
        Deque<Integer> ans = new LinkedList<>();
        for (int v = 0; v < listOfAdjacency.size(); v++) {
            if(colors.get(v) == Color.WHITE) {
                if (!dfs(listOfAdjacency, colors, ans,  v) ) {
                    return null;
                }
            }
        }
        return ans;
    }

    private static boolean dfs(List<List<Integer>> listOfAdjacency, List<Color> colors, Deque<Integer> ans, int u) {
        colors.set(u, Color.GRAY);
        for (Integer v : listOfAdjacency.get(u)) {
            switch (colors.get(v)) {
                case WHITE : {
                    if (!dfs(listOfAdjacency, colors, ans, v)) {
                        return false;
                    }
                    break;
                }
                case GRAY: {
                    return false;
                }
            }
        }
        ans.addFirst(u);
        colors.set(u, Color.BLACK);
        return true;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}