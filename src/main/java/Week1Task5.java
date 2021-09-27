import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Задача №52. Постфиксная запись
 */
public final class Week1Task5 {
    private Week1Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String token;
        Deque<Integer> stack = new ArrayDeque<>();
        while ((token = in.nextOrNull()) != null) {
            try {
                stack.push(Integer.parseInt(token));
                continue;
            } catch (NumberFormatException ignored) {
            }

            if (stack.size() < 2) {
                // Binary operation requires 2 operands. stack.size() is available
                return;
            }
            int rhs = stack.pop();
            switch (token) {
                case "+":
                    stack.push(stack.pop() + rhs);
                    break;
                case "-":
                    stack.push(stack.pop() - rhs);
                    break;
                case "*":
                    stack.push(stack.pop() * rhs);
                    break;
                case "/":
                    stack.push(stack.pop() / rhs);
                    break;
                default:
                    // Operation 'token' is unsupported
                    return;
            }
        }
        if (stack.size() != 1) {
            // Excessive number of operands or empty input
            return;
        }
        out.println(stack.getFirst().intValue());
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

        /**
         * Same as next(), except it returns null if end of the input is reached
         */
        String nextOrNull() {
            try {
                return next();
            } catch (NullPointerException e) {
                // end of input
                return null;
            }
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
