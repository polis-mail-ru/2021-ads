import org.jetbrains.annotations.NotNull;

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

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        //Can't be NPE, guaranteed by the condition of the task
        String element = in.next();
        while (!element.isEmpty()) {
            if (element.equals("+") || element.equals("-") || element.equals("*")) {
                if (element.equals("+")) stack.addFirst(stack.poll() + stack.poll());
                if (element.equals("-")) stack.addFirst(-stack.poll() + stack.poll());
                if (element.equals("*")) stack.addFirst(stack.poll() * stack.poll());
            } else {
                stack.addFirst(Integer.parseInt(element));
            }
            element = in.next();
        }
        out.println(stack.poll());
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
            else return "";
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
