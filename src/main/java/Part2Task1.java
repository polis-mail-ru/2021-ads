import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part2Task1 {
    private Part2Task1() {
        // Should not be instantiated
    }

    private static class Person implements Comparable<Person> {

        private final int id;
        private final int score;

        public Person(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Person o) {
            if (getScore() > o.getScore()) {
                return 1;
            }
            if (getScore() < o.getScore()) {
                return -1;
            }
            if (getId() < o.getId()) {
                return 1;
            }
            return 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Person[] people = new Person[n];
        for (int i = 0; i < n; ++i) {
            people[i] = new Person(in.nextInt(), in.nextInt());
        }
        quickSort(people, 0, n);
        for (int i = 0; i < n; ++i) {
            out.println(people[i].getId() + " " + people[i].getScore());
        }
    }

    private static void quickSort(Person[] people, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(people, fromInclusive, toExclusive);
        quickSort(people, fromInclusive, i);
        quickSort(people, i + 1, toExclusive);
    }

    private static int partition(Person[] people, int fromInclusive, int toExclusive) {
        Person pivotal = people[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (people[j].compareTo(pivotal) > 0) {
                swap(people, ++i, j);
            }
        }
        swap(people, i, fromInclusive);
        return i;
    }

    private static void swap(Person[] people, int index, int indexNext) {
        Person indexVariable = people[index];
        people[index] = people[indexNext];
        people[indexNext] = indexVariable;
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