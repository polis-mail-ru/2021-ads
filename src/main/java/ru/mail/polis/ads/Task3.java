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
//        Comparator<Integer> nativeComparator = Integer::compare;
//        Comparator<Integer> reversedComparator = (o1, o2) -> Integer.compare(0, Integer.compare(o1, o2));
        Comparator<Long> nativeComparator = Long::compare;
        Comparator<Long> reversedComparator = (o1, o2) -> Long.compare(0, Long.compare(o1, o2));
        Heap<Long> rightHeap = new Heap<>(reversedComparator, new Long[1000000 + 1]);//min куча
        String line = in.next();
        while (line != null) {
            rightHeap.insert(Long.parseLong(line));
            line = in.next();
        }

        Heap<Long> leftHeap = new Heap<>(nativeComparator, new Long[rightHeap.getActualSize() / 2 + 1]);//max куча
        int part = rightHeap.getActualSize() / 2;
        for (int i = 0; i < part; i++) {
            leftHeap.insert(rightHeap.peek());
        }
        if (leftHeap.getActualSize() == rightHeap.getActualSize()) {
            out.println((leftHeap.peek() + rightHeap.peek()) / 2);
        } else {
            out.println(rightHeap.peek());
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


    private static class Heap<T> {
        private int actualSize;
        private T[] heap;//куча будет иметь максимально возможный размер, но так как заполнится 0, то все ок.
        private final Comparator<T> customComparator;

        public Heap(Comparator<T> customComparator, T[] heap) {
            this.heap = heap;
            this.customComparator = customComparator;
            actualSize = 0;
        }

        public void insert(T el) {
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

        private T peek() {
            T el = heap[1];
            swap(1, actualSize--);
            sink(1);
            return el;
        }

        private void swim() {
            int currentIndex = actualSize;
            while (currentIndex != 1 && customComparator.compare(heap[currentIndex], heap[currentIndex / 2]) > 0) {
                swap(currentIndex, currentIndex / 2);
                currentIndex /= 2;
            }
        }

        private void swap(int first, int second) {
            T temp = heap[first];
            heap[first] = heap[second];
            heap[second] = temp;
        }

        private void sink(int startIndex) {
            int currentIndex = startIndex;
            while (2 * currentIndex <= actualSize) {
                int j = 2 * currentIndex; // left child
                if (j < actualSize && customComparator.compare(heap[j], heap[j + 1]) < 0) j++; //right child
                if (customComparator.compare(heap[currentIndex], heap[j]) >= 0) break; // invariant holds
                swap(currentIndex, j);
                currentIndex = j;
            }
        }

        public int getActualSize() {
            return actualSize;
        }
    }
}
