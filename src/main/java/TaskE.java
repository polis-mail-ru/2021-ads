import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void printRecursive(String ready, LinkedList<Integer> left) {
        if (left.size() == 1) {
            System.out.println(ready + left.removeLast());
            return;
        }
        for (int i = 0; i < left.size(); i++) {
            Integer el = left.remove(i);
            printRecursive(ready + el + " ", (LinkedList<Integer>) left.clone());
            left.add(i, el);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int max = in.nextInt();
        for (int i = 1; i <= max; i++) {
            list.addLast(i);
        }
        printRecursive("", list);
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
}
