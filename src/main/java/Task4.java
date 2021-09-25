import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static class Stack {
        private final List<Character> list;

        public Stack() {
            this.list = new ArrayList<>();
        }

        public void push(Character n) {
            list.add(n);
        }

        public char pop() {
            int index = list.size() - 1;
            Character res = list.get(index);
            list.remove(index);
            return res;
        }

        public char back() {
            return list.get(list.size() - 1);
        }

        public int size() {
            return list.size();
        }

        public void clear() {
            list.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String str = in.next();
        char firstChar = str.charAt(0);
        if (firstChar == ')' || firstChar == ']' || firstChar == '}') {
            out.println("no");
            return;
        }
        stack.push(firstChar);
        int currentIndex = 1;
        while (currentIndex < str.length()) {
            char currentSymbol = str.charAt(currentIndex);
            char lastSymbol;
            if (stack.size() != 0) {
                lastSymbol = stack.back();
            } else {
                if (currentSymbol == ')' || currentIndex == ']' || currentSymbol == '}') {
                    out.println("no");
                    return;
                }
                stack.push(currentSymbol);
                currentIndex++;
                continue;
            }
            switch (currentSymbol) {
                case '(':
                case '[':
                case '{':
                    stack.push(currentSymbol);
                    currentIndex++;
                    break;
                case ')':
                case ']':
                case '}':
                    if (
                            currentSymbol == ')' & lastSymbol == '('
                                    || currentSymbol == ']' & lastSymbol == '['
                                    || currentSymbol == '}' & lastSymbol == '{'
                    ) {
                        stack.pop();
                        currentIndex++;
                    } else {
                        out.println("no");
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
        if (stack.size() == 0 ) {
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
