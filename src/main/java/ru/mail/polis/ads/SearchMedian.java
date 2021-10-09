package ru.mail.polis.ads;

import java.io.*;

/**
 * Problem solution template.
 */
public final class SearchMedian {
    private SearchMedian() {
        // Should not be instantiated
    }

    private static class Heap {
        private final boolean isMax;
        private final int[] heap = new int[1000000];

        public int getSize() {
            return size;
        }

        private int size;

        public Heap(boolean isMax) {
            this.isMax = isMax;
            this.size = 0;
        }

        private void swap(int first, int second) {
            int tmp = heap[first];
            heap[first] = heap[second];
            heap[second] = tmp;
        }

        public void insert(int element) {
            heap[size++] = element;
            siftUp(size - 1);
        }

        private void siftUp(int i) {
            while (i >= 1 && ((isMax && heap[i] > heap[(i - 1) / 2]) ||
                    (!isMax && heap[i] < heap[(i - 1) / 2]))) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        public int extract() {
            int retElement = heap[0];
            heap[0] = heap[--size];
            siftDown(0);
            return retElement;
        }

        public int peek() {
            return heap[0];
        }

        private void siftDown(int i) {
            int left;
            int right;
            int j;
            while (2 * i + 1 < size) {
                left = 2 * i + 1;
                right = 2 * i + 2;
                j = left;
                if (right < size && ((isMax && heap[right] > heap[left]) ||
                        (!isMax && heap[right] < heap[left]))) {
                    j = right;
                }

                if ((isMax && heap[i] >= heap[j]) || (!isMax && heap[i] <= heap[j])) {
                    break;
                }
                swap(i, j);
                i = j;
            }
        }

    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int input;
        Heap maxHeapLeft = new Heap(true);
        Heap minHeapRight = new Heap(false);
        String s;

        int count = 0;
        try {
            while ((s = in.readLine()) != null) {
                input = Integer.parseInt(s);

                if (++count % 2 != 0) {
                    maxHeapLeft.insert(input);
                } else {
                    minHeapRight.insert(input);
                }

                if (minHeapRight.getSize() > 0 && maxHeapLeft.peek() > minHeapRight.peek()) {
                    maxHeapLeft.insert(minHeapRight.extract());
                    minHeapRight.insert(maxHeapLeft.extract());
                }

                if (minHeapRight.getSize() > maxHeapLeft.getSize()) {
                    out.write(minHeapRight.peek() + "\n");
                } else if (minHeapRight.getSize() < maxHeapLeft.getSize()) {
                    out.write(maxHeapLeft.peek() + "\n");
                } else {
                    out.write((minHeapRight.peek() + maxHeapLeft.peek()) / 2 + "\n");
                }
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
