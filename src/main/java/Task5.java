
import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String postfix = in.nextLine();
        Deque<Integer> stack = new LinkedList<>();
        for (int j = 0; j < postfix.length(); j++) {
            char el = postfix.charAt(j);
            if(el == ' ')
                continue;
            if (Character.isDigit(el)) {
                stack.push(Character.digit(el, 10));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                int res = 0;
                switch (el) {
                    case '+':
                        res = a + b;
                        stack.push(res);
                        break;
                    case '-':
                        res = b - a;
                        stack.push(res);
                        break;
                    case '*':
                        res = a * b;
                        stack.push(res);
                        break;
                    case '/':
                        res = b / a;
                        stack.push(res);
                        break;
                    default:
                        stack.push(b);
                        stack.push(a);
                        break;
                }
            }
        }
       out.println(stack.pop());


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

        String nextLine()
        {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tokenizer.nextToken();
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

