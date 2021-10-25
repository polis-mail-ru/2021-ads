import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Collections.swap;


public final class HomeWork3Task2 {

    private HomeWork3Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.removeMax());
            }
        }
    }

    private static class Heap {
        private final ArrayList<Integer> list = new ArrayList<>();

        {
            list.add(0);
        }

        public void insert(int x) {
            list.add(x);
            swim(list.size() - 1);
        }

        public int removeMax() {
            swap(list, 1, list.size() - 1);
            int max = list.remove(list.size() - 1);
            sink(1);
            return max;
        }

        private void swim(int i) {
            while (i > 1 && list.get(i) > list.get(i / 2)) {
                swap(list, i, i / 2);
                i = i / 2;
            }
        }

        private void sink(int i) {
            while (2 * i <= list.size() - 1) {
                int j = 2 * i;
                if (j < list.size() - 1 && list.get(j) < list.get(j + 1)) {
                    j++;
                }
                if (list.get(i) >= list.get(j)) {
                    break;
                }
                swap(list, i, j);
                i = j;
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