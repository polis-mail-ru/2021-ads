import javax.management.ListenerNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        String str = in.next();
        LinkedList<Character> bracketsStack = new LinkedList<>();

        int i = 0;
        char tmp;
        while (i < str.length()) {
            tmp = str.charAt(i);
            if (tmp == '(' || tmp == '{' || tmp == '[') {
                bracketsStack.add(tmp);
            } else {
                if (!bracketsStack.isEmpty()) {
                    switch (tmp) {
                        case '}':
                            if (bracketsStack.peekLast() == '{')
                                bracketsStack.pollLast();
                            else
                                i = str.length();
                            break;
                        case ']':
                            if (bracketsStack.peekLast() == '[')
                                bracketsStack.pollLast();
                            else
                                i = str.length();
                            break;
                        case ')':
                            if (bracketsStack.peekLast() == '(')
                                bracketsStack.pollLast();
                            else
                                i = str.length();
                            break;
                    }
                } else if (tmp == '}' || tmp == ']' || tmp == ')') {
                    bracketsStack.add(tmp);//добавляем элемент в стек чтобы он не был пустым при окончательной проверке
                    i = str.length();
                }
            }
            i++;
        }

        if (bracketsStack.isEmpty())
            out.println("yes");
        else
            out.println("no");
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
