package ru.mail.polis.ads.part3.sorgeligt;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9543406
public class Task2 {
    private final static int INSERT = 0;
    private final static int EXCTRACT = 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(Integer::compareTo);
        final int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            final int operation = in.nextInt();
            if (operation == INSERT) {
                heap.insert(in.nextInt());
            } else if (operation == EXCTRACT) {
                out.println(heap.exctract());
            } else {
                throw new IllegalArgumentException("Unknown operation");
            }
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