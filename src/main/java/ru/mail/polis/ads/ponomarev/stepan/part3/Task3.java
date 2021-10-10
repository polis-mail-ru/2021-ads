package ru.mail.polis.ads.ponomarev.stepan.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9495842

public class Task3 {
    private static class Heap {
        private int N;
        private final HeapType heapType;
        private final int[] data;

        public enum HeapType {
            MIN,
            MAX
        }

        public Heap(HeapType type) {
            this.N = 0;
            this.heapType = type;
            this.data = new int[1000000];
            this.data[0] = -1;
        }

        public void insert(int value) {
            N++;
            data[N] = value;
            swim(N);
        }

        public int removeTop() {
            int value = data[1];
            data[1] = data[N];
            N--;
            data[N + 1] = 0;

            sink(1);

            return value;
        }

        public Integer peek() {
            return data[1];
        }

        public int size() {
            return N;
        }

        private void swim(int k) {
            while (k > 1) {
                if (heapType == HeapType.MAX
                        ? data[k / 2] > data[k]
                        : data[k / 2] < data[k]
                ) {
                    int tmp = data[k / 2];
                    data[k / 2] = data[k];
                    data[k] = tmp;

                    k = k / 2;
                } else {
                    break;
                }
            }
        }

        private void sink(int k) {
            while (k * 2 <= N) {
                int j = k * 2;

                final boolean isMaxHeap = heapType == HeapType.MAX;
                if (j < N &&
                        isMaxHeap
                        ? data[j] > data[j + 1]
                        : data[j] < data[j + 1]
                ) {
                    j++;
                }

                if (isMaxHeap
                        ? data[k] > data[j]
                        : data[k] < data[j]
                ) {
                    int tmp = data[k];
                    data[k] = data[j];
                    data[j] = tmp;

                    k = j;
                } else {
                    break;
                }
            }
        }
    }

    private static class DynamicMedian {
        private final Heap leftHeap;
        private final Heap rightHeap;
        private int median;

        public DynamicMedian() {
            median = -1;
            this.leftHeap = new Heap(Heap.HeapType.MIN);
            this.rightHeap = new Heap(Heap.HeapType.MAX);
        }

        public void add(int value) {
            serveValue(value);
            swapHeadWithMedian();
            serveMedian();
        }

        public int getMedian() {
            return median == -1 ? (rightHeap.peek() + leftHeap.peek()) / 2 : median;
        }

        private void serveValue(int value) {
            final boolean thereIsNoMedian = median == -1;

            if (thereIsNoMedian) {
                median = value;
            } else if (median < value) {
                rightHeap.insert(value);
            } else {
                leftHeap.insert(value);
            }
        }

        private void swapHeadWithMedian() {
            if (rightHeap.size() != 0 && median > rightHeap.peek()) {
                int tmp = rightHeap.removeTop();
                rightHeap.insert(median);
                median = tmp;
            } else if (leftHeap.size() != 0 && median < leftHeap.peek()) {
                int tmp = leftHeap.removeTop();
                leftHeap.insert(median);
                median = tmp;
            }
        }

        private void serveMedian() {
            if (rightHeap.size() < leftHeap.size()) {
                rightHeap.insert(median);
                median = -1;
            } else if (leftHeap.size() < rightHeap.size()) {
                leftHeap.insert(median);
                median = -1;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        DynamicMedian dynamicMedian = new DynamicMedian();

        while (true) {
            int i = in.nextInt();

            dynamicMedian.add(i);

            out.println(dynamicMedian.getMedian());
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
        }
    }
}