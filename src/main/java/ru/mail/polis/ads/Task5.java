package ru.mail.polis.ads;

import java.util.Comparator;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparator<Integer> reversedComparator = (o1, o2) -> Integer.compare(0, Integer.compare(o1, o2));
        int n = scanner.nextInt();
        Heap minHeap = new Heap(n,reversedComparator);
        for (int i = 0; i < n; i++) {
            minHeap.insert(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(minHeap.peek());
        }
    }
    private static class Heap {
        private int actualSize;
        private int[] heap;
        private final Comparator<Integer> customComparator;

        public Heap(int n, Comparator<Integer> customComparator) {
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
            swap(heap, 1, actualSize--);
            sink(heap, actualSize, 1, customComparator);
            return el;
        }

        public void makeHeap(int[] heapCandidate) {
            int n = heapCandidate.length;
            for (int i = n / 2; i >= 1; i--) {
                sink(heapCandidate, heapCandidate.length - 1, i, customComparator);
            }
        }

        private void swim() {
            int currentIndex = actualSize;
            while (currentIndex != 1 && customComparator.compare(heap[currentIndex], heap[currentIndex / 2]) > 0) {
                swap(heap, currentIndex, currentIndex / 2);
                currentIndex /= 2;
            }
        }

        private static void swap(int[] heap, int first, int second) {
            int temp = heap[first];
            heap[first] = heap[second];
            heap[second] = temp;
        }

        private static void sink(int[] heap, int actualSize, int startIndex, Comparator<Integer> customComparator) {
            int currentIndex = startIndex;
            while (2 * currentIndex <= actualSize) {
                int j = 2 * currentIndex; // left child
                if (j < actualSize && customComparator.compare(heap[j], heap[j + 1]) < 0) j++; //right child
                if (customComparator.compare(heap[currentIndex], heap[j]) >= 0) break; // invariant holds
                swap(heap, currentIndex, j);
                currentIndex = j;
            }
        }

        public int getElement(int index) {
            return heap[index + 1];
        }

        public int size() {
            return actualSize;
        }
    }
}
