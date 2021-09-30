import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public final class Part2Task5 {
    private Part2Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] array1 = new int[N];
        for (int i = 0; i < N; i++) {
            array1[i] = in.nextInt();
        }
        int M = in.nextInt();
        int[] array2 = new int[M];
        for (int i = 0; i < M; i++) {
            array2[i] = in.nextInt();
        }

        sort(array1, 0, N);
        sort(array2, 0, M);

        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr1 < N && ptr2 < M) {
            if (array1[ptr1] != array2[ptr2]) {
                out.println("NO");
                return;
            }
            while (ptr1 < N - 1 && array1[ptr1] == array1[ptr1 + 1]) {
                ptr1++;
            }
            while (ptr2 < M - 1 && array2[ptr2] == array2[ptr2 + 1]) {
                ptr2++;
            }
            ptr1++;
            ptr2++;
        }
        if (ptr1 < N || ptr2 < M) {
            out.println("NO");
            return;
        }
        out.println("YES");
    }

    public static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = randomPartition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    public static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
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
