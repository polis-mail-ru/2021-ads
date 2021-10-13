package ru.mail.polis.ads;

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
public final class C {
    private C() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        Scanner scanner = new Scanner(System.in);
        int med = -1;
        boolean isEven = true;
        int[] maxHeap = new int[1000001];//root min(max elements)
        int[] minHeap = new int[1000001];//root max(min elements)
        int minSize = 1;
        int maxSize = 1;
        //int num=-1;
        while (scanner.hasNextInt() && maxSize == 1) {
            int num = scanner.nextInt();
            if (minSize == 1) {
                minHeap[minSize] = num;
                minSize++;
                out.println(num);
            } else {
                if (minHeap[1] > num) {
                    maxHeap[1] = minHeap[1];
                    minHeap[1] = num;
                } else {
                    maxHeap[1] = num;
                }
                maxSize++;
                med = (maxHeap[1] + minHeap[1]) / 2;
                out.println(med);
            }
        }
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (isEven) {
                if (num >= minHeap[1] && num <= maxHeap[1]) {
                    med = num;
                } else if (num > maxHeap[1]) {
                    med = maxHeap[1];
                    maxHeap[1] = num;
                    down(maxHeap, 1, maxSize - 1, false);
                } else {
                    //addElement(num,maxHeap,maxSize-1,false);
                    // maxSize++;
                    med = minHeap[1];
                    minHeap[1] = num;
                    down(minHeap, 1, minSize - 1, true);
                }
            } else {
                if (num < med) {
                    addElement(med, maxHeap, maxSize - 1, false);
                    addElement(num, minHeap, minSize - 1, true);
                } else {
                    addElement(num, maxHeap, maxSize - 1, false);
                    addElement(med, minHeap, minSize - 1, true);
                }
                maxSize++;
                minSize++;
                med = (maxHeap[1] + minHeap[1]) / 2;
            }
            out.println(med);
            isEven = !isEven;
        }
    }


    private static void down(int[] heap, int k, int count, boolean isMin) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && ((isMin && heap[j] < heap[j + 1]) || ((!isMin && heap[j] > heap[j + 1]))))
                j++;
            if ((isMin && heap[k] >= heap[j]) || (!isMin && heap[k] <= heap[j]))
                break;
            swap(heap, k, j);
            k = j;
        }
    }

    private static void addElement(int num, int[] heap, int count, boolean isMin) {
        heap[count + 1] = num;
        up(heap, count + 1, isMin);
    }

    private static void up(int[] heap, int i, boolean isMin) {
        int ind = (i);
        while (ind > 1 && ((isMin && heap[ind] > heap[ind / 2]) || (!isMin && heap[ind] <= heap[ind / 2]))) {
            swap(heap, ind, ind / 2);
            ind /= 2;
        }
    }

    private static void swap(int[] heap, int ind, int i) {
        int tmp = heap[ind];
        heap[ind] = heap[i];
        heap[i] = tmp;
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
