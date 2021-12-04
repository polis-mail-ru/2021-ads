package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class CheckForCycles {
    static Map<Integer, Set<Integer>> edges;
    static int[] cl, p;
    static int cycle_end, cycle_st = -1;
    static int n;

    //https://www.eolymp.com/ru/submissions/10033907
    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        cl = new int[n + 1];
        p = new int[n + 1];
        edges = new HashMap<>();

        Arrays.fill(cl, 0);
        Arrays.fill(p, -1);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int to = in.nextInt();
            if (a != to) {
                edges.compute(a, (k, v) -> {
                    if (v == null) {
                        v = new HashSet<>();
                    }
                    v.add(to);
                    return v;
                });
                edges.compute(to, (k, v) -> {
                    if (v == null) {
                        v = new HashSet<>();
                    }
                    v.add(a);
                    return v;
                });
            }
        }

        int minres = Integer.MAX_VALUE;
        for (Integer integer : edges.keySet()) {
            if (dfs(integer)) {
                int min = cycle_st;
                int i = cycle_end;
                while (i != cycle_st) {
                    if (min > i) {
                        min = i;
                    }
                    i = p[i];
                }
                if (min < minres) {
                    minres = min;
                }
                Arrays.fill(cl, 0);
            }

        }

        if (minres == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.print(minres);
        }
    }

    static boolean dfs(int v) {
        cl[v] = 1;
        for (int i : edges.getOrDefault(v, Collections.emptySet())) {
            if (i == p[v])
                continue;
            if (cl[i] == 0) {
                p[i] = v;
                if (dfs(i)) return true;
            } else if (cl[i] == 1) {
                cycle_end = v;
                cycle_st = i;
                return true;
            }
        }
        cl[v] = 2;
        return false;
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
}