package ru.mail.polis.ads.part3.DenZhid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task3 {

    private static int NOTHING = -1;

    private static class Heap {
        private int[] arr = new int[2];
        private int size = 0;
        private final boolean isMax;

        public Heap(boolean isMax) {
            this.isMax = isMax;
        }

        private void insert(int x) {
            if (size >= arr.length - 1) {
                int[] newArray = new int[arr.length * 2];
                System.arraycopy(arr, 0, newArray, 0, arr.length);
                arr = newArray;
            }
            arr[++size] = x;
            swim(size);
        }

        public int extract() {
            int max = arr[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        public int top() {
            return arr[1];
        }

        private void swim(int index) {
            int k = index;
            while (k > 1 && (isMax && arr[index] > arr[index / 2] || !isMax && arr[index] < arr[index / 2])) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && (isMax && arr[index] < arr[index + 1] || !isMax && arr[index] > arr[index + 1])) {
                    j++;
                }
                if (isMax && arr[k] >= arr[j] || !isMax && arr[k] <= arr[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(true);
        Heap minHeap = new Heap(false);
        maxHeap.insert(in.nextInt());
        out.println(maxHeap.top());
        String str = in.next();
        while (str != null) {
            int newNumber = Integer.parseInt(str);
            minHeap.insert(newNumber);
            if (minHeap.size - maxHeap.size > 0 || minHeap.top() < maxHeap.top()) {
                maxHeap.insert(minHeap.extract());
            }
            if (minHeap.size - maxHeap.size < 0) {
                minHeap.insert(maxHeap.extract());
            }
            if (minHeap.size == maxHeap.size) {
                out.println(maxHeap.top() + minHeap.top() / 2);
            } else {
                if (maxHeap.size > minHeap.size) {
                    out.println(maxHeap.top());
                } else {
                    out.println(minHeap.top());
                }
            }
            str = in.next();
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return "";
            }
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
