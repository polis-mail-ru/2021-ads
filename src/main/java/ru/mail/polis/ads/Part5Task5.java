package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Part5Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }
        List<List<Integer>> result = permute(array);
        for (List<Integer> list : result) {
            out.println(listToString(list));
        }
    }

    private static List<List<Integer>> permute(int[] array) {
        List<List<Integer>> permutations = new ArrayList<>();
        backtrackPermute(permutations, new ArrayList<>(), array);
        return permutations;
    }

    private static void backtrackPermute(List<List<Integer>> permutations, List<Integer> temp, int[] array) {
        if (temp.size() == array.length) {
            permutations.add(new ArrayList<>(temp));
            return;
        }
        for (int j : array) {
            if (temp.contains(j)) {
                continue;
            }
            temp.add(j);
            backtrackPermute(permutations, temp, array);
            temp.remove(temp.size() - 1);
        }
    }

    private static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int n : list) {
            sb.append(n).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
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
