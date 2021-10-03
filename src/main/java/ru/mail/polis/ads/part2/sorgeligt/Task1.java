package ru.mail.polis.ads.part2.sorgeligt;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Participant> participants = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            final int id = in.nextInt();
            final int score = in.nextInt();
            participants.add(new Participant(id, score));
        }
        MergeSort.sort(participants, 0, participants.size(), (o1, o2) -> {
            if (o1.getScore() < o2.getScore()) {
                return -1;
            } else if (o1.getScore() > o2.getScore()) {
                return 1;
            } else {
                return o2.getId() - o1.getId();
            }
        });
        participants.forEach(x -> out.println(x.getId() + " " + x.getScore()));
    }

    static class MergeSort {
        public static <T> void sort(List<T> array, int begin, int end, Comparator<T> cmp) {
            ArrayList<T> auxiliaryArray = new ArrayList<>(end - begin);
            sort(array, begin, end, auxiliaryArray, cmp);
        }

        private static <T> void sort(List<T> array, int begin, int end, ArrayList<T> utilityArray, Comparator<T> cmp) {
            if (begin >= end - 1) {
                return;
            }
            final int mid = begin + ((end - begin) >> 1);
            sort(array, begin, mid, utilityArray, cmp);
            sort(array, mid, end, utilityArray, cmp);
            merge(array, begin, mid, end, utilityArray, cmp);
        }

        private static <T> void merge(List<T> array, int begin, int mid, int end,
                                      ArrayList<T> utilityArray, Comparator<T> cmp) {
            utilityArray.clear();
            int l = begin;
            int r = mid;
            while (l < mid && r < end) {
                if (cmp.compare(array.get(r), array.get(l)) >= 0) {
                    utilityArray.add(array.get(r++));
                } else {
                    utilityArray.add(array.get(l++));
                }
            }
            while (l < mid) {
                utilityArray.add(array.get(l++));
            }
            while (r < end) {
                utilityArray.add(array.get(r++));
            }
            for (int k = begin, m = 0; k < end; k++, m++) {
                array.set(k, utilityArray.get(m));
            }
        }
    }

    private static class Participant {
        private final int id;
        private final int score;

        public Participant(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
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