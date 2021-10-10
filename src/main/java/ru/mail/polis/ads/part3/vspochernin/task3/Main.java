package ru.mail.polis.ads.part3.vspochernin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Main {
    private static final int[] maxHeapArray = new int[5000001];
    private static final int[] minHeapArray = new int[5000001];
    private static int maxHeapSize = 0;
    private static int minHeapSize = 0;

    private Main() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void maxHeapSwim(int k) {
        while (k > 1 && maxHeapArray[k] > maxHeapArray[k / 2]) {
            swap(maxHeapArray, k, k / 2);
            k = k / 2;
        }
    }

    private static void minHeapSwim(int k) {
        while (k > 1 && minHeapArray[k] < minHeapArray[k / 2]) {
            swap(minHeapArray, k, k / 2);
            k = k / 2;
        }
    }

    private static void maxHeapSink(int k) {
        while (2 * k <= maxHeapSize) {
            int j = 2 * k;
            if (j < maxHeapSize && maxHeapArray[j] < maxHeapArray[j + 1]) {
                j++;
            }
            if (maxHeapArray[k] >= maxHeapArray[j]) {
                break;
            }
            swap(maxHeapArray, k, j);
            k = j;
        }
    }
    private static void minHeapSink(int k) {
        while (2 * k <= minHeapSize) {
            int j = 2 * k;
            if (j < minHeapSize && minHeapArray[j] > minHeapArray[j + 1]) {
                j++;
            }
            if (minHeapArray[k] <= minHeapArray[j]) {
                break;
            }
            swap(minHeapArray, k, j);
            k = j;
        }
    }

    private static void maxHeapInsert(int x) {
        maxHeapArray[++maxHeapSize] = x;
        maxHeapSwim(maxHeapSize);
    }
    private static void minHeapInsert(int x) {
        minHeapArray[++minHeapSize] = x;
        minHeapSwim(minHeapSize);
    }

    private static int maxHeapExtract() {
        int max = maxHeapArray[1];
        swap(maxHeapArray, 1, maxHeapSize--);
        maxHeapSink(1);
        return max;
    }
    private static int minHeapExtract() {
        int min = minHeapArray[1];
        swap(minHeapArray, 1, minHeapSize--);
        minHeapSink(1);
        return min;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean isSeparatedMedian = true;
        int n;
        int median = 0;
        int[] a = new int[3];
        Scanner newIn = new Scanner(System.in);
        if (newIn.hasNextInt()) {
            median = newIn.nextInt();
            System.out.println(median);
        }
        while (newIn.hasNextInt()) {
            n = newIn.nextInt();
            if (isSeparatedMedian) { // Медиана была отдельным элементом.
                if (n > median) {
                    minHeapInsert(n);
                    maxHeapInsert(median);
                } else {
                    minHeapInsert(median);
                    maxHeapInsert(n);
                }
                median = (maxHeapArray[1] + minHeapArray[1]) / 2;
            } else { // Медиана была средним арифметическим.
                a[0] = maxHeapExtract();
                a[1] = n;
                a[2] = minHeapExtract();
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                if (a[1] > a[2]) {
                    swap(a, 1, 2);
                }
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                maxHeapInsert(a[0]);
                median = a[1];
                minHeapInsert(a[2]);
            }
            isSeparatedMedian = !isSeparatedMedian;
            System.out.println(median);
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