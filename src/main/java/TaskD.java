import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            int n = in.nextInt();
            ArrayList<Integer> mass = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                mass.add(in.nextInt());
            }
            sort(mass, 0, mass.size() - 1, Integer::compare);
            int ans = 1;
            for (int i = 1; i < n; i++) {
                if (mass.get(i) > mass.get(i - 1)) {
                    ans++;
                }
            }
            System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static <T> void swap(ArrayList<T> array, int i, int j) {
        T tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    public static <T> ArrayList<T> sort(ArrayList<T> array, int begin, int end, Comparator<T> cmp) {
        if (array.size() <= 1 || end <= 0) {
            return array;
        }
        if (begin >= end) {
            return array;
        }
        int pivot = begin + (end - begin) / 2;
        T opora = array.get(pivot);
        swap(array, pivot, end);
        for (int i = pivot = begin; i < end; i++) {
            if (cmp.compare(array.get(i), opora) <= 0) {
                swap(array, pivot++, i);
            }
        }
        swap(array, pivot, end);
        sort(array, begin, pivot - 1, cmp);
        sort(array, pivot + 1, end, cmp);
        return array;
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
