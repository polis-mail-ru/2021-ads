import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] mass = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = i + 1;
        }
        generate(out, 0, mass);
    }

    private static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i : arr)
            sb.append(i).append(' ');
        return sb.toString();
    }

    private static void generate(final PrintWriter out, int pos, int[] mass) {
        if (pos == mass.length - 1) {
            out.println(arrToString(mass));
            return;
        }
        generate(out, pos + 1, mass);
        for (int i = pos + 1; i < mass.length; i++) {
            int tmp = mass[i];
            mass[i] = mass[pos];
            mass[pos] = tmp;
            Arrays.sort(mass, pos + 1, mass.length);
            generate(out, pos + 1, mass);
            Arrays.sort(mass, pos, mass.length);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
