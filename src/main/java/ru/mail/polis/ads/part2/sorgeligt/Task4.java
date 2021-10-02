package ru.mail.polis.ads.part2.sorgeligt;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        QuickSort.sort(list, 0, list.size(), Integer::compareTo);
        out.println(countUniqueNumbers(list));
    }

    public static int countUniqueNumbers(List<Integer> arr) {
        int cnt = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (!arr.get(i).equals(arr.get(i - 1))) {
                cnt++;
            }
        }
        return cnt + 1;
    }

    static class QuickSort {
        public static <T> void sort(List<T> list, int begin, int end, Comparator<T> cmp) {
            if (begin >= end - 1) {
                return;
            }
            final int i = partition(list, begin, end, cmp);
            sort(list, begin, i, cmp);
            sort(list, i + 1, end, cmp);
        }

        private static <T> int partition(List<T> list, int begin, int end, Comparator<T> cmp) {
            final int randIndex = ThreadLocalRandom.current().nextInt(begin, end);
            Collections.swap(list, begin, randIndex);
            final T pivot = list.get(begin);
            int i = begin;
            for (int j = begin + 1; j < end; j++) {
                if (cmp.compare(pivot, list.get(j)) >= 0) {
                    Collections.swap(list, ++i, j);
                }
            }
            Collections.swap(list, i, begin);
            return i;
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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