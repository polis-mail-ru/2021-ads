import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.e-olymp.com/ru/submissions/9509777
 */
public final class Week3Task2 {
    private Week3Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        Heap heap = new Heap(N);

        for (int i = 0; i < N; ++i) {
            int command = in.nextInt();
            switch (command) {
                case 0: {
                    heap.insert(in.nextInt());
                    break;
                }
                case 1: {
                    out.println(heap.extract());
                    break;
                }
            }
        }
    }

    private static class Heap {
        private int[] array;
        private int lastIndex;

        Heap(int n) {
            this.array = new int[n + 1];
            this.lastIndex = 0;
        }

        public void insert(int newElement) {
            array[++lastIndex] = newElement;
            swim(lastIndex);
        }

        public int extract() {
            int max = array[1];

            int temp = array[1];
            array[1] = array[lastIndex];
            array[lastIndex] = temp;

            lastIndex--;

            sink(1);

            return max;
        }

        private void swim(int k) {
            int index = k;

            while (index > 1 && array[index] > array[index / 2]) {
                int temp = array[index];
                array[index] = array[index / 2];
                array[index / 2] = temp;

                index /= 2;
            }
        }

        private void sink(int k) {
            int index = k;

            while (2 * index <= lastIndex) {
                int left = 2 * index;

                if (left < lastIndex && array[left] < array[left + 1]) {
                    left++;
                }
                if (array[index] >= array[left]) {
                    break;
                }

                int temp = array[index];
                array[index] = array[left];
                array[left] = temp;

                index = left;
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
