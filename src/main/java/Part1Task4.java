import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part1Task4 {
    private Part1Task4() {
        // Should not be instantiated
    }

    private static final ArrayList<Character> stack = new ArrayList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {

        String inToString = in.next();
        int stackSize = 0;

        for (int i = 0; i < inToString.length(); i++) {
            switch (inToString.charAt(i)) {
                case '(':
                case '{':
                case '[':
                    stack.add(inToString.charAt(i));
                    stackSize++;
                    break;
                case ')':
                    if (stack.isEmpty() || !(stack.get(stackSize - 1) == ('('))) {
                        out.println("no");
                        return;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || !(stack.get(stackSize - 1) == ('{'))) {
                        out.println("no");
                        return;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || !(stack.get(stackSize - 1) == ('['))) {
                        out.println("no");
                        return;
                    } else {
                        stack.remove(stack.size() - 1);
                    }
                    break;
            }
        }
        out.println(stack.isEmpty() ? "yes" : "no");
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
