
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int N1 = in.nextInt();
        int[] arr1  = new int[N1];

        for (int i = 0; i < N1; i++) {
            arr1[i] = in.nextInt();
        }

        final int N2 = in.nextInt();
        int[] arr2 = new int[N2];

        for(int i = 0;i<N2;i++){
            arr2[i] = in.nextInt();
        }

        sort(arr1,0,N1);
        sort(arr2,0,N2);

        int i1 = 0;
        int i2 = 0;
        int counter = 0;

        while (true)
        {

            while (i1+1<arr1.length && arr1[i1] == arr1[i1+1])
                i1++;

            while (i2+1<arr2.length && arr2[i2] == arr2[i2+1])
                i2++;

            if (i1 == arr1.length && i2 == arr2.length)
                break;

            if(i1 == arr1.length || i2 == arr2.length){
                out.println("NO");
                return;
            }
            if(arr1[i1] != arr2[i2]){
                out.println("NO");
                return;
            }else {
                counter++;
                i1++;
                i2++;
            }
        }
            out.println("YES");

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
