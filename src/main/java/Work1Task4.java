import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Work1Task4 {
    private Work1Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Integer> stack = new ArrayDeque<>();

        String input = in.next();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '(': {
                    stack.push(1);
                    break;
                }
                case '[': {
                    stack.push(2);
                    break;
                }
                case '{': {
                    stack.push(3);
                    break;
                }
                case ')': {
                    if (!checkClosingBracket(1, stack)) {
                        out.println("no");
                        return;
                    }
                    break;
                }
                case ']': {
                    if (!checkClosingBracket(2, stack)) {
                        out.println("no");
                        return;
                    }
                    break;
                }
                case '}': {
                    if (!checkClosingBracket(3, stack)) {
                        out.println("no");
                        return;
                    }
                    break;
                }
            }
        }

        if (!stack.isEmpty()) out.println("no");
        else out.println("yes");
    }

    private static boolean checkClosingBracket(int numberOfBracket, Deque<Integer> stack) {
        if (stack.isEmpty()) return false;
        int elem = stack.pop();
        return elem == numberOfBracket;
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
