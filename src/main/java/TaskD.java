import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String sequence = in.next();
        for (char bracket : sequence.toCharArray()) {
            if (isOpeningBracket(bracket)) {
                stack.push(bracket);
            } else {
                if (stack.size() == 0 || openingBracket(bracket) != stack.pop()) {
                    out.println("no");
                    return;
                }
            }
        }
        if (stack.size() == 0) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }

    private static boolean isOpeningBracket(char bracket) {
        return (bracket == '(' || bracket == '{' || bracket == '[');
    }

    private static char openingBracket(char bracket) {
        switch (bracket) {
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                return '?';
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
