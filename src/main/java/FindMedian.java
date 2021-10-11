import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//https://www.e-olymp.com/ru/submissions/9511955
public class FindMedian {

    private static void solve(final Scanner in, final PrintWriter out) {
        OrderedHeap left = new MaxOrderedHeap();
        OrderedHeap right = new MinOrderedHeap();
        int med = -1;
        while (in.hasNext()) {
            int number = in.nextInt();
            if (number > med) {
                if (right.getSize() > left.getSize()) {
                    int elementToBeMoved = right.extract();
                    right.insert(number);
                    left.insert(elementToBeMoved);
                } else {
                    right.insert(number);
                }
            } else {
                if (left.getSize() > right.getSize()) {
                    int elementToBeMoved = left.extract();
                    left.insert(number);
                    right.insert(elementToBeMoved);
                } else {
                    left.insert(number);
                }
            }
            if ((left.getSize() + right.getSize()) % 2 == 0) {
                med = (left.peekTop() + right.peekTop()) / 2;
            } else {
                med = left.getSize() > right.getSize() ? left.peekTop() : right.peekTop();
            }
            out.println(med);
        }
    }

    private static class MaxOrderedHeap extends OrderedHeap {

        @Override
        boolean rule(int child, int parent) {
            return parent >= child;
        }
    }

    private static class MinOrderedHeap extends OrderedHeap {

        @Override
        boolean rule(int child, int parent) {
            return parent <= child;
        }
    }

    private static abstract class OrderedHeap {
        public int getSize() {
            return size;
        }

        private int size;
        private final ArrayList<Integer> a;

        abstract boolean rule(int child, int parent);

        public OrderedHeap() {
            size = 0;
            a = new ArrayList<>();
            a.add(0);
        }

        public void insert(int number) {
            size++;
            a.add(number);
            swim(size);
        }

        public int extract() {
            int top = a.get(1);
            swap(1, size--);
            a.remove(a.size() - 1);
            sink(1);
            return top;
        }


        public int peekTop() {
            return a.get(1);
        }

        private void swim(int i) {
            int k = i;
            while ((k > 1) && !rule(a.get(k), a.get(k / 2))) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int child = 2 * k;
                if (child + 1 <= size && rule(a.get(child), a.get(child + 1))) {
                    child = child + 1;
                }
                if (rule(a.get(child), a.get(k))) {
                    break;
                }
                swap(k, child);
                k = child;
            }
        }

        private void swap(int i, int j) {
            Collections.swap(a, i, j);
        }

        @Override
        public String toString() {
            return size + "|" + a;
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
