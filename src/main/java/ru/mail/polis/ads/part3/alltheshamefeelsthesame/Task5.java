package ru.mail.polis.ads.part3.alltheshamefeelsthesame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/9485192

public class Task5 {

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

    private static void swap(int idx1, int idx2, int[] arr) {
        int swap;
        swap = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = swap;
    }

    public static void sink(int idx, int[] arr, int size) {
        int pos = idx;
        while (2 * pos < size) {
            int max = 2 * pos;
            if (max + 1 < size && arr[max + 1] > arr[max]) {
                max++;
            }
            if (arr[pos] >= arr[max]) {
                break;
            }

            swap(pos, max, arr);
            pos = pos * 2 + max % 2;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N + 1];

        for (int i = 1; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = N / 2; i > 0; i--) {
            sink(i, arr, arr.length);
        }

        for (int i = N; i > 1; i--) {
            swap(1, i, arr);
            sink(1, arr, i);
        }

        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
