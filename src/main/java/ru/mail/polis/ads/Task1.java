package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        Comparator<int[]> comparator = (pair1, pair2) -> {
            int pointsDiffer = Integer.compare(pair1[1], pair2[1]);
            if (pointsDiffer == 0) {
                int idNumberDiffer = Integer.compare(pair1[0], pair2[0]);
                if (idNumberDiffer < 0) {
                    return 1;
                }
                return -1;
            }
            return pointsDiffer;//-1 if pair[1]<pair[2],1 if pair[1]>pair[2]
        };

        arr = mergeSort(arr, comparator, 0, arr.length - 1);

        for (int[] pairs : arr) {
            System.out.printf("%d %d%n", pairs[0], pairs[1]);
        }
    }

    private static int[][] mergeSort(int[][] arr, Comparator<int[]> comparator, int l, int r) {
        if (l == r) return new int[][]{arr[l]};
        int middle = (l + r) / 2;
        int[][] left = mergeSort(arr, comparator, l, middle);
        int[][] right = mergeSort(arr, comparator, middle + 1, r);
        return merge(left, right, comparator);
    }

    private static int[][] merge(int[][] arr1, int[][] arr2, Comparator<int[]> comparator) {
        int[][] result = new int[arr1.length + arr2.length][2];
        int i = 0, j = 0;
        while (true) {
            int res = comparator.compare(arr1[i], arr2[j]);
            if (res > 0) {
                result[i + j] = arr1[i];
                i++;
            } else {
                result[i + j] = arr2[j];
                j++;
            }
            if (i == arr1.length) {
                for (int k = i + j; k < result.length; k++) {
                    result[k] = arr2[j];
                    j++;
                }
                return result;
            }
            if (j == arr2.length) {
                for (int k = i + j; k < result.length; k++) {
                    result[k] = arr1[i];
                    i++;
                }
                return result;
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
