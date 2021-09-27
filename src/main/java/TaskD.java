import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        Stack<Integer> stack = new Stack<>();
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            switch (a) {
                case '(':
                    stack.push(0);
                    break;
                case '[':
                    stack.push(1);
                    break;
                case '{':
                    stack.push(2);
                    break;
                case ')':
                    if (stack.empty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.peek() != 0) {
                        System.out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.empty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.peek() != 1) {
                        System.out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (stack.empty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.peek() != 2) {
                        System.out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
            }
        }

        if (stack.empty())
            System.out.println("yes");
        else
            System.out.println("no");
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
