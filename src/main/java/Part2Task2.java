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

    // Общий сдвиг, чтобы точно убрать возможный минус у инта, при этом не изменив "цикличность" остатков от деления
    // на 108.
    private static final long shift = 108L * 20_100_000L;

    // Локальный сдвиг, который вычисляется так, чтобы максимальное число имело максимальный остаток от деления на 108.
    private static long localShift = 0;

    // Структура данных, чтобы по остатку от деления на 108 восстановить исходное число.
    private static class myMap {
        private final long[] values = new long[108];

        public void putValueWithMod(long value, int mod) {
            values[mod] = value;
        }

        public long getValueFromMod(int mod) {
            return values[mod];
        }
    }

    // Пара, где мы можем хранить число и его остаток от деления на 108.
    private static class myPair {
        private long shiftedValue;
        private int mod;

        public myPair(long value) {
            this.shiftedValue = value + shift;
        }

        public void addLocalShift() {
            shiftedValue += localShift;
            mod = (int) (shiftedValue % 108);
        }

        public void setNewShiftedValue(long value) {
            this.shiftedValue = value;
            this.mod = (int) (shiftedValue % 108);
        }

        public long getNormalValue() {
            return shiftedValue - shift - localShift;
        }

        public long getShiftedValue() {
            return shiftedValue;
        }

        public int getMod() {
            return mod;
        }
    }

    private static void sort(myPair[] arr) {
        int[] countOfOccurrences = new int[108];
        myMap map = new myMap();
        // Заполняем мапу, чтобы по остатку от деления можно было быстро узнать значение.
        // Одновременно считаем количество встречаемости.
        for (int i = 0; i < arr.length; i++) {
            map.putValueWithMod(arr[i].getShiftedValue(), arr[i].getMod());
            countOfOccurrences[arr[i].getMod()]++;
        }
        int j = 0;
        for (int i = 0; i < countOfOccurrences.length; i++) {
            while (countOfOccurrences[i] > 0) {
                arr[j].setNewShiftedValue(map.getValueFromMod(i));
                countOfOccurrences[i]--;
                j++;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long max = Long.MIN_VALUE;
        myPair[] arr = new myPair[n];
        int value;
        for (int i = 0; i < n; i++) {
            value = in.nextInt();
            arr[i] = new myPair(value);
            if (value > max) {
                max = value;
            }
        }
        long shiftedMaxValue = max + shift;
        // Остаток от деления на 108 у максимального числа должен быть максимальным (равным 107).
        localShift = 107 - shiftedMaxValue % 108;
        for (int i = 0; i < n; i++) {
            arr[i].addLocalShift();
        }
        sort(arr);
        for (int i = 0; i < n; i++) {
            out.print(arr[i].getNormalValue() + " ");
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
