package ru.mail.polis.ads.part3.zadorotskas;

import java.io.*;
import java.util.StringTokenizer;

public class Work3Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap leftHeap = new Heap(Heap.Type.MAX);
        Heap rightHeap = new Heap(Heap.Type.MIN);

        int first = in.nextInt();
        leftHeap.insert(first);
        rightHeap.insert(first);
        out.println(first);

        boolean isOdd = true;
        String str = in.next();

        while (!str.equals("")) {
            int n = Integer.parseInt(str);
            str = in.next();

            if (isOdd) {
                int med = leftHeap.getFirst();
                if (n > med) {
                    rightHeap.extract();
                    rightHeap.insert(n);
                } else {
                    leftHeap.extract();
                    leftHeap.insert(n);
                }

                out.println((leftHeap.getFirst() + rightHeap.getFirst()) / 2);
            } else {
                int left = leftHeap.getFirst();
                int right = rightHeap.getFirst();

                if (right < n) {
                    out.println(right);
                    leftHeap.insert(right);
                    rightHeap.insert(n);
                } else if (n < left) {
                    out.println(left);
                    rightHeap.insert(left);
                    leftHeap.insert(n);
                } else {
                    out.println(n);
                    rightHeap.insert(n);
                    leftHeap.insert(n);
                }
            }
            isOdd = !isOdd;
        }
    }


    private static class Heap {
        private int n = 0;
        private int[] array = new int[2];
        private final Type type;

        private enum Type {
            MAX,
            MIN
        }

        public Heap(Type type) {
            this.type = type;
        }

        public void insert(int v) {
            if (n >= array.length - 1) {
                int[] newArray = new int[array.length * 2];
                System.arraycopy(array, 0, newArray, 0, array.length);
                array = newArray;
            }
            array[++n] = v;
            swim(n);
        }

        public int extract() {
            int first = array[1];
            swap(array, 1, n--);
            sink(1);
            return first;
        }

        public int getFirst() {
            return array[1];
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && compareForSink(array[j], array[j + 1])) j++;

                if (!compareForSink(array[k], array[j])) break;

                swap(array, k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && compareForSwim(array[k], array[k / 2])) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        private boolean compareForSink(int first, int second) {
            if (type == Type.MAX) {
                return first < second;
            }
            return first > second;
        }

        private boolean compareForSwim(int first, int second) {
            if (type == Type.MAX) {
                return first > second;
            }
            return first < second;
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
                    String str = reader.readLine();
                    if (str == null) {
                        return "";
                    }
                    tokenizer = new StringTokenizer(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
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
