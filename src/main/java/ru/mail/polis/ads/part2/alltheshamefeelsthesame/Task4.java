package ru.mail.polis.ads.part2.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {

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

    private static void swap(int[] arr, int i, int idx) {
        if (arr[i] == arr[idx]) {
            return;
        }
        arr[i] ^= arr[idx];
        arr[idx] ^= arr[i];
        arr[i] ^= arr[idx];
    }

    private static int partition(int[] arr, int start, int end) {
        int pivotal = arr[start];
        int idx = start;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] <= pivotal) {
                swap(arr, i, ++idx);
            }
        }
        swap(arr, idx, start);
        return idx;
    }

    public static void qSort(int[] arr, int start, int end) {
        if (start >= end - 1) {
            return;
        }

        int from = partition(arr, start, end);
        qSort(arr, start, from);
        qSort(arr, from + 1, end);
    }

    public static int differentCount(int[] arr) {
        int result = 1;
        qSort(arr, 0, arr.length);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(differentCount(arr));
    }
}
