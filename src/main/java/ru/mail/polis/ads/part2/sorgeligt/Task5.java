package ru.mail.polis.ads.part2.sorgeligt;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n1 = in.nextInt();
        int[] firstArray = new int[n1];
        for (int i = 0; i < n1; i++) {
            firstArray[i] = in.nextInt();
        }
        final int n2 = in.nextInt();
        int[] secondArray = new int[n2];
        for (int i = 0; i < n2; i++) {
            secondArray[i] = in.nextInt();
        }
        IntegerQuickSort.sort(firstArray, 0, firstArray.length);
        IntegerQuickSort.sort(secondArray, 0, secondArray.length);
        if (countUniqueNumbers(firstArray) != countUniqueNumbers(secondArray)) {
            out.println("NO");
            return;
        }
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (firstArray[i] != secondArray[j]) {
                out.println("NO");
                return;
            }
            while (i + 1 < n1 && firstArray[i] == firstArray[i + 1]) {
                i++;
            }
            while (j + 1 < n2 && secondArray[j] == secondArray[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        out.println(i == n1 && j == n2 ? "YES" : "NO");
    }

    public static int countUniqueNumbers(int[] arr) {
        int cnt = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                cnt++;
            }
        }
        return cnt;
    }

    static class IntegerQuickSort {
        public static void sort(int[] arr, int begin, int end) {
            if (begin >= end - 1) {
                return;
            }
            final int i = partition(arr, begin, end);
            sort(arr, begin, i);
            sort(arr, i + 1, end);
        }

        private static int partition(int[] arr, int begin, int end) {
            final int pivot = arr[begin];
            int i = begin;
            for (int j = begin + 1; j < end; j++) {
                if (pivot >= arr[j]) {
                    swap(arr, ++i, j);
                }
            }
            swap(arr, i, begin);
            return i;
        }

        public static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
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