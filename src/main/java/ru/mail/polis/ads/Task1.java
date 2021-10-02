package ru.mail.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[n][2];
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

        mergeSort(arr, comparator);

        for (int[] pairs : arr) {
            System.out.printf("%d %d%n", pairs[0], pairs[1]);
        }
    }

    private static void mergeSort(int[][] arr, Comparator<int[]> comparator) {
        int[][] temp = new int[arr.length][2];
        mergeSort(arr, temp, 0, arr.length - 1, comparator);
    }

    private static void mergeSort(int[][] arr, int[][] temp, int l, int r, Comparator<int[]> comparator) {
        if (l == r) {
            return;
        }
        int middle = (l + r) / 2;
        mergeSort(arr, temp, l, middle, comparator);
        mergeSort(arr, temp, middle + 1, r, comparator);
        merge(arr, temp, l, middle, r, comparator);
    }

    private static void merge(int[][] arr, int[][] temp, int l, int middle, int r, Comparator<int[]> comparator) {
        int i = l;
        int j = middle + 1;
        for (int k = l; k <= r; k++) {
            int result = comparator.compare(arr[i], arr[j]);
            if (result > 0) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            if (i == middle + 1) {
                for (int m = k + 1; m <= r; m++) {
                    temp[m] = arr[j];
                    j++;
                }
                break;
            }
            if (j == r + 1) {
                for (int m = k + 1; m <= r; m++) {
                    temp[m] = arr[i];
                    i++;
                }
                break;
            }
        }
        for (int k = l; k <= r; k++) {
            arr[k] = temp[k];
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
