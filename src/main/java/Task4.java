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
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        Stack<Character> bracesStack = new Stack();

        for (int i = 0; i < input.length(); i++) {
            char tmpChar = input.charAt(i);
            switch (tmpChar) {
                case ('('):
                case ('['):
                case ('{'):
                    bracesStack.push(tmpChar);
                    break;
                case (')'):
                    if (bracesStack.size() == 0) {
                        out.println("no");
                        return;
                    } else if (bracesStack.back() == '(') {
                        bracesStack.pop();
                        break;
                    }
                    out.println("no");
                    return;
                case (']'):
                    if (bracesStack.size() == 0) {
                        out.println("no");
                        return;
                    } else if (bracesStack.back() == '[') {
                        bracesStack.pop();
                        break;
                    }
                    out.println("no");
                    return;
                case ('}'):
                    if (bracesStack.size() == 0) {
                        out.println("no");
                        return;
                    } else if (bracesStack.back() == '{') {
                        bracesStack.pop();
                        break;
                    }
                    out.println("no");
                    return;
            }
        }
        out.println(bracesStack.size() == 0 ? "yes" : "no");
    }

    private static class Stack<T> {
        LinkedList<T> stack;

        private Stack() {
            this.stack = new LinkedList<>();
        }

        void push(T n) {
            stack.addFirst(n);
        }

        T pop() {
            return stack.pollFirst();
        }

        T back() {
            return stack.getFirst();
        }

        int size () {
            return stack.size();
        }

        void clear() {
            stack = new LinkedList<>();
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
