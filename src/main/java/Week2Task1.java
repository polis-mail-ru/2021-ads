import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Задача №1446. Результаты олимпиады (1)
 * <p>
 * Memory O(N)
 * <p>
 * Time O(N log N)
 */
public final class Week2Task1 {
    private Week2Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        OlympiadEntry[] entries = new OlympiadEntry[in.nextInt()];
        for (int i = 0; i < entries.length; i++) {
            entries[i] = new OlympiadEntry(in);
        }
        mergeSort(entries, OlympiadEntry.byResult);
        for (OlympiadEntry entry : entries) {
            entry.println(out);
        }
    }

    static class OlympiadEntry {
        final int id;
        final int result;

        public OlympiadEntry(int id, int result) {
            this.id = id;
            this.result = result;
        }

        public OlympiadEntry(FastScanner in) {
            this(in.nextInt(), in.nextInt());
        }

        void println(PrintWriter out) {
            out.print(id);
            out.print(' ');
            out.println(result);
        }

        public static final Comparator<OlympiadEntry> byResult = Comparator
                .comparingInt((OlympiadEntry e) -> e.result)
                .reversed()
                .thenComparingInt((OlympiadEntry e) -> e.id);
    }

    static void mergeSort(OlympiadEntry[] array, Comparator<OlympiadEntry> comparator) {
        OlympiadEntry[] buffer = new OlympiadEntry[array.length];
        mergeSort(array, 0, array.length, buffer, comparator);
    }

    static void mergeSort(OlympiadEntry[] array, int begin, int end, OlympiadEntry[] buffer, Comparator<OlympiadEntry> comparator) {
        if (begin >= end - 1) {
            return;
        }
        int mid = begin + ((end - begin) >> 1);
        mergeSort(array, begin, mid, buffer, comparator);
        mergeSort(array, mid, end, buffer, comparator);
        merge(array, begin, mid, end, buffer, comparator);
    }

    static void merge(OlympiadEntry[] array, int begin, int mid, int end, OlympiadEntry[] buffer, Comparator<OlympiadEntry> comparator) {
        for (int i = begin, j = mid, k = i; i < mid || j < end; ) {
            if (j >= end || i < mid && comparator.compare(array[i], array[j]) <= 0) {
                buffer[k++] = array[i++];
            } else {
                buffer[k++] = array[j++];
            }
        }
        if (end - begin >= 0) {
            System.arraycopy(buffer, begin, array, begin, end - begin);
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
