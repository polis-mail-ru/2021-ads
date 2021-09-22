import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work1Task5 {
    private Work1Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Integer> stack = new ArrayDeque<>();
        String s = in.next();

        while (!s.equals("")) {
            switch (s) {
                case "*": {
                    int res = stack.pop() * stack.pop();
                    stack.push(res);
                    break;
                }
                case "+": {
                    int res = stack.pop() + stack.pop();
                    stack.push(res);
                    break;
                }
                case "-": {
                    int deductible = stack.pop();
                    int numToBeReduced = stack.pop();
                    int res = numToBeReduced - deductible;
                    stack.push(res);
                    break;
                }
                default: {
                    int number = Integer.parseInt(s);
                    stack.push(number);
                }
            }
            s = in.next();
        }

        out.println(stack.pop());
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
            else return "";
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
