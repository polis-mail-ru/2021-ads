package ru.mail.polis.ads.part2.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Task1 {

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

    public static void swap(int i, int minIdx, int[][] arr) {
        if (i == minIdx) {
            return;
        }
        int[] current = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = current;
    }

    public static void sort(int[][] arr, Compare compare) {
        int maxIdx;

        for (int i = 0; i < arr.length - 1; i++) {
            maxIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (compare.compare(arr[maxIdx], arr[j]) < 0) {
                    maxIdx = j;
                }
            }
            swap(i, maxIdx, arr);
        }

        for (int[] ints : arr) {
            System.out.println(ints[0] + " " + ints[1]);
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
        }

        sort(arr, new Compare());
    }

    static class Compare implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        }
    }
}


