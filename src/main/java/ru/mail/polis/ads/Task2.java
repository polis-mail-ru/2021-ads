package ru.mail.polis.ads;

import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            int mode = scanner.nextInt();
            if (mode == 0) {
                heap.insert(scanner.nextInt());
            } else {
                System.out.println(heap.peek());
            }

        }
    }

    private static class Heap {
        private int actualSize;
        private final int[] heap;//куча будет иметь максимально возможный размер, но так как заполнится 0, то все ок.

        public Heap(int n) {
            this.heap = new int[n+1];
            actualSize = 0;
        }

        public void insert(int el) {
            heap[++actualSize] = el;
            swim();
        }

        private int peek() {
            int el = heap[1];
            swap(1, actualSize--);
            sink(1);
            return el;
        }

        private void swim() {
            int currentIndex = actualSize;
            while (currentIndex != 1 && heap[currentIndex / 2] < heap[currentIndex]) {
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
                if (j < actualSize && heap[j] < heap[j+1]) j++; //right child
                if (heap[currentIndex] >= heap[j]) break; // invariant holds
                swap(currentIndex, j);
                currentIndex = j;
            }
        }

    }

}
