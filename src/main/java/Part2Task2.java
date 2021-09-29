import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task2 {
    private Part2Task2() {
        // Should not be instantiated
    }


    private static class myMap {
        private long[] values = new long[108];

        public void putValueWithMod(long value, int mod) {
            values[mod] = value;
        }

        public long getValueFromMod(int mod) {
            return values[mod];
        }
    }

    // Пара, где мы можем хранить число и его остаток от деления на 108.
    private static class myPair {
        private final long value;
        private final int mod;

        public myPair(long value) {
            this.value = value;
            this.mod = (int) (value % 108);
        }

        public long getValue() {
            return value;
        }

        public int getMod() {
            return mod;
        }
    }

    private static void sort(myPair[] arr) {
        int[] countOfOccurrences = new int[108];
        myMap map = new myMap();
        // Заполняем мапу, чтобы по остатку от деления можно было быстро узнать значение.
        // Одновременно считаем количество встречаемости для младших разрядов
        for (int i = 0; i < arr.length; i++) {
            map.putValueWithMod(arr[i].getValue(), arr[i].getMod());
            countOfOccurrences[arr[i].getMod()]++;
        }
        int j = 0;
        for (int i = 0; i < countOfOccurrences.length; i++) {
            while (countOfOccurrences[i] > 0) {
                arr[j] = new myPair(map.getValueFromMod(i));
                countOfOccurrences[i]--;
                j++;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        myPair[] arr = new myPair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new myPair(in.nextInt() + (long) Integer.MAX_VALUE + 10);
        }
        sort(arr);
        for (int i = 0; i < n - 1; i++) {
            out.print(arr[i].getValue() - Integer.MAX_VALUE - 10 + " ");
        }
        out.println(arr[n - 1].getValue() - Integer.MAX_VALUE - 10);
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
