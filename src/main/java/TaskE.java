import java.io.*;
import java.util.StringTokenizer;

public class TaskE {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }

        int invCount = countInv(array, 0, size);
        out.println(invCount);
    }

    private static int countInv(int[] array, int beg, int end) {
        if (end - beg <= 1) {
            return 0;
        }

        int mid = (beg + end) / 2;
        return countInv(array, beg, mid) + countInv(array, mid, end) + countSplitInv(array, beg, end, mid);
    }

    private static int countSplitInv(int[] array, int beg, int end, int mid) {
        // Здесь нужно делать merge sort и считать количество инверсий
        int it1 = 0;
        int it2 = 0;
        int invCount = 0;

        int[] result = new int[end - beg];

        while (beg + it1 < mid && mid + it2 < end) {
            if (array[beg + it1] <= array[mid + it2]) {
                result[it1 + it2] = array[beg + it1];
                it1++;
            }
            else {
                result[it1 + it2] = array[mid + it2];
                it2++;
                invCount += mid - (beg + it1);
            }
        }

        while (beg + it1 < mid) {
            result[it1 + it2] = array[beg + it1];
            it1++;
        }

        while (mid + it2 < end) {
            result[it1 + it2] = array[mid + it2];
            it2++;
        }

        for(int i = 0; i < it1+ it2; i++) {
            array[beg + i] = result[i];
        }

        return invCount;
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
