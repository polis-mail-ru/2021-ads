import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Week1Task4 {
    private Week1Task4() {
        // Should not be instantiated
    }

    private static LinkedList<Character> stack = new LinkedList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean isRight = true;

        String sequence = in.next();

        for(int i = 0; i < sequence.length(); ++i) {
            char character = sequence.charAt(i);

            switch (character) {
                case '(':
                case '[':
                case '{': {
                    stack.addLast(character);
                    break;
                }
                case ')': {
                    if (stack.isEmpty()) {
                        isRight = false;
                        break;
                    }

                    char oldCharacter = stack.getLast();
                    stack.removeLast();

                    if (oldCharacter != '(') {
                        isRight = false;
                    }
                    break;
                }
                case ']': {
                    if (stack.isEmpty()) {
                        isRight = false;
                        break;
                    }

                    char oldCharacter = stack.getLast();
                    stack.removeLast();

                    if (oldCharacter != '[') {
                        isRight = false;
                    }
                    break;
                }
                case '}': {
                    if (stack.isEmpty()) {
                        isRight = false;
                        break;
                    }

                    char oldCharacter = stack.getLast();
                    stack.removeLast();

                    if (oldCharacter != '{') {
                        isRight = false;
                    }
                    break;
                }
            }

            if (!isRight) {
                break;
            }
        }

        if (isRight && stack.isEmpty()) {
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
