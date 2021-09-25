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

    private static Character getOpenBracket(Character bracket) {
        if (bracket.equals(')')) return '(';
        if (bracket.equals(']')) return '[';
        return '{';
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String data = in.next();
        LinkedList<Character> needToClose = new LinkedList<>();//last open bracket
        for (Character symbol : data.toCharArray()) {
            if (symbol.equals('(') || symbol.equals('[') || symbol.equals('{')) {
                needToClose.add(symbol);
            } else if (symbol.equals(')') || symbol.equals(']') || symbol.equals('}')) {
                if (needToClose.size() == 0 || !needToClose.pollLast().equals(getOpenBracket(symbol))) {
                    out.println("no");
                    return;
                }
            } else {
                out.println("no");
                return;
            }
        }
        if (needToClose.isEmpty()) out.println("yes");
        else out.println("no");
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
