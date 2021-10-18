package ru.mail.polis.ads.part4.sorgeligt;


import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/9576816
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] array = new int[n + 2];
        array[0] = 0;
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        array[n + 1] = 0;
        final int k = in.nextInt();
        System.out.println(getMaxCost(array, k));
    }

    /**
     * Основная часть решения использующая дп
     */
    private static int getMaxCost(int[] array, int k) {
        IntegerCircularQueue maxCost = new IntegerCircularQueue(k + 1);
        maxCost.add(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (maxCost.size() - 1 == k) {
                maxCost.removeFirst();
            }
            maxCost.add(array[i] + maxCost.getMaxElem());
        }
        return maxCost.getLast();
    }

    /**
     * Циклическая очередь с константным размером
     */
    static class IntegerCircularQueue {
        private final int[] queue;
        private final int capacity;
        private int size = 0;
        private int front;
        private int back;

        public IntegerCircularQueue(int size) {
            this.capacity = size;
            queue = new int[size];
            front = back = -1;
        }

        public void add(int value) {
            if (size == capacity) {
                throw new ArrayIndexOutOfBoundsException();
            }
            back = (back + 1) % capacity;
            queue[back] = value;
            if (front == -1) {
                front = back;
            }
            size++;
        }

        public void removeFirst() {
            if (isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            front = (front + 1) % capacity;
            size--;
        }

        public int getMaxElem() {
            if (isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int max = queue[front];
            for (int i = front, k = 0; k < size; i = (i + 1) % capacity, k++) {
                max = Math.max(max, queue[i]);
            }
            return max;
        }

        public int getFirst() {
            return queue[front];
        }

        public int getLast() {
            return queue[back];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
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