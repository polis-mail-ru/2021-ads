import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week2Task1 {
    private Week2Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfPairs = in.nextInt();

        ArrayList<MyEntry> data = new ArrayList<>();
        for (int i = 0; i < countOfPairs; i++) {
            data.add(new MyEntry(in.nextInt(), in.nextInt()));
        }
        mySort(data);
        for (MyEntry entry : data) {
            out.println(entry.id + " " + entry.score);
        }
    }

    private static void mySort(ArrayList<MyEntry> array) {
        ArrayList<MyEntry> temp = new ArrayList<>(array.size());
        mergeSort(array, temp, 0, array.size());
    }

    private static void mergeSort(ArrayList<MyEntry> array, ArrayList<MyEntry> temp, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, temp, fromInclusive, mid);
        mergeSort(array, temp, mid, toExclusive);
        merge(array, temp, fromInclusive, mid, toExclusive);
    }

    private static void merge(ArrayList<MyEntry> array, ArrayList<MyEntry> temp, int fromInclusive, int mid, int toExclusive) {
        int i = 0;
        int i1 = fromInclusive;
        int i2 = mid;
        while (i1 < mid || i2 < toExclusive) {
            if (i1 < mid && (i2 == toExclusive || array.get(i1).compareTo(array.get(i2)) > 0)) {
                temp.add(i, array.get(i1));
                i1++;
            } else {
                temp.add(i, array.get(i2));
                i2++;
            }
            i++;
        }
        for (i = 0; i < toExclusive - fromInclusive; i++) {
            array.set(i + fromInclusive, temp.get(i));
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

    static class MyEntry implements Comparable<MyEntry> {
        int score;
        int id;

        MyEntry(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Week2Task1.MyEntry other) {
            //can't be equals, cause of id
            if (this.score > other.score) {
                return 1;
            }
            if (this.score == other.score) {
                if (this.id < other.id) return 1;
            }
            return -1;
        }
    }
}
