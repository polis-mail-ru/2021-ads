import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MinHeap maxHalf = new MinHeap(500001);
        MaxHeap minHalf = new MaxHeap(500001);
        Scanner input = new Scanner(System.in);
        int median = Integer.MIN_VALUE;
        while (input.hasNext()) {
            int value = input.nextInt();
            if (value < median) {
                if (minHalf.currSize > maxHalf.currSize) {
                    maxHalf.insert(minHalf.extract());
                }
                minHalf.insert(value);
            } else {
                if (maxHalf.currSize > minHalf.currSize) {
                    minHalf.insert(maxHalf.extract());
                }
                maxHalf.insert(value);
            }
            if ((maxHalf.currSize + minHalf.currSize) % 2 == 0) {
                median = (maxHalf.peek() + minHalf.peek()) / 2;
            } else if (maxHalf.currSize > minHalf.currSize) {
                median = maxHalf.peek();
            } else {
                median = minHalf.peek();
            }
            out.println(median);
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

    private static class MinHeap {
        private final int[] arr;
        private int currSize;

        public MinHeap(int size) {
            arr = new int[size + 1];
            currSize = 0;
        }

        public void insert(int value) {
            arr[++currSize] = value;
            swim(currSize);
        }

        public int extract() {
            int temp = arr[1];
            arr[1] = arr[currSize--];
            sink(1);
            return temp;
        }

        public int peek() {
            return arr[1];
        }

        private void sink(int k) {
            while (2 * k <= currSize) {
                int child = 2 * k;
                if (child < currSize && arr[child] > arr[child + 1]) {
                    child++;
                }
                if (arr[k] <= arr[child]) {
                    break;
                }
                int temp = arr[k];
                arr[k] = arr[child];
                arr[child] = temp;
                k = child;
            }
        }

        private void swim(int k) {
            while (k > 1 && arr[k] < arr[k / 2]) {
                int temp = arr[k];
                arr[k] = arr[k / 2];
                arr[k / 2] = temp;
                k = k / 2;
            }
        }
    }

    private static class MaxHeap {
        private final int[] arr;
        private int currSize;

        public MaxHeap(int size) {
            arr = new int[size + 1];
            currSize = 0;
        }

        public void insert(int value) {
            arr[++currSize] = value;
            swim(currSize);
        }

        public int extract() {
            int temp = arr[1];
            arr[1] = arr[currSize--];
            sink(1);
            return temp;
        }

        public int peek() {
            return arr[1];
        }

        private void sink(int k) {
            while (2 * k <= currSize) {
                int child = 2 * k;
                if (child < currSize && arr[child] < arr[child + 1]) {
                    child++;
                }
                if (arr[k] >= arr[child]) {
                    break;
                }
                int temp = arr[k];
                arr[k] = arr[child];
                arr[child] = temp;
                k = child;
            }
        }

        private void swim(int k) {
            while (k > 1 && arr[k] > arr[k / 2]) {
                int temp = arr[k];
                arr[k] = arr[k / 2];
                arr[k / 2] = temp;
                k = k / 2;
            }
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
    }
}
