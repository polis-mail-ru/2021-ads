import java.io.*;
import java.security.InvalidParameterException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String infix = null;
        try {
            infix = in.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stack<Integer> stack = new Stack<>();
        int a, b, infixSize = infix.length();
        for (int i = 0; i < infixSize; i++) {
            if(infix.charAt(i) == ' ')
                continue;
            switch (infix.charAt(i)) {
                case '+':
			/*
			if(stack.size() < 2)
				throw std::invalid_argument("Entered wrong seq\n");
				*/
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    a = stack.peek();
                    stack.pop();
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    b = stack.peek();
                    stack.pop();
                    stack.push(a + b);
                    break;

                case '-':
			/*
			if (stack.size() < 2)
				throw std::invalid_argument("Entered wrong seq\n");
				*/
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    a = stack.peek();
                    stack.pop();
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    b = stack.peek();
                    stack.pop();
                    stack.push(b - a);
                    break;

                case '*':
			/*if (stack.size() < 2)
				throw std::invalid_argument("Entered wrong seq\n");*/
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    a = stack.peek();
                    stack.pop();
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    b = stack.peek();
                    stack.pop();
                    stack.push(a * b);
                    break;

                case '/':
			/*if (stack.size() < 2)
				throw std::invalid_argument("Entered wrong seq\n");*/
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    a = stack.peek();
                    stack.pop();
                    if (stack.isEmpty())
                        throw new InvalidParameterException("Entered wrong seq\n");
                    b = stack.peek();
                    stack.pop();
                    stack.push((int) (b / a));
                    break;

                default:
                    if (infix.charAt(i) <= '9' && infix.charAt(i) >= '0') {
                        StringBuilder strbdl = new StringBuilder();
                        do {
                            strbdl.append(infix.charAt(i));

                        } while ( (i < infix.length() - 1) && infix.charAt(++i) != ' ');

                        stack.push(Integer.parseInt(strbdl.toString()));
                    }
                    else
                        throw new InvalidParameterException("Entered unknown symbol\n");
                    break;
            }
        }
        if (stack.size() == 1)
            System.out.println(stack.peek());
        else
            throw new InvalidParameterException("Entered wrong seq\n");
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
