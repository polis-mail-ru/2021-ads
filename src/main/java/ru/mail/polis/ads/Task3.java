package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;

/**
 * Problem solution template.
 */
public class Task3 {
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Comparator<Long> nativeComparator = Long::compare;
        Comparator<Long> reversedComparator = (o1, o2) -> Long.compare(0, Long.compare(o1, o2));
        Heap rightHeap = new Heap(reversedComparator, 1000000 / 2 + 1);//min heap
        Heap leftHeap = new Heap(nativeComparator, 1000000 / 2); //max heap
        String next = in.next();
        out.println(next);
        rightHeap.insert(Integer.parseInt(next));
        next = in.next();
        while (next != null) {
            rightHeap.insert(Integer.parseInt(next));
            if (rightHeap.getActualSize() > leftHeap.actualSize + 1 || rightHeap.getFirst() < leftHeap.getFirst()) {
                leftHeap.insert(rightHeap.peek());
            }
            if (leftHeap.getActualSize() == rightHeap.getActualSize() + 2) {
                rightHeap.insert(leftHeap.peek());
            }
            if (leftHeap.getActualSize() == rightHeap.getActualSize()) {
                out.println(((long) leftHeap.getFirst() + (long) rightHeap.getFirst()) / 2);
            } else {
                out.println((leftHeap.getActualSize() > rightHeap.getActualSize()) ? leftHeap.getFirst() : rightHeap.getFirst());
            }
            next = in.next();
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
                return null;
            }
        }
    }


    private static class Heap {
        private int actualSize;
        private int[] heap;//куча будет иметь максимально возможный размер, но так как заполнится 0, то все ок.
        private final Comparator<Long> customComparator;

        public Heap(Comparator<Long> customComparator, int n) {
            this.heap = new int[n + 1];
            this.customComparator = customComparator;
            actualSize = 0;
        }

        public void insert(int el) {
//            if (actualSize + 1 == heap.length) {
//                extendHeapSize();
//            }
            heap[++actualSize] = el;
            swim();
        }

//        private void extendHeapSize() {
//            int newSize = Math.min(heap.length * 2, 1000000);
//            heap = Arrays.copyOf(heap, newSize);
//        }

        private int peek() {
            int el = heap[1];
            heap[1] = 0;
            swap(1, actualSize--);
            sink(1);
            return el;
        }

        private void swim() {
            int currentIndex = actualSize;
            while (currentIndex != 1 && customComparator.compare((long) heap[currentIndex], (long) heap[currentIndex / 2]) > 0) {
                swap(currentIndex, currentIndex / 2);
                currentIndex /= 2;
            }
        }

        private void swap(int first, int second) {
            int temp = heap[first];
            heap[first] = heap[second];
            heap[second] = temp;
        }

        private void sink(int startIndex) {
            int currentIndex = startIndex;
            while (2 * currentIndex <= actualSize) {
                int j = 2 * currentIndex; // left child
                if (j < actualSize && customComparator.compare((long) heap[j], (long) heap[j + 1]) < 0)
                    j++; //right child
                if (customComparator.compare((long) heap[currentIndex], (long) heap[j]) >= 0) break; // invariant holds
                swap(currentIndex, j);
                currentIndex = j;
            }
        }

        public int getFirst() {
            return heap[1];
        }

        public int getActualSize() {
            return actualSize;
        }
    }
}
