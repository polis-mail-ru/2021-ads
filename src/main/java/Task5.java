import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        String str = in.next();
        while (str != null) {
            switch (str) {
                case "+":
                    stack.addLast(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    int first = stack.pollLast();
                    int second = stack.pollLast();
                    stack.addLast(second - first);
                    break;
                case "*":
                    stack.addLast(stack.pollLast() * stack.pollLast());
                    break;
                default:
                    stack.addLast(Integer.valueOf(str));
                    break;
            }
            try {
                str = in.next();
            } catch (NoSuchElementException e) {
                break;
            }
        }
        out.println(stack.pollLast());
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
                } catch (NullPointerException e) {
                    throw new NoSuchElementException();
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
