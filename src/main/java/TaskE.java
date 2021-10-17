import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskE {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        ArrayList<Integer> elems = new ArrayList<>();
        elems.add(Integer.MIN_VALUE);

        for(int i = 1; i <= size; i++) {
            elems.add(in.nextInt());
        }

        heapSort(elems);

        for (int i = 1; i <= size; i++) {
            out.print(elems.get(i) + " ");
        }
    }

    private static void heapSort(ArrayList<Integer> elems) {
        int size = elems.size() - 1;
        for(int k = size/2; k>=1; k--) {
            sink(elems, k, size);
        }

        while (size > 1) {
            Collections.swap(elems, 1, size--);
            sink(elems, 1, size);
        }
    }

    private static void sink(ArrayList<Integer> elems, int elemInd, int size) {
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