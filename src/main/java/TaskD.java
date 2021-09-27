import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        ArrayList<Integer> stack = new ArrayList<>();
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            switch (a) {
                case '(':
                    stack.add(0);
                    break;
                case '[':
                    stack.add(1);
                    break;
                case '{':
                    stack.add(2);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.get(stack.size()-1) != 0) {
                        System.out.println("no");
                        return;
                    }
                    stack.remove(stack.size()-1);
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.get(stack.size()-1) != 1) {
                    System.out.println("no");
                    return;
                }
                stack.remove(stack.size()-1);
                break;
                case '}':
                    if (stack.isEmpty()) {
                        System.out.println("no");
                        return;
                    }
                    if (stack.get(stack.size()-1 )!= 2) {
                    System.out.println("no");
                    return;
                }
                stack.remove(stack.size()-1);
                break;
            }
        }

        if (stack.isEmpty())
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
