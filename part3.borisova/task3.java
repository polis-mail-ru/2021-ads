import java.io.*;
import java.util.Scanner;

public class task3 {
    public static class Heap {
        private final int[] heapArray;
        private int currentSize = 0;
        private final boolean isGreater;

        public Heap(int maxSize, boolean isGreater) {
            this.isGreater = isGreater;
            heapArray = new int[maxSize];
        }

        public void insert(int n) {
            if (currentSize < heapArray.length) {
                heapArray[currentSize] = n;
                displaceUp(currentSize++);
            }
        }
        private void displaceUp(int index) {
            int parentIndex = (index - 1) / 2;
            int bottom = heapArray[index];
            while (index > 0 &&((isGreater && heapArray[parentIndex] > bottom) || (!isGreater && heapArray[parentIndex] < bottom))) {
                heapArray[index] = heapArray[parentIndex];
                index = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
            heapArray[index] = bottom;

        }

        public int extract() {
            int top = heapArray[0];
            heapArray[0] = heapArray[currentSize - 1];
            heapArray[currentSize - 1] = 0;
            currentSize--;
            displaceDown();
            return top;
        }

        public int top() {
            return heapArray[0];
        }

        private void displaceDown() {
            int index = 0;
            int largerChild;
            int top = heapArray[index];
            while (index < currentSize / 2) {
                int leftChild = 2 * index + 1;
                int rightChild = leftChild + 1;

                if (rightChild < currentSize && ((isGreater && heapArray[leftChild] > heapArray[rightChild]) || (!isGreater && heapArray[leftChild] < heapArray[rightChild]))) {
                    largerChild = rightChild;
                } else {
                    largerChild = leftChild;
                }
                if ((isGreater && top <= heapArray[largerChild]) || (!isGreater && top >= heapArray[largerChild])) {
                    break;
                }
                heapArray[index] = heapArray[largerChild];
                index = largerChild;
            }
            heapArray[index] = top;
        }
    }

    private static void solve(PrintWriter out){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int median = n;
        out.println(median);
        Heap greater = new Heap(500001, true);
        Heap less = new Heap(500001, false);
        int[] comparison = new int[3];
        int count = 1;
        while (in.hasNextInt()) {
            n = in.nextInt();
            if (count % 2 == 1) {
                if (n < median) {
                    less.insert(n);
                    greater.insert(median);
                } else {
                    less.insert(median);
                    greater.insert(n);
                }
                median = (greater.top() + less.top()) / 2;
            } else {
                comparison[0] = greater.extract();
                comparison[1] = n;
                comparison[2] = less.extract();
                if (comparison[0] > comparison[1]) {
                    swap(comparison, 0, 1);
                }
                if (comparison[1] > comparison[2]) {
                    swap(comparison, 1, 2);
                }
                less.insert(comparison[0]);
                median = comparison[1];
                greater.insert(comparison[2]);
            }
            count++;
            out.println(median);
        }
        out.flush();
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
