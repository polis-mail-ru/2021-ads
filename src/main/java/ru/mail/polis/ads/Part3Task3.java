package ru.mail.polis.ads;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Part3Task3 {

    private static final int MAX_SIZE = 1_000_000;


    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap minHeap = new Heap(MAX_SIZE / 2 , Heap.HeapType.MAX);
        Heap maxHeap = new Heap(MAX_SIZE / 2, Heap.HeapType.MIN);

        int median = 0;

        int current = in.nextInt();
        minHeap.insert(current);
        maxHeap.insert(current);
        System.out.println(current);


        boolean isOdd = true;
        String currentInString;

        while (!(currentInString = in.next()).equals("")) {
            current = Integer.parseInt(currentInString);

            if (isOdd) {
                if (current <= minHeap.peek()) {
                    minHeap.extract();
                    minHeap.insert(current);
                } else {
                    maxHeap.extract();
                    maxHeap.insert(current);
                }
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            }
            if (!isOdd) {
                int min = minHeap.peek();
                int max = maxHeap.peek();

                if (max < current) {
                    median  = max;
                    minHeap.insert(max);
                    maxHeap.insert(current);
                } else if (min > current) {
                    median = min;
                    maxHeap.insert(min);
                    minHeap.insert(current);
                } else {
                    median = current;
                    maxHeap.insert(current);
                    minHeap.insert(current);
                }
            }
            isOdd = !isOdd;
            System.out.println(median);
        }
    }


    private static class Heap {
        private final int[] array;
        private int n;
        private final HeapType heapType;

        public enum HeapType {
            MAX,
            MIN
        }

        private enum SinkOrSwim {
            SINK,
            SWIM
        }

        public Heap(int maxSize, HeapType type) {
            array = new int[maxSize + 2];
            this.n = 0;
            this.heapType = type;
        }

        public int peek() {
            return array[1];
        }

        public void insert(int value) {
            array[++n] = value;
            swim(n);
        }

        public int extract() {
            int max = array[1];
            swap(array, 1, n--);
            sink(1);
            return max;
        }


        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && compareForSinkAndSwim(array[j], array[j + 1], SinkOrSwim.SINK)) j++;

                if (!compareForSinkAndSwim(array[k], array[j], SinkOrSwim.SINK)) break;

                swap(array, k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && compareForSinkAndSwim(array[k], array[k / 2], SinkOrSwim.SWIM)) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        private boolean compareForSinkAndSwim(int first, int second, SinkOrSwim type) {
            if (heapType == HeapType.MAX) {
                if (type == SinkOrSwim.SINK) {
                    return first < second;
                } else {
                    return first > second;
                }
            }
            if (type == SinkOrSwim.SINK) {
                return first > second;
            } else {
                return first < second;
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
                    String str = reader.readLine();
                    if (str == null) {
                        return "";
                    }
                    tokenizer = new StringTokenizer(str);
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