import java.io.*;
import java.util.StringTokenizer;

public class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int elemsC = in.nextInt();
        int values[] = new int[elemsC];
        values[0] = in.nextInt();
        int min = values[0];
        for (int i = 1; i < elemsC; i++) {
            values[i] = in.nextInt();
            if (values[i] < min) {
                min = values[i];
            }
        }

        final int maxDifference = 107;
        Element sortedElems[] = countingSort(min, values, maxDifference);
        for (Element element : sortedElems) {
            if (element != null) {
                for (int i = 0; i < element.getCount(); i++) {
                    out.print(element.getValue() + " ");
                }
            }
        }
    }

    private static Element[] countingSort(int min, int values[], int maxDifference) {
        Element sortedElems[] = new Element[maxDifference];
        for (int value : values) {
            if (sortedElems[value - min] != null) {
                sortedElems[value - min].plusCount();
            } else {
                sortedElems[value - min] = new Element(value);
            }
        }
        return sortedElems;
    }

    private static class Element {
        private int count = 0;
        private int value;

        public Element(int value) {
            this.value = value;
            count = 1;
        }

        public int getCount() {
            return count;
        }

        public int getValue() {
            return value;
        }

        public void plusCount() {
            count++;
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
