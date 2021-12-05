package ru.mail.polis.ads.part9.AirBurn0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/10044767
public final class Week9Test3 {

    private final static byte WHITE = 0;

    private final static byte GRAY = 1;

    private final static byte BLACK = 2;

    private Week9Test3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int v = in.nextInt(), e = in.nextInt();
        List<Integer>[] list = new ArrayList[v];
        for (int i = 0; i < v; ++i) {
            list[i] = new ArrayList<Integer>();
        }

        int tempFrom, tempTo;
        for (int i = 0; i < e; ++i) {
            tempFrom = in.nextInt() - 1;
            tempTo = in.nextInt() - 1;
            list[tempFrom].add(tempTo);
            list[tempTo].add(tempFrom);
        }
        LinkedList<Integer> stack = new LinkedList<>();
        boolean[] cycle = new boolean[v];
        byte[] color = new byte[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            if (color[i] == WHITE) {
                stack.clear();
                stack.add(i);
                dfs(list, stack, color, prev, cycle);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cycle.length; ++i) {
            if (cycle[i] && min > i) {
                min = i;
            }
        }

        if (min == Integer.MAX_VALUE) {
            out.printf("No");
            return;
        }

        out.println("Yes");
        out.println(min + 1);
    }

    private static void dfs(List<Integer>[] list, Deque<Integer> stack, byte[] color, int[] prev, boolean[] cycle) {
        while (!stack.isEmpty()) {
            int u = stack.peek();
            color[u] = GRAY;
            boolean flag = true;

            for (int v : list[u]) {
                if (prev[u] == v) {
                    continue;
                }
                if (color[v] == WHITE) {
                    prev[v] = u;
                    stack.push(v);
                    flag = false;
                    break;
                } else if (color[v] == GRAY) {
                    cycle[v] = true;

                    int forNode = u;
                    while (forNode != v) {
                        cycle[forNode] = true;
                        forNode = prev[forNode];
                        if (cycle[forNode]) {
                            break;
                        }
                    }
                }
            }
            if (flag) {
                stack.pop();
                color[u] = BLACK;
            }
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
