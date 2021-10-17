import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskB {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();

        int commCount = in.nextInt();
        for (int i = 0; i < commCount; i++) {
            switch (in.nextInt()) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.extract());
                    break;
            }
        }
    }

    private static class Heap {
        private int size;
        private ArrayList<Integer> elems;

        Heap() {
            size = 0;
            elems = new ArrayList<>();
            //First element won't be used
            elems.add(Integer.MIN_VALUE);
        }

        public void insert(int elem) {
            size++;
            // This if need to because actual array size may be != size of heap (I don't want copy arrays)
            if(elems.size() <= size) {
                elems.add(elem);
            }
            else {
                elems.set(size, elem);
            }

            swim(size);
        }

        public int extract() {
            int max = elems.get(1);
            Collections.swap(elems, 1, size--);
            sink(1);
            return max;
        }

        private void swim(int elemInd) {
            while (elemInd > 1 && elems.get(elemInd) > elems.get(elemInd / 2)) {
                Collections.swap(elems, elemInd, elemInd / 2);
                elemInd = elemInd / 2;
            }
        }

        private void sink(int elemInd) {
            while (2 * elemInd <= size) {
                int childInd = 2 * elemInd;
                if (childInd < size && elems.get(childInd) < elems.get(childInd + 1)) {
                    childInd++;
                }

                if (elems.get(elemInd) >= elems.get(childInd)) {
                    break;
                }

                Collections.swap(elems, elemInd, childInd);
                elemInd = childInd;
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
