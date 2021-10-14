package ru.mail.polis.ads.part3.sorgeligt;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9543407
public class Task3 {
    private static final int EMPTY_MEDIAN = -1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap minHeapOfMaxElement = new Heap(Comparator.reverseOrder());
        Heap maxHeapOfMinElement = new Heap(Comparator.naturalOrder());
        String stringNumber = in.nextLine();
        if (stringNumber == null) {
            return;
        }
        int median = Integer.parseInt(stringNumber);
        out.println(median);
        while ((stringNumber = in.nextLine()) != null) {
            int number = Integer.parseInt(stringNumber);
            if (median == EMPTY_MEDIAN) {
                if (number > minHeapOfMaxElement.peek()) {
                    median = minHeapOfMaxElement.exctract();
                    minHeapOfMaxElement.insert(number);
                } else if (number < maxHeapOfMinElement.peek()) {
                    median = maxHeapOfMinElement.exctract();
                    maxHeapOfMinElement.insert(number);
                } else {
                    median = number;
                }
            } else {
                if (number > median) {
                    maxHeapOfMinElement.insert(median);
                    minHeapOfMaxElement.insert(number);
                } else {
                    minHeapOfMaxElement.insert(median);
                    maxHeapOfMinElement.insert(number);
                }
                median = EMPTY_MEDIAN;
            }
            printMedian(out, minHeapOfMaxElement, maxHeapOfMinElement, median);
        }
    }

    private static void printMedian(PrintWriter out, Heap minHeapOfMaxElement, Heap maxHeapOfMinElement, int median) {
        if (median == EMPTY_MEDIAN) {
            out.println(maxHeapOfMinElement.peek() + (minHeapOfMaxElement.peek() - maxHeapOfMinElement.peek()) / 2);
        } else {
            out.println(median);
        }
    }

    private static class Heap {
        final static private int DEFAULT_CAPACITY = 10;
        private final Comparator<Integer> cmp;
        private int[] elementData;
        private int topIndex = 0;
        private int capacity;

        public Heap(Comparator<Integer> cmp) {
            this.cmp = cmp;
            capacity = DEFAULT_CAPACITY;
            elementData = new int[capacity];
        }

        public void insert(int value) {
            if (topIndex + 1 >= capacity) {
                reallocHeap();
            }
            elementData[++topIndex] = value;
            swim(topIndex);
        }

        public int exctract() {
            final int maxElem = elementData[1];
            swap(elementData, 1, topIndex--);
            sink(1);
            return maxElem;
        }

        public int peek() {
            return elementData[1];
        }

        private void swim(int k) {
            while (k > 1 && cmp.compare(elementData[k], elementData[k / 2]) > 0) {
                swap(elementData, k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (k * 2 <= topIndex) {
                int j = k * 2;
                if (j < topIndex && cmp.compare(elementData[j], elementData[j + 1]) < 0) {
                    j++;
                }
                if (cmp.compare(elementData[k], elementData[j]) >= 0) {
                    break;
                }
                swap(elementData, k, j);
                k = j;
            }
        }

        private void reallocHeap() {
            capacity *= 2;
            elementData = Arrays.copyOf(elementData, capacity);
        }

        private void swap(int[] array, int firstIndex, int secondIndex) {
            if (Math.max(firstIndex, secondIndex) > topIndex && Math.min(firstIndex, secondIndex) < 0) {
                throw new IndexOutOfBoundsException();
            }
            final int tmp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = tmp;
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

        String nextLine() {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}