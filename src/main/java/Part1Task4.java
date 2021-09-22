import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Part1Task4 {
    private Part1Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Character> list = new ArrayList<>(100_000);
        String sequence = in.next();

        boolean check = true;
        for (int i = 0; i < sequence.length() && check; i++) {
            switch (sequence.charAt(i)) {
                case '(':
                    list.add('(');
                    break;
                case '[':
                    list.add('[');
                    break;
                case '{':
                    list.add('{');
                    break;
                case ')':
                    if (!list.isEmpty() && list.get(list.size() - 1) == '(') {
                        list.remove(list.size() - 1);
                    } else {
                        check = false;
                    }
                    break;
                case ']':
                    if (!list.isEmpty() && list.get(list.size() - 1) == '[') {
                        list.remove(list.size() - 1);
                    } else {
                        check = false;
                    }
                    break;
                case '}':
                    if (!list.isEmpty() && list.get(list.size() - 1) == '{') {
                        list.remove(list.size() - 1);
                    } else {
                        check = false;
                    }
                    break;
            }
        }

        out.println((check && list.isEmpty()) ? "yes" : "no");
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
