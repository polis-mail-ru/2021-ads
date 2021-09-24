import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Character> stack = new LinkedList<>();
        String token = in.next();

        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            char other = token.charAt(i);
            switch (c) {
                case ')':
                    other = '(';
                    break;
                case ']':
                    other = '[';
                    break;
                case '}':
                    other = '{';
                    break;
                default:
                    stack.addLast(token.charAt(i));
                    continue;
            }

            if (stack.isEmpty() || stack.pollLast() != other) {
                out.println("no");
                return;
            }
        }

        if (stack.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
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
