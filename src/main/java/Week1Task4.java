import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class Week1Task4 {
    private Week1Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        if (IfRightBracketSequence(in.next()))
        {
            out.println("yes");
        }
        else {
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

    public static boolean IfRightBracketSequence(String input) {
        LinkedList<Character> bracketsStack = new LinkedList<Character>();
        int length = input.length();
        char curBracket;
        for (int i = 0; i < length; i++) {
            curBracket = input.charAt(i);
            switch (curBracket) {
                case '(': bracketsStack.push(')'); break;
                case '[': bracketsStack.push(']'); break;
                case '{': bracketsStack.push('}'); break;
                case ')':
                case ']':
                case '}':
                    if (bracketsStack.size() == 0 || curBracket != bracketsStack.pop()) {
                        return false;
                    }
                    break;
            }
        }
        return bracketsStack.size() == 0;
    }
}