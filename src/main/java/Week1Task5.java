import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Task5 {
    private Week1Task5() {
        // Should not be instantiated
    }

    private static LinkedList<Integer> stack = new LinkedList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        do {
            char character = in.next().charAt(0);

            if (character >= '0' && character <= '9') {
                stack.addLast(character - '0');
            }
            else {
                int secondDigit = stack.getLast();
                stack.removeLast();
                int firstDigit = stack.getLast();
                stack.removeLast();

                switch (character) {
                    case '+': {
                        stack.addLast(firstDigit + secondDigit);
                        break;
                    }
                    case '-': {
                        stack.addLast(firstDigit - secondDigit);
                        break;
                    }
                    case '*': {
                        stack.addLast(firstDigit * secondDigit);
                        break;
                    }
                }
            }
        } while(in.hasNext());

        out.println(stack.getLast());
        stack.removeLast();
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

        boolean hasNext() {
            return tokenizer.hasMoreTokens();
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
