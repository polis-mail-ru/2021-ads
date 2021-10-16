package part3.tkachenkoalexandra;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public final class ThirdTask {

    private static final int DEFAULT_SIZE = 50;
    private static final int MAX_SIZE = 1000000;

    private ThirdTask() {
    }

    public static void solve(final FastScanner in, final PrintWriter out) {
        Heap heapMin = new Heap(DEFAULT_SIZE, false);
        Heap heapMax = new Heap(DEFAULT_SIZE, true);
        int number = in.nextInt();
        int median = number;
        out.println(median);
        boolean isOdd = true;
        for (String s; (s = in.nextLine()) != null; isOdd = !isOdd) {
            number = Integer.parseInt(s);
            if (isOdd) {
                if (number > median) {
                    heapMin.insert(number);
                    heapMax.insert(median);
                } else {
                    heapMin.insert(median);
                    heapMax.insert(number);
                }
                median = (heapMax.top() + heapMin.top()) / 2;
            } else {
                int topMin = heapMin.top();
                int topMax = heapMax.top();
                if (number > topMin) {
                    median = heapMin.extract();
                    heapMin.insert(number);
                } else if (number < topMax) {
                    median = heapMax.extract();
                    heapMax.insert(number);
                } else {
                    median = number;
                }
            }
            out.println(median);
        }
    }

    private static class Heap {
        private int[] array;
        private int count;
        private final Comparator<Integer> comparator;

        public Heap(int size, boolean type) {
            array = new int[size];
            comparator = type ? Comparator.naturalOrder() : Comparator.reverseOrder();
        }

        public Heap(int[] array, boolean type) {
            this(array.length, type);
            Arrays.stream(array).forEach(this::insert);
        }

        public void insert(int value) {
            if (count == array.length) {
                if (array.length > MAX_SIZE / 2) {
                    throw new RuntimeException("The task exceeds the limit of resources allocated to it.\n");
                }
                this.array = Arrays.copyOf(array, array.length * 2);
            }
            array[count++] = value;
            swim(count - 1);
        }

        private void swim(int i) {
            for (; i >= 1 && comparator.compare(array[i], array[(i - 1) / 2]) > 0; i = (i - 1) / 2) {
                swap(i, (i - 1) / 2);
            }
        }

        public int top() {
            return array[0];
        }

        public int extract() {
            int top = array[0];
            swap(0, --count);
            sink(0);
            return top;
        }

        private void sink(int k) {
            while (2 * k + 1 < count) {
                int j = 2 * k + 1;
                if (j + 1 < count && comparator.compare(array[j], array[j + 1]) < 0) {
                    j++;
                }
                if (comparator.compare(array[k], array[j]) >= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            if (i != j) {
                array[j] ^= array[i];
                array[i] ^= array[j];
                array[j] ^= array[i];
            }
        }

        public void sort() {
            while (count > 1) {
                swap(0, --count);
                sink(0);
            }
        }

        public void print(final PrintWriter out) {
            IntStream.range(0, array.length)
                    .mapToObj(i -> array[i] + (i != array.length - 1 ? " " : "")).forEach(out::print);
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
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

