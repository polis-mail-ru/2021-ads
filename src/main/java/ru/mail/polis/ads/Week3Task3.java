package ru.mail.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class Week3Task3 {
    private Week3Task3() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        Heap maxHeap = new Heap();//left
        Heap minHeap = new Heap(false);//right

        int current = in.nextInt();
        int median = current;
        int size = 1;
        while (in.hasNextInt()) {
            out.println(median);
            current = in.nextInt();
            if (size % 2 == 1) {//clear median
                if (current > median) {
                    minHeap.add(current);
                    maxHeap.add(median);
                    median = (median + minHeap.peek()) / 2;

                } else {
                    minHeap.add(median);
                    maxHeap.add(current);
                    median = (median + maxHeap.peek()) / 2;
                }
                size++;
                continue;
            }
            //implicit median
            int maxMin = maxHeap.peek();
            int minMax = minHeap.peek();
            if (current >= minMax) {
                if (current <= maxMin) {
                    median = current;
                } else {
                    minHeap.add(current);
                    median = minHeap.pop();
                }
                size++;
                continue;
            }
            maxHeap.add(current);
            median = maxHeap.pop();
            size++;
        }
        out.println(median);
    }

    private static class Heap {

        private int[] data;
        private int count;
        private boolean maxheap;

        Heap(int size, boolean maxheap) {
            data = new int[size + 1];
            this.maxheap = maxheap;
        }

        Heap(boolean maxheap) {
            this(10, maxheap);
        }

        Heap() {
            this(10, true);
        }

        public void add(int element) {
            extendsArrayIfFull();
            data[++count] = element;
            siftUp(count);
        }

        public int peek() {
            return data[1];
        }

        public int pop() {
            int max = data[1];
            data[1] = data[count];
            data[count--] = 0;
            siftDown(1);
            return max;
        }

        private int size() {
            return count;
        }

        private void siftUp(int index) {
            int k = index;
            if (maxheap) {
                while (k > 1 && data[k] > data[k / 2]) {
                    swap(k, k / 2);
                    k /= 2;
                }
                return;
            }
            while (k > 1 && data[k] < data[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void siftDown(int index) {
            int k = index;
            if (maxheap) {
                while (2 * k <= count) {
                    int j = 2 * k; // left child
                    if (j < count && data[j] < data[j + 1]) j++; //right
                    if (data[k] >= data[j]) return;
                    swap(k, j);
                    k = j;
                }
            }
            while (2 * k <= count) {
                int j = 2 * k; // left child
                if (j < count && data[j] > data[j + 1]) j++; //right
                if (data[k] <= data[j]) return;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i1, int i2) {
            int temp = data[i1];
            data[i1] = data[i2];
            data[i2] = temp;
        }

        private void extendsArrayIfFull() {
            if (count + 1 == data.length) {
                int[] dataNew = new int[(count + 1) * 2];
                System.arraycopy(data, 0, dataNew, 0, count + 1);
                data = dataNew;
            }
        }

    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
