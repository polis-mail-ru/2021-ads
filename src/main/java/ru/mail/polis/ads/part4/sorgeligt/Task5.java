package ru.mail.polis.ads.part4.sorgeligt;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9576822
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>(n);
        /* Используется List для взаимодействия с реализацией общего вида,
            но я понимаю, что примитивы быстрее и легче, и при более жестких
             ограничениях задачи необходимо их использование */
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        CountInversion<Integer> listInversion = new CountInversion<>(list);
        final int answer = listInversion.countInversion(0, list.size(), Integer::compareTo);
        out.println(answer);
    }

    static class CountInversion<T> {
        private final ArrayList<T> list;
        private int countInversion = 0;

        public CountInversion(ArrayList<T> listForCounting) {
            list = listForCounting;
        }

        public int countInversion(int begin, int end, Comparator<T> cmp) {
            ArrayList<T> auxiliaryArray = new ArrayList<>(end - begin);
            ArrayList<T> copyList = new ArrayList<>(list);
            countInversion(copyList, begin, end, auxiliaryArray, cmp);
            return countInversion;
        }

        private void countInversion(List<T> list, int begin, int end, ArrayList<T> utilityArray, Comparator<T> cmp) {
            if (begin >= end - 1) {
                return;
            }
            final int mid = begin + ((end - begin) >> 1);
            countInversion(list, begin, mid, utilityArray, cmp);
            countInversion(list, mid, end, utilityArray, cmp);
            countSplitInversion(list, begin, mid, end, utilityArray, cmp);
        }

        private void countSplitInversion(List<T> list, int begin, int mid, int end,
                                         ArrayList<T> utilityArray, Comparator<T> cmp) {
            utilityArray.clear();
            int l = begin;
            int r = mid;
            while (l < mid && r < end) {
                if (cmp.compare(list.get(l), list.get(r)) >= 0) {
                    utilityArray.add(list.get(r++));
                    countInversion += mid - l;
                } else {
                    utilityArray.add(list.get(l++));
                }
            }
            while (l < mid) {
                utilityArray.add(list.get(l++));
            }
            while (r < end) {
                utilityArray.add(list.get(r++));
            }
            for (int k = begin, m = 0; k < end; k++, m++) {
                list.set(k, utilityArray.get(m));
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