import java.io.*;
import java.util.StringTokenizer;

public class TaskD {
    private static void solve(final FastScanner in, final PrintWriter out) {
        char[] template = in.next().toCharArray();
        char[] str = in.next().toCharArray();

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '?' || str[i] == '*') {
                char[] temp = template;
                template = str;
                str = temp;
                break;
            }
        }

        boolean[][] dinArr = new boolean[str.length][template.length];

        if (str[0] == template[0] || template[0] == '?' || template[0] == '*') {
            dinArr[0][0] = true;
        } else {
            out.println("NO");
            return;
        }

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < template.length; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                if (template[j] == '*') {
                    dinArr[i][j] = j - 1 >= 0 && dinArr[i][j - 1];
                    dinArr[i][j] = dinArr[i][j] || i - 1 >= 0 && dinArr[i - 1][j];
                } else if (template[j] == '?') {
                    dinArr[i][j] = i - 1 >= 0 && j - 1 >= 0 && dinArr[i - 1][j - 1];
                } else if (str[i] == template[j]) {
                    dinArr[i][j] = i - 1 >= 0 && j - 1 >= 0 && dinArr[i - 1][j - 1];
                } else {
                    dinArr[i][j] = false;
                }
            }
        }

        if (dinArr[str.length - 1][template.length - 1]) {
            out.println("YES");
        } else {
            out.println("NO");
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
