import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class task4 {
    public static class Heap {
        private final int[] heapArray;
        private final int maxSize;
        private int currentSize = 0;

        public Heap(int maxSize) {
            this.maxSize = maxSize;
            heapArray = new int[maxSize];
        }

        public void insert(int n) {
            heapArray[currentSize] = n;
            displaceUp(currentSize++);
        }

        private void displaceUp(int index) {
            int parentIndex = (index - 1) / 2;
            int bottom = heapArray[index];
            while (index > 0 && heapArray[parentIndex] < bottom) {
                heapArray[index] = heapArray[parentIndex];
                index = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
            heapArray[index] = bottom;
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) {
            int size =Integer.parseInt(in.nextLine());
            String s = in.nextLine();
            String[] numbers = s.split(" ");
            Heap heap = new Heap(size);
            for (int i = 0; i < size; i++) {
                heap.insert(Integer.parseInt(numbers[i]));
            }
            for (int i = 0; i < size; i++) {
                out.print(heap.heapArray[i] + " ");
            }
            out.flush();
    }

    public static void main(final String[] arg) {
            Scanner in = new Scanner(System.in);
            try (PrintWriter out = new PrintWriter(System.out, true)) {
                solve(in, out);
            }
    }
}
