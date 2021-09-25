import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Brackets {

    static LinkedList<Character> stack = new LinkedList<>();

    private static void solve(final FastScanner in) {
        String line = in.next();
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '(' || line.charAt(i) == '{' || line.charAt(i) == '[') {
                stack.add(line.charAt(i));
            } else if (!stack.isEmpty()) {
                switch (line.charAt(i)) {
                    case(')') :
                        if (stack.peekLast() == '(') {
                            stack.pollLast();
                        }
                        else {
                            System.out.println("no");
                            return;
                        }
                        break;
                    case('}') :
                        if (stack.peekLast() == '{') {
                            stack.pollLast();
                        }
                        else {
                            System.out.println("no");
                            return;
                        }
                        break;
                    case(']') :
                        if (stack.peekLast() == '[') {
                            stack.pollLast();
                        }
                        else {
                            System.out.println("no");
                            return;
                        }
                        break;
                    default:
                        System.out.println("no");
                        return;
                }
            }
            else {
                System.out.println("no");
                return;
            }
            i++;
        }
        System.out.println(!stack.isEmpty() ? "no" : "yes");
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        solve(in);
    }
}
