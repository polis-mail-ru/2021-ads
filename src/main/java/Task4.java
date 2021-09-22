
import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static char getCloseBrace(char openBrace)
    {
        switch (openBrace)
        {
            case '(': return ')';
            case '{': return '}';
            default: return ']';
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Character> braces = new LinkedList<>();
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (cur == '(' || cur == '{' || cur == '[') {
                braces.push(cur);
            }
            else if (cur == ')' || cur == '}' || cur == ']')
            {
                if (braces.isEmpty() || cur != getCloseBrace(braces.pop()))
                {
                    out.println("no");
                    return;
                }
            }
        }
        if (!braces.isEmpty())
            out.println("no");
        else
            out.println("yes");
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
